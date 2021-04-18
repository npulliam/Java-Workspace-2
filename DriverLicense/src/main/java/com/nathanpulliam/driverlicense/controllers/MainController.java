package com.nathanpulliam.driverlicense.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nathanpulliam.driverlicense.models.License;
import com.nathanpulliam.driverlicense.models.Person;
import com.nathanpulliam.driverlicense.services.MainService;

@Controller
public class MainController {
	@Autowired
	private MainService mainServ;
	
	@RequestMapping("/persons/new")
	public String newPerson(@ModelAttribute("personObj") Person emptyPerson) {
		return "new_person.jsp";
	}
	
	@PostMapping("/persons/create")
	public String createPerson(@ModelAttribute("personObj") Person filledPerson, BindingResult results) {
		if(results.hasErrors()) {
			return "new_person.jsp";
		} else {
			mainServ.savePerson(filledPerson);
			return "redirect:/persons/new";
		}
	}
	
	@RequestMapping("/licenses/new")
	public String newLicense(@ModelAttribute("licenseObj") License emptyLicense, Model model) {
		List<Person> personsFromDb = mainServ.allPersons();
		String[] states = {"AL", "AK", "AR", "AZ", "CA", "CO", "CT", "DE", "DC", 
				"FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", 
				"MD", "MA", "MI", "MN", "MS", "MO", "MO", "MT", "NE", "NV", "NH", 
				"NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC",
				"SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"};
		model.addAttribute("states", states);
		model.addAttribute("allPersons", personsFromDb);
		return "new_license.jsp";
	}
	@PostMapping("licenses/create")
	public String createLicense(
			@Valid @ModelAttribute("licenseObj") License filledLicense,
			BindingResult results) {
		if(results.hasErrors()) {
			return "new_license.jsp";
		} else {
			filledLicense.getExpirationDate();
			mainServ.saveLicense(filledLicense);
			return "redirect:/licenses/new";
		}
	}
}
