package com.example.modelsviewerweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Application.class);

//		builder.headless(true);

        ConfigurableApplicationContext context = builder.run(args);
    }

}
