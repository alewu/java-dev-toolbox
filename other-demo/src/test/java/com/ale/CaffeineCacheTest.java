package com.ale;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Expiry;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.TimeUnit;

import static java.time.temporal.ChronoUnit.MILLIS;

public class CaffeineCacheTest {
    @Test
    void test() throws InterruptedException {
        LoadingCache<String, DataObject> cache = Caffeine.newBuilder().expireAfter(new Expiry<String, DataObject>() {
            @Override
            public long expireAfterCreate(
                    String key, DataObject value, long currentTime) {

                long seconds = LocalDateTime.now().plusSeconds(5)
                                    .minus(System.currentTimeMillis(), MILLIS)
                                   .toEpochSecond(ZoneOffset.of("Z"));
                long l = TimeUnit.MILLISECONDS.toSeconds(currentTime);
                System.out.println("currentTime " + l);
                return TimeUnit.SECONDS.toNanos(seconds);
            }

            @Override
            public long expireAfterUpdate(
                    String key, DataObject value, long currentTime, long currentDuration) {
                return currentDuration;
            }

            @Override
            public long expireAfterRead(
                    String key, DataObject value, long currentTime, long currentDuration) {
                return currentDuration;
            }
        }).build(k -> DataObject.get("Data for " + k + Instant.now()));

        for (int i = 0; i < 5; i++) {
            DataObject ok1 = cache.get("ok");
            System.out.println(ok1.toString());
            TimeUnit.SECONDS.sleep(2);
        }

        DataObject ok2 = cache.get("ok1");
        System.out.println(ok2.toString());
        TimeUnit.SECONDS.sleep(10);
        DataObject ok3 = cache.get("ok");
        System.out.println(ok3.toString());
        TimeUnit.MINUTES.sleep(10);
    }

}
