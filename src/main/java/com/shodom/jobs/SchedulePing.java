package com.shodom.jobs;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.shodom.model.Machine;
import com.shodom.repository.MachineRepository;
import com.shodom.tools.PingEngine;

@Component
public class SchedulePing {
	
	private Logger logger = LoggerFactory.getLogger(SchedulePing.class);
	
	String[] mailAdresses = { "enginoz@sinerjias.com.tr", "emrahaltingoller@sinerjias.com.tr",
			"gultekincan@sinerjias.com.tr"};
	
	private Date lastSended;
	
	@Autowired
	private MachineRepository machineRepository;

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Scheduled(fixedRate = 10000)
	public void reportCurrentTime() {
		boolean zaman = false;
		boolean hata = false;
		if(lastSended != null){
			long diff = new Date().getTime() - lastSended.getTime();
			long hours = TimeUnit.MILLISECONDS.toHours(diff);
			if(hours >= 2){
				zaman = true; 
			}
		} else {
			zaman = true;
		}
		
		List<String> ips = new LinkedList<>();
		for (Machine mac : machineRepository.findAll()) {
			if(!PingEngine.getPing(mac.ip)){
				ips.add(mac.ip);
				hata = true;
			}
		}

		if(zaman && hata){
			sendNotification("Ip Erişim Hatası", String.join(", ", ips)+" ip adresleri yanıt vermiyor.");
			
			lastSended = new Date();
		}
	}
	
	private void sendMail(String to, String subject, String body) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(to);
			message.setSubject(subject);
			message.setText(body);
			javaMailSender.send(message);
		} catch (Exception e) {
			 logger.error("Hata Mesaji", e);			
		}
	}
	
	private void sendNotification(String baslik, String konu){
		for (String mail : mailAdresses) {
			sendMail(mail, baslik, konu);
		}		
	}
}
