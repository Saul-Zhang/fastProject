package com.fc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.fc.v2")
@MapperScan(basePackages = "com.fc.v2.mapper")
public class V2Application {

  public static void main(String[] args) {

    SpringApplication.run(V2Application.class, args);

  }

}
