package cn.psy.homework11;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.psy.homework11.dao")
public class Homework11Application {

    public static void main(String[] args) {
        SpringApplication.run(Homework11Application.class, args);
    }

}

