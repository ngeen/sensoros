package com.shodom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shodom.model.Machine;
import com.shodom.repository.MachineRepository;

@RestController
public class MachineController {
	
	@Autowired
	private MachineRepository repository;
	
	@RequestMapping(value = "/addMachine", method = RequestMethod.POST)
	public ResponseEntity<?> addMachine(@RequestBody Machine mac) {

		Machine machine = repository.findByIp(mac.ip);
		if(machine != null){
			return new ResponseEntity<String>("Makine daha önceden kaydedilmiş.", HttpStatus.NOT_FOUND);
		}
		
		repository.save(mac);
		
		return new ResponseEntity<String>(mac.name+" Makine kaydı başarılı", HttpStatus.CREATED);
    }
	
	@RequestMapping(value = "/updateMachine", method = RequestMethod.POST)
	public ResponseEntity<?> updateMachine(@RequestBody Machine mac) {

		Machine machine = repository.findByIp(mac.ip);
		if(machine == null){
			return new ResponseEntity<String>("Böyle Bir Makine Bulunamadı", HttpStatus.NOT_FOUND);
		}
		
		machine.name = mac.name;
		
		repository.save(machine);
		
		return new ResponseEntity<String>(mac.name+" Makinesinde Düzenleme yapılmıştır.", HttpStatus.CREATED);
    }
	
	@RequestMapping(value = "/deleteMachine", method = RequestMethod.POST)
	public ResponseEntity<?> deleteMachine(@RequestBody Machine mac) {

		Machine machine = repository.findByIp(mac.ip);
		if(machine == null){
			return new ResponseEntity<String>("Böyle Bir Makine Bulunamadı", HttpStatus.NOT_FOUND);
		}
		
		repository.delete(machine);
		
		return new ResponseEntity<String>(mac.name+" Makinesin Silindi.", HttpStatus.CREATED);
    }

}
