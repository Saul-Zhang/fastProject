package com.fastproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(scanBasePackages = "com.fastproject")
@MapperScan(basePackages = "com.fastproject.mapper")
@EnableCaching
public class FastProjectApplication {

  public static void main(String[] args) {

    SpringApplication.run(FastProjectApplication.class, args);

  }

}
