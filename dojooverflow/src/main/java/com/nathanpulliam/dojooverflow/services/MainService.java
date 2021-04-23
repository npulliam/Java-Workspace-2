package com.nathanpulliam.dojooverflow.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nathanpulliam.dojooverflow.models.Question;
import com.nathanpulliam.dojooverflow.models.Tag;
import com.nathanpulliam.dojooverflow.repositories.QuestionRepository;
import com.nathanpulliam.dojooverflow.repositories.TagRepository;

@Service
public class MainService {
	@Autowired
	private QuestionRepository questionRepo;
	@Autowired
	private TagRepository tagRepo;
	
	public Question createQuestion(Question newQ) {
		return questionRepo.save(newQ);
	}
	public Tag createTag(Tag newTag) {
		return tagRepo.save(newTag);
	}
	
	public ArrayList<Tag> createTagsFromQ(Question pendingQ) {
		String[] tags = pendingQ.getTagString().split(",");
		List<Tag> qTags = new ArrayList<Tag>();
		
		for(String tagText :  tags) {
			List<Tag> tagCheck = tagRepo.findBySubject(tagText);
			if(tagCheck.isEmpty()) {
				Tag newTag = new Tag(tagText);
				tagRepo.save(newTag);
				qTags.add(newTag);
			} else {
				if qTags.
			}
		}
		return qTags;
	}
}
