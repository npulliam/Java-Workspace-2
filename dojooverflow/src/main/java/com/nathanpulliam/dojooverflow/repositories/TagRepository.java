package com.nathanpulliam.dojooverflow.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nathanpulliam.dojooverflow.models.Tag;

@Repository
public interface TagRepository extends CrudRepository<Tag, Long>{
	List<Tag> findAll();
	List<Tag> findBySubject(String subject);
}
