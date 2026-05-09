
package com.hange.booking.auth.entity.permission;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hange.booking.auth.entity.role.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "permissions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SuppressWarnings("unused")
public class Permission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name; // USER_CREATE, USER_DELETE...

	private String apiPath; // /api/users/**

	private String method; // GET, POST, PUT, DELETE

	private String module; // USER, BOOKING, AUTH...

	@ManyToMany(mappedBy = "permissions")
	@JsonIgnore
	private List<Role> roles;
}