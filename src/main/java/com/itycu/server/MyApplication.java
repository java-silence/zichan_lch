package com.itycu.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动类
 * 
 * @author 小威老师 xiaoweijiagou@163.com
 *
 */
@SpringBootApplication
@EnableScheduling
public class MyApplication {

	public static void main(String[] args) {

		SpringApplication.run(MyApplication.class, args);
	}
}
