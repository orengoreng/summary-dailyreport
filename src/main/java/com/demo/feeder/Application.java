package com.demo.feeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.demo.feeder.executor.InputFeederReader;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableScheduling
@SpringBootApplication
public class Application {

	@Autowired
	private InputFeederReader feederReader;

	public static void main(String args[]) {
		SpringApplication.run(Application.class, args);
	}

	@Scheduled(fixedRateString = "${feeder.rate}")
	public void feeder() {
		try {
			log.info("## input feeder task execute ##");
			feederReader.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
