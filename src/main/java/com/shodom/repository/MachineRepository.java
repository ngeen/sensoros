package com.shodom.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.shodom.model.Machine;

public interface MachineRepository extends MongoRepository<Machine, String> {
	
	public Machine findByName(String name);
	
	public Machine findByIp(String ip);
    
}
