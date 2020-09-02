package com.ale.wheeltimer;

import io.netty.util.Timeout;
import io.netty.util.TimerTask;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
  *
  * @author alewu
  * @date 2020/9/2
  */
@Slf4j
@Data
@AllArgsConstructor
public class MyTimerTask implements TimerTask {
    private boolean flag;
    @Override
    public void run(Timeout timeout) throws Exception {
        log.info("要去数据库删除订单了。。。。");
        this.flag= false;
    }
}
