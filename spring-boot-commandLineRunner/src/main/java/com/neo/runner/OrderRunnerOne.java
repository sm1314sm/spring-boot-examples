package com.neo.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Component
public class OrderRunnerOne implements CommandLineRunner {
    @Override
    public void run(String... args) {
        System.out.println("The OrderRunner1 start to initialize ...");
    }
}