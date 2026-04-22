package com.medflow.labs.jvm;

public class JitWarmupDemo {
    public static void main(String[] args) {
        long sum = 0;
        for (int i = 0; i < 1_000_000; i++) sum += i;
        System.out.println(sum);
    }
}
