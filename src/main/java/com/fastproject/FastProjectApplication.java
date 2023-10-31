package com.fastproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.fastproject")
@MapperScan(basePackages = "com.fastproject.mapper")
@EnableCaching
@EnableScheduling
public class FastProjectApplication {

  public static void main(String[] args) {

    SpringApplication.run(FastProjectApplication.class, args);

  }

}
