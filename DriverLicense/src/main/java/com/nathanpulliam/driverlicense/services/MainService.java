package com.nathanpulliam.driverlicense.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nathanpulliam.driverlicense.models.License;
import com.nathanpulliam.driverlicense.models.Person;
import com.nathanpulliam.driverlicense.repositories.LicenseRepository;
import com.nathanpulliam.driverlicense.repositories.PersonRepository;

@Service
public class MainService {
	public final PersonRepository personRepo;
	public final LicenseRepository licenseRepo;
	
	public MainService(PersonRepository personRepo, LicenseRepository licenseRepo) {
		this.personRepo = personRepo;
		this.licenseRepo = licenseRepo;
	}
	
	public List<Person> allPersons() {
		return personRepo.findAll();
	}
	public List<License> allLicenses() {
		return licenseRepo.findAll();
	}
	public Person findPerson(Long id) {
		return personRepo.findById(id).orElse(null);
	}
	public Person savePerson(Person person) {
		return personRepo.save(person);
	}
	public License saveLicense(License license) {
		return licenseRepo.save(license);
	}
}
