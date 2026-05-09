package com.hange.booking.auth.service.permission;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hange.booking.auth.entity.permission.Permission;
import com.hange.booking.auth.entity.role.Role;
import com.hange.booking.auth.exception.AppRuntimeException;
import com.hange.booking.auth.exception.ErrorCode;
import com.hange.booking.auth.repository.PermissionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PermissionService {

	private final PermissionRepository permissionRepository;

	public Permission create(Permission permission) {

		if (permissionRepository.existsByName(permission.getName())) {
			throw new AppRuntimeException(ErrorCode.PERMISSION_ALREADY_EXISTS);
		}

		return permissionRepository.save(permission);
	}

	public Permission update(Long id, Permission request) {

		Permission permission = getById(id);

		permission.setName(request.getName());
		permission.setApiPath(request.getApiPath());
		permission.setMethod(request.getMethod());
		permission.setModule(request.getModule());

		return permissionRepository.save(permission);
	}

	public Permission getById(Long id) {

		return permissionRepository.findById(id)
				.orElseThrow(() -> new AppRuntimeException(ErrorCode.PERMISSION_NOT_FOUND));
	}

	public Permission getByName(String name) {

		return permissionRepository.findByName(name)
				.orElseThrow(() -> new AppRuntimeException(ErrorCode.PERMISSION_NOT_FOUND));
	}

	public List<Permission> getAll() {
		return permissionRepository.findAll();
	}

	public List<Permission> getByModule(String module) {
		return permissionRepository.findByModule(module);
	}

	public void delete(Long id) {

		Permission permission = getById(id);

		for (Role role : permission.getRoles()) {
			role.getPermissions().remove(permission);
		}
		permissionRepository.delete(permission);
	}

}