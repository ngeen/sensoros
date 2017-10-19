package com.shodom.jobs;

import java.net.InetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.shodom.model.Machine;
import com.shodom.model.MachinePing;
import com.shodom.repository.MachinePingRepository;
import com.shodom.repository.MachineRepository;
import com.shodom.service.NextSequenceService;

@Component
public class SchedulePing {
	
	@Autowired
	private MachineRepository machineRepository;
	
	@Autowired
	private MachinePingRepository machinePingRepository;
	
	@Autowired
	private NextSequenceService nss;
	
	@Scheduled(fixedRate = 10000)
    public void reportCurrentTime() {
		long seq = nss.getNextSequence("pingSeq");
        for (Machine mac : machineRepository.findAll()) {
        	MachinePing machinePing = new MachinePing();
        	machinePing.machine = mac;
        	try {
        		InetAddress address = InetAddress.getByName(mac.ip);

                boolean reachable = address.isReachable(5000);
                
                machinePing.access = reachable;
			} catch (Exception e) {
				machinePing.access = false;
			}
        	machinePing.pingSeq = seq;
        	
        	machinePingRepository.save(machinePing);
		}
    }
}
