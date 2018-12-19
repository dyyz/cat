package com.example.cat.user.role;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.example.cat.user.entity.UserLogin;

@Entity
@Table(name = "tbl_role", uniqueConstraints= {@UniqueConstraint(columnNames = {"name"})})
public class Role implements Serializable {

	private static final long serialVersionUID = -385003352552887539L;
	
	private Long id;
	private String name;
	private String description;
	private Set<UserLogin> UserLogin = new HashSet<>();
	
	public Role() {
	}
	public Role(String name) {
		this.name = name;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@ManyToMany(targetEntity = UserLogin.class, mappedBy = "roles")
	public Set<UserLogin> getUserLogin() {
		return UserLogin;
	}
	public void setUserLogin(Set<UserLogin> userLogin) {
		UserLogin = userLogin;
	}
	
}
