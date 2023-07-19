package com.example.booking;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication   // SpringBoot App 的入口注解
@MapperScan("com.example.booking.mapper")  // Mybatis 框架注解
public class BookingApplication {

    public static void main(String[] args) {

        SpringApplication.run(BookingApplication.class, args);
    }

}
