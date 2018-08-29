package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 要将Application类放在最外侧,即包含所有子包
 * 原因:spring-boot会自动加载启动类所在包下及其子包下的所有组件.
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}
}
