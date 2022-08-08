package com.ale.demo;

public class DemoInterfaceImpl implements DemoInterface{
//     @Override
//     public String hello(String message) {
//        return "Hello " + message +  "ss";
//    }

    public static void main(String[] args) {
        System.out.println(new DemoInterfaceImpl().hello("ok"));
    }
}
