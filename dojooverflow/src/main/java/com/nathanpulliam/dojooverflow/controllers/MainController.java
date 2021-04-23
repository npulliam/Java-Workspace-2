package com.nathanpulliam.dojooverflow.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.nathanpulliam.dojooverflow.models.Question;
import com.nathanpulliam.dojooverflow.services.MainService;

@Controller
public class MainController {
	@Autowired
	private MainService mainServ;
	
	@GetMapping("")
	public String index() {
		return "index.jsp";
	}
	
	@GetMapping("/questions/new")
	public String newQuestion(@ModelAttribute("questionObj") Question emptyQ) {
		return "new_question.jsp";
	}
	@PostMapping("/questions/new")
	public String createQ(
			@Valid @ModelAttribute("questionObj") Question filledQ, BindingResult results
		) {
		if(results.hasErrors()) {
			return "new_question.jsp";
		} else {
			filledQ.setTags(mainServ.createTagsFromQ(filledQ));
			mainServ.createQuestion(filledQ);
			return "redirect:/";
		}
	}
	
}
