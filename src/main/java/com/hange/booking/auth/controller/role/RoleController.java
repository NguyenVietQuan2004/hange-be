package com.hange.booking.auth.controller.role;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hange.booking.auth.dto.role.RoleCreateRequest;
import com.hange.booking.auth.entity.role.Role;
import com.hange.booking.auth.service.role.RoleService;
import com.hange.booking.auth.utils.FormatResponse.ApiResponseFormat;
import com.hange.booking.auth.utils.FormatResponse.ApiResponseUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RoleController {

	private final RoleService roleService;

	@PostMapping
	public ResponseEntity<ApiResponseFormat> create(@Valid @RequestBody RoleCreateRequest request) {
		return ResponseEntity.ok(ApiResponseUtil.success(roleService.create(request), HttpStatus.OK.value()));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponseFormat> updateRole(@PathVariable("id") Long id,
			@Valid @RequestBody RoleCreateRequest request) {
		return ResponseEntity.ok(ApiResponseUtil.success(roleService.updateRole(id, request), HttpStatus.OK.value()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponseFormat> getById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(ApiResponseUtil.success(roleService.getById(id), HttpStatus.OK.value()));
	}

	@GetMapping
	public ResponseEntity<ApiResponseFormat> getAll() {
		List<Role> roles = roleService.getAll();
		return ResponseEntity.ok(ApiResponseUtil.success(roles, HttpStatus.OK.value()));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponseFormat> delete(@PathVariable("id") Long id) {
		roleService.delete(id);
		return ResponseEntity.ok(ApiResponseUtil.success("Delete role success", HttpStatus.OK.value()));
	}

}