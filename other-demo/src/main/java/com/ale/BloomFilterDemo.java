package com.ale;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BloomFilterDemo {

    private static int size = 1000000;

    private static BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), size);

    public static void main(String[] args) {
        for (int i = 0; i < size; i++) {
            bloomFilter.put(i);
        }
        // 获取开始时间
        long startTime = System.nanoTime();
        //判断这一百万个数中是否包含29999这个数
        if (bloomFilter.mightContain(29999)) {
            log.info("命中了");
        }
        long endTime = System.nanoTime();   // 获取结束时间
        log.info("程序运行时间： {} ns", (endTime - startTime));
    }

}
