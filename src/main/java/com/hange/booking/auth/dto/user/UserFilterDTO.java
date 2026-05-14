package com.hange.booking.auth.dto.user;

import java.time.LocalDateTime;

import com.hange.booking.auth.entity.user.AccountStatusEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserFilterDTO {

	private String keyword; // search fullName/email
	private String email;
	private String phone;

	private Boolean emailVerified;
	private AccountStatusEnum accountStatus;

	private Long roleId;

	private LocalDateTime fromDate;
	private LocalDateTime toDate;
}