package com.hange.booking.auth.utils.FormatResponse;

public class ApiResponseUtil {

	public static ApiResponseFormat success(Object data, int statusCode) {
		return ApiResponseFormat.builder().statusCode(statusCode).message("CALL API SUCCESS").error(null).data(data)
				.build();
	}

	public static ApiResponseFormat error(Object message, int statusCode, String errorCode) {
		return ApiResponseFormat.builder().statusCode(statusCode).errorCode(errorCode).message("CALL API FAIL")
				.error(message).data(null).build();
	}
}