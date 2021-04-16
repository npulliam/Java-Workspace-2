package com.nathanpulliam.languages.services;


import com.nathanpulliam.languages.models.Language;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nathanpulliam.languages.repositories.LanguageRepository;
@Service
public class LanguageService {
	private final LanguageRepository languageRepository;
	
	public LanguageService(LanguageRepository languageRepository) {
		this.languageRepository = languageRepository;
	}
	public List<Language> allLanguages() {
		return languageRepository.findAll();
	}
	public Language createLanguage(Language l) {
		return languageRepository.save(l);
	}
	public Language findLanguage(Long id) {
		Optional<Language> optionalLanguage = languageRepository.findById(id);
		if(optionalLanguage.isPresent()) {
			return optionalLanguage.get();
		} else {
			return null;
		}
	}
	public Language updateLanguage(Language updateLang) {
		Optional<Language> optionalLanguage = languageRepository.findById(updateLang.getId());
		if(optionalLanguage.isPresent()) {
			return languageRepository.save(updateLang);
		} else {
			return null;
		}
	}
	public void deleteLanguage(Long id) {
		languageRepository.deleteById(id);
	}
}
