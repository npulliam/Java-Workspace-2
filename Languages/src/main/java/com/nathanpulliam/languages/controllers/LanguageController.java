package com.nathanpulliam.languages.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nathanpulliam.languages.models.Language;
import com.nathanpulliam.languages.services.LanguageService;

@Controller
@RequestMapping("/languages")
public class LanguageController {
	@Autowired
	private LanguageService languageServ;
	
	@RequestMapping("")
	public String index(@ModelAttribute("langObj") Language emptyLanguage, Model model) {
		List<Language> languagesFromDb = languageServ.allLanguages();
		model.addAttribute("allLanguages", languagesFromDb);
		return "index.jsp";
	}
	@PostMapping("/new")
	public String newLanguage(@Valid @ModelAttribute("langObj") Language filledLanguage, BindingResult results, Model model) {
		List<Language> languagesFromDb = languageServ.allLanguages();
		model.addAttribute("allLanguages", languagesFromDb);
		if(results.hasErrors()) {
			return "index.jsp";
		} else {
			languageServ.createLanguage(filledLanguage);
			System.out.println("New Language created");
			return "redirect:/languages";	
		}
	}
	@RequestMapping("/{id}/delete")
	public String deleteLang(@PathVariable("id") Long id) {
		languageServ.deleteLanguage(id);
		return "redirect:/languages";
	}
	@RequestMapping("/{id}/edit")
	public String editLanguage(@PathVariable("id") Long id, Model model) {
		Language editLang = languageServ.findLanguage(id);
		model.addAttribute("langObj", editLang);
		return "edit.jsp";
	}
	@RequestMapping(value="/{id}/update", method=RequestMethod.PUT)
	public String updateLanguage(
			@Valid @ModelAttribute("langObj") Language updatedLanguage, 
			BindingResult results) {
		if(results.hasErrors()) {
			return "edit.jsp";
		} else {
			languageServ.updateLanguage(updatedLanguage);
			return "redirect:/languages";
		}
	}
	@RequestMapping("/{id}")
	public String showLanguage(@PathVariable("id") Long id, Model model) {
		Language showLang = languageServ.findLanguage(id);
		model.addAttribute("showLang", showLang);
		return "show.jsp";
	}
	
}
