package com.hange.booking.auth.Specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.hange.booking.auth.dto.user.UserFilterDTO;
import com.hange.booking.auth.entity.user.User;

import jakarta.persistence.criteria.Predicate;

public class UserSpecification {

	public static Specification<User> filter(UserFilterDTO f) {
		return (root, query, cb) -> {

			List<Predicate> predicates = new ArrayList<>();

			// ===== KEYWORD (fullName OR email) =====
			if (f.getKeyword() != null && !f.getKeyword().isEmpty()) {
				String like = "%" + f.getKeyword().toLowerCase() + "%";

				predicates.add(cb.or(cb.like(cb.lower(root.get("fullName")), like),
						cb.like(cb.lower(root.get("email")), like)));
			}

			// ===== EMAIL EXACT =====
			if (f.getEmail() != null) {
				predicates.add(cb.equal(root.get("email"), f.getEmail()));
			}

			// ===== PHONE =====
			if (f.getPhone() != null) {
				predicates.add(cb.equal(root.get("phone"), f.getPhone()));
			}

			// ===== EMAIL VERIFIED =====
			if (f.getEmailVerified() != null) {
				predicates.add(cb.equal(root.get("emailVerified"), f.getEmailVerified()));
			}

			// ===== STATUS =====
			if (f.getAccountStatus() != null) {
				predicates.add(cb.equal(root.get("accountStatus"), f.getAccountStatus()));
			}

			// ===== ROLE =====
			if (f.getRoleId() != null) {
				predicates.add(cb.equal(root.get("role").get("id"), f.getRoleId()));
			}

			if (f.getFromDate() != null) {
				predicates.add(cb.greaterThanOrEqualTo(root.get("createdAt"), f.getFromDate()));
			}

			if (f.getToDate() != null) {
				predicates.add(cb.lessThanOrEqualTo(root.get("createdAt"), f.getToDate()));
			}

			predicates.add(cb.isNull(root.get("deletedAt")));

			return cb.and(predicates.toArray(new Predicate[0]));
		};
	}
}