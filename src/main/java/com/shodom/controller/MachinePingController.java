package com.shodom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shodom.model.MachinePing;
import com.shodom.repository.MachinePingRepository;

@RestController
public class MachinePingController {

	@Autowired
	private MachinePingRepository repository;
	
	@RequestMapping(value = "/listMachinePings", method = RequestMethod.GET)
	public ResponseEntity<?> listMachines() {
		
		return new ResponseEntity<List<MachinePing>>(repository.findAll(), HttpStatus.CREATED);
    }
}
