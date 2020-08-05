package com.rodrigosan88.todos.models.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.rodrigosan88.todos.base.AbstractEntity;
import com.rodrigosan88.todos.models.enums.SeverityEnum;

@Entity
@Table(name = "todo")
public class Todo extends AbstractEntity<Long>{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "active", nullable = false)
	private Boolean active = Boolean.TRUE;
	
	@Column(name = "content", nullable = false)
	private String content;
	
	@Column(name = "created", nullable = false)
	private Date created;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "severity", nullable = false)
	private SeverityEnum severity;
	
	@ManyToOne
	@JoinColumn(name = "owner")
	private User owner;

	public Todo() {
		super();
	}

	public Todo(Long id, Boolean active, String content, Date created, SeverityEnum severity) {
		super();
		this.id = id;
		this.active = active;
		this.content = content;
		this.created = created;
		this.severity = severity;
	}

	@Override
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Boolean getActive() {
		return this.active;
	}

	@Override
	public void setActive(Boolean active) {
		
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public SeverityEnum getSeverity() {
		return severity;
	}

	public void setSeverity(SeverityEnum severity) {
		this.severity = severity;
	}
	
	public User getOwner() {
		return owner;
	}
	
	public void setOwner(User owner) {
		this.owner = owner;
	}
	
}
