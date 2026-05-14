package com.hange.booking.auth.dto.permission;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissionFilterDTO {
	private String keyword;
	private String name;
	private String apiPath;
	private String method;
	private String module;
}
