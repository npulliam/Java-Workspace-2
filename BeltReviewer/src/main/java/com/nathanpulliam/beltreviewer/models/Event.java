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
import javax.persistence.ManyToOne;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="events")
public class Event {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	@NotEmpty(message = "Event name must not be empty")
    private String name;
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	@FutureOrPresent(message = "Upcoming events only (date must be in future)")
    private Date eventDate;
	@NotEmpty(message = "Location must be provided")
    private String location;
	@Size(min=2, max=2, message= "State must be in abbreviated 2 character format")
    private String state;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "host_id")
	private User host;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "users_events",
		joinColumns = @JoinColumn(name = "event_id"),
		inverseJoinColumns = @JoinColumn(name = "user_id")
	)
	private List<User> joinedUsers;
	
	@Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    
    public Event() {
    }
    
    public Event(String name, Date eventDate, String location, String state) {
    	this.name = name;
    	this.eventDate = eventDate;
    	this.location = location;
    	this.state = state;
    }
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
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

	public User getHost() {
		return host;
	}

	public void setHost(User host) {
		this.host = host;
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

	public List<User> getJoinedUsers() {
		return joinedUsers;
	}
	
	public void setJoinedUsers(List<User> joinedUsers) {
		this.joinedUsers = joinedUsers;
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
