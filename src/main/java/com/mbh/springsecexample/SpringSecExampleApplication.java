package com.mbh.springsecexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SpringSecExampleApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringSecExampleApplication.class, args);
  }

}
