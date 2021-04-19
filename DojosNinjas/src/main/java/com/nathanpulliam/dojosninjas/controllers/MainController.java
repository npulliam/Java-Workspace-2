package com.nathanpulliam.dojosninjas.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.nathanpulliam.dojosninjas.models.Dojo;
import com.nathanpulliam.dojosninjas.models.Ninja;
import com.nathanpulliam.dojosninjas.services.MainService;

@Controller
public class MainController {
	public final MainService mainServ;
	
	public MainController(MainService mainServ) {
		this.mainServ = mainServ;
	}
	
	@GetMapping("")
	public String index() {
		return "redirect:/dojos/new";
	}
	@GetMapping("/dojos/new")
	public String newDojo(@ModelAttribute("dojoObj") Dojo emptyDojo) {
		return "new_dojo.jsp";
	}
	@PostMapping("/dojos/new")
	public String createDojo(@Valid @ModelAttribute("dojoObj") Dojo filledDojo, BindingResult results) {
		if(results.hasErrors()) {
			return "new_dojo.jsp";
		} else {
			mainServ.createDojo(filledDojo);
			return "redirect:/dojos/new";
		}
	}
	@GetMapping("/ninjas/new")
	public String newNinja(@ModelAttribute("ninjaObj") Ninja emptyNinja, Model model) {
		List<Dojo> dojosFromDb = mainServ.getAllDojos();
		model.addAttribute("allDojos", dojosFromDb);
		return "new_ninja.jsp";
	}
	@PostMapping("ninjas/new")
	public String createNinja(@Valid @ModelAttribute("ninjaObj") Ninja filledNinja, BindingResult results) {
		if(results.hasErrors()) {
			return "new_ninja.jsp";
		} else {
			mainServ.createNinja(filledNinja);
			return "redirect:/ninjas/new";
		}
	}
	@GetMapping("/dojos/{id}")
	public String showDojo(@PathVariable("id") Long id, Model model) {
		Dojo dojo = mainServ.getDojo(id);
		model.addAttribute("dojo", dojo);
		return "show_dojo.jsp";
	}
}
