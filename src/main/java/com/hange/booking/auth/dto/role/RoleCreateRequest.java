package com.hange.booking.auth.dto.role;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RoleCreateRequest {

	@NotBlank(message = "Role name is required")
	private String name;

	private String description;

	private List<Long> permissionIds;
}