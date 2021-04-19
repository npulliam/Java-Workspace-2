package com.nathanpulliam.dojosninjas.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nathanpulliam.dojosninjas.models.Dojo;
import com.nathanpulliam.dojosninjas.models.Ninja;
import com.nathanpulliam.dojosninjas.repositories.DojoRepository;
import com.nathanpulliam.dojosninjas.repositories.NinjaRepository;

@Service
public class MainService {
	private final DojoRepository dojoRepo;
	private final NinjaRepository ninjaRepo;
	
	public MainService(DojoRepository dojoRepo, NinjaRepository ninjaRepo) {
		this.dojoRepo = dojoRepo;
		this.ninjaRepo = ninjaRepo;
	}
	
	// CRUD for Dojo
	public Dojo createDojo(Dojo dojo) {
		return dojoRepo.save(dojo);
	}
	
	public Dojo getDojo(Long id) {
		return dojoRepo.findById(id).orElse(null);
	}
	
	public List<Dojo> getAllDojos() {
		return dojoRepo.findAll();
	}
	
	// CRUD for Ninja
	public Ninja createNinja(Ninja ninja) {
		return ninjaRepo.save(ninja);
	}
	
	public List<Ninja> getAllNinjas() {
		return ninjaRepo.findAll();
		
	}
	
	public Ninja getNinja(Long id) {
		return ninjaRepo.findById(id).orElse(null);
	}
}
