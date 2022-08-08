package com.ale.demo;

public interface DemoInterface {
    default String hello(String message) {
        return "Hello " + message;
    }
}
