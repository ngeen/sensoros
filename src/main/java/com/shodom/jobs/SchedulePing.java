package com.shodom.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.shodom.model.Machine;
import com.shodom.repository.MachineRepository;
import com.shodom.tools.PingEngine;

@Component
public class SchedulePing {

	@Autowired
	private MachineRepository machineRepository;

	//@Scheduled(fixedRate = 10000)
	public void reportCurrentTime() {
		for (Machine mac : machineRepository.findAll()) {
			PingEngine.getPing(mac.ip);
		}
	}
}
