package com.nathanpulliam.beltreviewer.models;

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
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message = "Email must not be empty")
    private String email;
    @NotEmpty(message = "First Name must be provided")
    private String firstName;
    @NotEmpty(message = "Last Name must be provided")
    private String lastName;
    @NotEmpty(message = "Location must be provided")
    private String location;
    @Size(min=2, max=2, message= "State must be in abbreviated 2 character format")
    private String state;
    @NotEmpty(message = "Password must not be empty")
    private String password;
    
    @Transient
    private String passwordConfirmation;
    @Transient
    private String duplicate;
    
    @OneToMany(mappedBy = "host", fetch = FetchType.LAZY)
    private List<Event> eventsHosted;

    @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "users_events",
		joinColumns = @JoinColumn(name = "user_id"),
		inverseJoinColumns = @JoinColumn(name = "event_id")
	)
    private List<Event> joinedEvents;
    
    @Column(updatable=false)
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private Date updatedAt;
    
    public User() {
    }
    
    public User(String email, String firstName, String lastName, String location, String state) {
    	this.email = email;
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.location = location;
    	this.state = state;
    }
    
    
    // other getters and setters removed for brevity
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PostPersist
    protected void onUpdate(){
        this.updatedAt = new Date();
    }

	public Long getId() {
		return id;
	}

	public List<Event> getJoinedEvents() {
		return joinedEvents;
	}

	public void setJoinedEvents(List<Event> joinedEvents) {
		this.joinedEvents = joinedEvents;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
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

	public String getDuplicate() {
		return duplicate;
	}

	public void setDuplicate(String duplicate) {
		this.duplicate = duplicate;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<Event> getEventsHosted() {
		return eventsHosted;
	}

	public void setEventsHosted(List<Event> eventsHosted) {
		this.eventsHosted = eventsHosted;
	}
}

