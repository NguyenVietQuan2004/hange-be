package com.hange.booking.auth.dto.user;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class RequestForgotPasswordDTO {
	@Email
	private String email;
}