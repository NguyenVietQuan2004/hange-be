package com.hange.booking.auth.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import com.hange.booking.auth.utils.FormatResponse.ApiResponseFormat;
import com.hange.booking.auth.utils.FormatResponse.ApiResponseUtil;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(AppRuntimeException.class)
	public ResponseEntity<ApiResponseFormat> handleAppException(AppRuntimeException ex) {

		ErrorCode code = ex.getErrorCode();

		return ResponseEntity.status(code.getStatus())
				.body(ApiResponseUtil.error(code.getMessage(), code.getStatus().value(), code.name()));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponseFormat> handleValidationException(MethodArgumentNotValidException ex) {

		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errors.put(error.getField(), error.getDefaultMessage());
		});

		return ResponseEntity.badRequest().body(ApiResponseUtil.error(errors, 400, "VALIDATION_ERROR"));
	}

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<ApiResponseFormat> handleMaxSizeException(MaxUploadSizeExceededException ex) {

		return ResponseEntity.badRequest().body(
				ApiResponseUtil.error("File upload vượt quá dung lượng cho phép (tối đa 50MB)", 400, "FILE_TOO_LARGE"));
	}

//
//	// Xử lý tất cả các exception khác
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<ApiResponseFormat> handleAllExceptions(Exception ex) {
//		int status = HttpStatus.INTERNAL_SERVER_ERROR.value();
//		String message = ex.getMessage();
//		System.out.println("Error at fallback @ExceptionHandler: " + message);
//		return ResponseEntity.internalServerError().body(ApiResponseUtil.error("Internal server error", status));
//	}
}