package com.nathanpulliam.dojooverflow.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;



@Entity
@Table(name="questions")
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message="Question must be provided")
	private String question;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "questions_tags",
		joinColumns = @JoinColumn(name="question_id"),
		inverseJoinColumns = @JoinColumn(name="tag_id")
	)
	private List<Tag> tags;
	@Column(updatable=false)
	private Date createdAt;
	private Date updatedAt;
	
	@OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
	private List<Answer> answers;
	
	@Transient
	private String tagString;
	

	public String getTagString() {
		return tagString;
	}

	public void setTagString(String tagString) {
		this.tagString = tagString;
	}

	public Question() {	
	}
	
	public Question(String question) {
		this.question = question;
	}
	
	
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<Answer> getAnswers() {
		return answers;
	}
	
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	
	@PrePersist
  	protected void onCreate(){
    	this.createdAt = new Date();
    }
  	@PostPersist
  	protected void onUpdate(){
  	    this.updatedAt = new Date();
  	}
	
}
