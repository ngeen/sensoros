package com.shodom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.shodom.repository.MachineRepository;

@EnableScheduling
@SpringBootApplication
public class SensorOsApplication {
	
	@Autowired
	private MachineRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SensorOsApplication.class, args);
	}
}
