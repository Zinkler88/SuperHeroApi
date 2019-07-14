package com.superhero;


import io.github.kaiso.relmongo.config.EnableRelMongo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableRelMongo
public class SuperHero {
    public static void main(String[] args) {
        SpringApplication.run(SuperHero.class, args);
    }
}
