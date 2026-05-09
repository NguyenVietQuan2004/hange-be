package com.hange.booking.auth.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestUpdateUserDTO {

	private String fullName;
	private String phone;
	private String avatarUrl;
	private String address;
}