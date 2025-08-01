package com.coderzoe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yinhuasheng
 * @date 2025/7/10 09:49
 */
@SpringBootApplication
@RestController
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello cicd develop env";
    }
}