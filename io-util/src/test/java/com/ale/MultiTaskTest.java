package com.ale;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;


/**
 * Unit test for simple App.
 */
@Slf4j
class MultiTaskTest {

    /**
     * 使用map保存对应的CompletableFuture，然后在后面获取结果
     */
    @Test
    void test() {
        Executor executor = ExecutorUtil.getExecutor();

        long start = Instant.now().getEpochSecond();

        List<String> names = getNames();
        Map<String, CompletableFuture<String>> nameToFuture = new HashMap<>(5);
        names.forEach(name -> {
            CompletableFuture<String> completableFuture =
                    CompletableFuture.supplyAsync(() -> new ImgPressTaskSupplier(name).press(), executor);
            nameToFuture.put(name, completableFuture);
        });

        //  Map<String, String> nameToResult = nameToFuture.entrySet().stream().collect(Collectors.toMap(Map
        //  .Entry::getKey,
        //                                                                                                v -> v
        //                                                                                                .getValue()
        //                                                                                                .join()));

        Map<String, String> nameToResult = new HashMap<>(5);
        nameToFuture.forEach((key, value) -> nameToResult.put(key, value.join()));

        log.info("takes time total {} s", Instant.now().getEpochSecond() - start);
        System.out.println(nameToResult);

    }

    /**
     * 直接存在一个list中，然后在后面获取
     */
    @Test
    void testMap() {

        Executor executor = ExecutorUtil.getExecutor();

        long start = Instant.now().getEpochSecond();

        List<String> names = getNames();

        List<CompletableFuture<ImgPressTaskMapSupplier>> collect =
                names.stream().map(name -> CompletableFuture.supplyAsync(() -> new ImgPressTaskMapSupplier(name)
                        , executor)).collect(Collectors.toList());

        Map<String, String> collect1 =
                collect.stream().map(CompletableFuture::join).collect(Collectors.toMap(ImgPressTaskMapSupplier::getName,
                                                                                       ImgPressTaskMapSupplier::press));

        log.info("takes time total {} s", Instant.now().getEpochSecond() - start);
        System.out.println(collect1);
    }

    @Test
    void testThenApply() {
        Executor executor = ExecutorUtil.getExecutor();

        long start = Instant.now().getEpochSecond();

        List<String> names = getNames();
        Map<String, CompletableFuture<String>> nameToFuture = new HashMap<>(5);
        names.forEach(name -> {
            CompletableFuture<String> completableFuture =
                    CompletableFuture.supplyAsync(() -> new ImgTask(name).scale(), executor).thenApplyAsync(s -> new ImgTask(name).press(s), executor);
            nameToFuture.put(name, completableFuture);
        });

        Map<String, String> nameToResult = nameToFuture.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey,
                                                                                                     v -> v.getValue()
                                                                                                           .join()));

        Collection<CompletableFuture<String>> values = nameToFuture.values();
        CompletableFuture<Void> allFutures =
                CompletableFuture.allOf(values.toArray(new CompletableFuture[values.size()]));
        CompletableFuture<List<String>> allMsgResultFuture =
                allFutures.thenApply(v -> values.stream().map(CompletableFuture::join)
                                                 .collect(Collectors.toList()));
        List<String> msgResult = allMsgResultFuture.join();

        CompletableFuture.allOf(values.toArray(new CompletableFuture[values.size()]))
                         .thenApply(v -> values.stream().map(CompletableFuture::join).collect(Collectors.toList()))
                         .join();

        log.info("takes time total {} s", Instant.now().getEpochSecond() - start);
        System.out.println(nameToResult);
    }

    private static List<String> getNames() {
        List<String> names = new ArrayList<>();
        names.add("a");
        names.add("b");
        names.add("c");
        names.add("d");
        names.add("e");
        return names;
    }

}
