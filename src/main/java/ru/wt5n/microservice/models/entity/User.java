package ru.wt5n.microservice.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name = "user")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer userId;
	@Column(unique = true)
	private String username;
	private String token;
	@Column(name = "is_enabled")
	private boolean isEnabled;
//	@Enumerated(EnumType.STRING)
//	private Role role;
}