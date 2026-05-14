package com.hange.booking.auth.Specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.hange.booking.auth.dto.permission.PermissionFilterDTO;
import com.hange.booking.auth.entity.permission.Permission;

import jakarta.persistence.criteria.Predicate;

public class PermissionSpecification {

	public static Specification<Permission> filter(PermissionFilterDTO f) {
		return (root, query, cb) -> {

			List<Predicate> predicates = new ArrayList<>();

			// ===== KEYWORD SEARCH =====
			if (f.getKeyword() != null && !f.getKeyword().isEmpty()) {
				String like = "%" + f.getKeyword().toLowerCase() + "%";

				predicates.add(cb.or(cb.like(cb.lower(root.get("name")), like),
						cb.like(cb.lower(root.get("apiPath")), like), cb.like(cb.lower(root.get("module")), like)));
			}

			// ===== EXACT FIELDS =====
			if (f.getName() != null) {
				predicates.add(cb.equal(root.get("name"), f.getName()));
			}

			if (f.getApiPath() != null) {
				predicates.add(cb.equal(root.get("apiPath"), f.getApiPath()));
			}

			if (f.getMethod() != null) {
				predicates.add(cb.equal(root.get("method"), f.getMethod()));
			}

			if (f.getModule() != null) {
				predicates.add(cb.equal(root.get("module"), f.getModule()));
			}

			return cb.and(predicates.toArray(new Predicate[0]));
		};
	}
}