package com.hange.booking.auth.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hange.booking.auth.entity.permission.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

	Optional<Permission> findByName(String name);

	Optional<Permission> findByApiPathAndMethod(String apiPath, String method);

	boolean existsByName(String name);

	boolean existsByApiPathAndMethod(String apiPath, String method);

	List<Permission> findByModule(String module);

	List<Permission> findByMethod(String method);

	List<Permission> findByApiPathContaining(String apiPath);
}