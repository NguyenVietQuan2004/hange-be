
package com.hange.booking.auth.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseLoginDTO {

	private String accessToken;
	private String refreshToken;

	private UserDTO userDTO;
}