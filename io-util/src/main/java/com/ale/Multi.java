package com.ale;

import com.ale.bean.Msg;
import com.ale.bean.User;
import com.github.javafaker.Faker;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
public class Multi {
    public static void main(String[] args) throws InterruptedException {
        List<Msg> msgs = Lists.newArrayList();
        //设置 语言 ，地区
        Locale local = new Locale("zh", "CN");
        //创建对象
        Faker faker = new Faker(local);
        for (int i = 0; i < 8; i++) {
            Msg msg = new Msg();
            msg.setAppId(faker.app().author());
            msg.setContent(faker.company().name());
            msgs.add(msg);
        }
        Map<String, List<Msg>> appidToMsg = msgs.stream().collect(Collectors.groupingBy(Msg::getAppId));

        List<User> users = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setName(faker.name().username());
            users.add(user);
        }


        Executor sendMsgExecutor = ExecutorUtil.getSendMsgExecutor();
        Executor userExecutor = ExecutorUtil.getUserExecutor();
        for (Map.Entry<String, List<Msg>> entry : appidToMsg.entrySet()) {
            CompletableFuture.runAsync(() -> {
                log.error("线程名称：【{}】", Thread.currentThread().getName());
                long start = Instant.now().getEpochSecond();
                log.info("start appid:{}", entry.getKey());
                List<Msg> value = entry.getValue();
                for (Msg msg : value) {
                    log.info("start send msg:{}", msg.getContent());

                    for (User user : users) {
                        CompletableFuture<Boolean> completableFuture = CompletableFuture.supplyAsync(() -> {
                            return sendMsg(msg, user);
                        }, userExecutor);
                    }
                }
                long end = Instant.now().getEpochSecond();
                log.warn("appid:{} takes:{}", entry.getKey(), end - start);
                System.out.println("I'll run in a separate thread than the main thread.");
            }, sendMsgExecutor);
        }

        TimeUnit.SECONDS.sleep(500);

    }

    private static boolean sendMsg(Msg msg, User user) {
        int i = ThreadLocalRandom.current().nextInt(1, 2);
        try {
            TimeUnit.SECONDS.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("send msg:{} to user:{}, takes {} s", msg.getContent(), user.getName(), i);
        return Boolean.TRUE;
    }

}
