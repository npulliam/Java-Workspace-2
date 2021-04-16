package com.nathanpulliam.languages.repositories;

import com.nathanpulliam.languages.models.Language;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LanguageRepository extends CrudRepository<Language, Long>{
 // this method retrieves all the books from the database
 List<Language> findAll();
 // this method finds languages with descriptions containing the search string
 List<Language> findByNameContaining(String search);
 // this method counts how many language names contain a certain string
 Long countByNameContaining(String search);
 // this method deletes a language that starts with a specific name
 Long deleteByNameStartingWith(String search);
}

