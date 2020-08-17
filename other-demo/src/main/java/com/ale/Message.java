package com.ale;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;


@Data
@Slf4j
public class Message {
    private String content;

    public Message(String content) {
        this.content = content;
    }

    public void display() {
       log.info("display {}", content);
    }
}
