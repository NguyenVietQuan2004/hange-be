package com.hange.booking.auth.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RequestResetPasswordDTO {

	@NotBlank
	private String token;

	@NotBlank
	private String newPassword;

}