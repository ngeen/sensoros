package com.shodom.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shodom.model.Machine;
import com.shodom.model.MachinePing;
import com.shodom.repository.MachineRepository;
import com.shodom.tools.PingEngine;

@RestController
public class MachinePingController {

	@Autowired
	private MachineRepository repository;

	@RequestMapping(value = "/listMachinePings", method = RequestMethod.GET)
	public ResponseEntity<?> listMachines() {

		List<Machine> machines = repository.findAll();
		List<MachinePing> macPings = new ArrayList<MachinePing>();

		for (Machine machine : machines) {
			MachinePing mp = new MachinePing();
			mp.machine = machine;
			mp.access = PingEngine.getPing(machine.ip);
			macPings.add(mp);
		}

		return new ResponseEntity<List<MachinePing>>(macPings, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/machinePing/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> machinePing(@PathVariable String id) {

		Machine machine = repository.findById(id);

		MachinePing mp = PingEngine.getMachinePing(machine);

		return new ResponseEntity<MachinePing>(mp, HttpStatus.CREATED);
	}
}
