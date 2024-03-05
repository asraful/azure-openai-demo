package com.learn.demo.ai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class PlaygroundaiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlaygroundaiApplication.class, args);
	}
}
