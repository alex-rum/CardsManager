package com.paymentsmanager;

import org.springframework.boot.SpringApplication;

public class TestPaymentsManagerApplication {

    public static void main(String[] args) {
        SpringApplication.from(PaymentsManagerApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
