package com.edgewalk.accidence.signaling;

/**
 * Created by: edgewalk
 * 2018-08-20 21:46
 */
public class Main {
    public static void main(String[] args) {
        Apple apple = new Apple();
        Producer producer = new Producer(apple);
        Consumer consumer = new Consumer(apple);
        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
