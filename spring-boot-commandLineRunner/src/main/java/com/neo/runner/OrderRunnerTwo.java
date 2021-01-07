package com.neo.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)
@Component
public class OrderRunnerTwo implements CommandLineRunner {
    @Override
    public void run(String... args) {
        System.out.println("The OrderRunner2 start to initialize ...");
    }
}