package com.hange.booking.auth.utils.FormatResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("unused")
@Builder
public class ApiResponseFormat {

	private int statusCode;
	private Object message;
	private Object error;
	private Object errorCode;
	private Object data;
}