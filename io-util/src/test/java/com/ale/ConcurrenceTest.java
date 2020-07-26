package com.ale;

import org.junit.jupiter.api.Test;

public class ConcurrenceTest {

    @Test
    void test(){
//        List<CompletableFuture<Boolean>> futures = Lists.newLinkedList();
//        for (WxMpKefuMessage wxMpKefuMessage : wxMpKefuMessages) {
//            CompletableFuture<Boolean> future = kfMsgSendToOneUserAsyncService.sendKfMsgAsync(kefuService,
//                                                                                              wxMpKefuMessage);
//            futures.add(future);
//        }
//        CompletableFuture<Void> allFutures =
//                CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
//        CompletableFuture<List<Boolean>> allMsgResultFuture =
//                allFutures.thenApply(v -> futures.stream().map(CompletableFuture::join)
//                                                 .collect(Collectors.toList()));
//        List<Boolean> msgResult = allMsgResultFuture.join();
//
//        long successQty = msgResult.parallelStream().filter(aBoolean -> Objects.equals(aBoolean, Boolean.TRUE)).count();
    }
}
