//
//
//package com.hange.booking.auth.exception;
//
//import org.springframework.http.HttpStatus;
//
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//
//@Getter
//@RequiredArgsConstructor
//public enum ErrorCode {
//
//	// USER
//	USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found"), USER_NOT_ACTIVE(HttpStatus.FORBIDDEN, "User is not active"),
//	USER_LOCKED(HttpStatus.LOCKED, "User is locked due to multiple failed attempts"),
//	ACCOUNT_DISABLED(HttpStatus.FORBIDDEN, "Account is disabled"),
//
//	// EMAIL
//	EMAIL_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "Email already exists"),
//	EMAIL_NOT_VERIFIED(HttpStatus.FORBIDDEN, "Email is not verified"),
//	EMAIL_ALREADY_VERIFIED(HttpStatus.BAD_REQUEST, "Email already verified"),
//
//	// AUTH
//	INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "Invalid credentials"),
//	WRONG_PASSWORD(HttpStatus.BAD_REQUEST, "Wrong password"),
//	TOO_MANY_REQUESTS(HttpStatus.TOO_MANY_REQUESTS, "Too many requests"),
//
//	// ROLE
//	ROLE_NOT_FOUND(HttpStatus.NOT_FOUND, "Role not found"),
//
//	// TOKEN (GENERAL)
//	INVALID_TOKEN(HttpStatus.BAD_REQUEST, "Invalid token"), TOKEN_EXPIRED(HttpStatus.BAD_REQUEST, "Token expired"),
//	TOKEN_REVOKED(HttpStatus.UNAUTHORIZED, "Token revoked"), TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, "Token not found"),
//	TOKEN_ALREADY_REVOKED(HttpStatus.CONFLICT, "Token already revoked"),
//	INVALID_TOKEN_VERSION(HttpStatus.BAD_REQUEST, "Invalid token version"),
//	USED_TOKEN(HttpStatus.BAD_REQUEST, "Token already used"),
//
//	// REFRESH TOKEN
//	INVALID_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "Invalid refresh token"),
//	REFRESH_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "Refresh token expired"),
//	REFRESH_TOKEN_REVOKED(HttpStatus.UNAUTHORIZED, "Refresh token revoked"),
//	REFRESH_TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, "Refresh token not found"),
//	REFRESH_TOKEN_ALREADY_REVOKED(HttpStatus.CONFLICT, "Refresh token already revoked"),
//
//	// FILE
//	FILE_EMPTY(HttpStatus.BAD_REQUEST, "File is empty"), INVALID_FILE_NAME(HttpStatus.BAD_REQUEST, "Invalid file name"),
//	UNSUPPORTED_EXTENSION(HttpStatus.BAD_REQUEST, "Unsupported file extension"),
//	UNSUPPORTED_MIME_TYPE(HttpStatus.BAD_REQUEST, "Unsupported file type"),
//	FILE_TOO_LARGE(HttpStatus.CONTENT_TOO_LARGE, "File size exceeds limit"),
//	FILE_UPLOAD_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "File upload failed"),
//	MISSING_REQUIRED_PARAMS(HttpStatus.BAD_REQUEST, "Missing required parameters"),
//	FILE_NOT_FOUND(HttpStatus.NOT_FOUND, "File not found"),
//	// FOLDER
//	CREATE_FOLDER_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "Cannot create upload folder"),
//	FOLDER_INVALID(HttpStatus.BAD_REQUEST, "Invalid folder path"),
//	FOLDER_PERMISSION_DENIED(HttpStatus.FORBIDDEN, "No permission to access folder"),
//
//	// SECURITY FILE UPLOAD
//	FILE_NAME_PATH_TRAVERSAL(HttpStatus.BAD_REQUEST, "File name contains invalid path sequence"),
//	FILE_SUSPICIOUS_CONTENT(HttpStatus.BAD_REQUEST, "Suspicious file content detected"),
//
//	UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "Unauthorized"),
//
//	// PERMISSION
//	PERMISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "Permission not found"),
//	PERMISSION_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "Permission already exists"),
//	PERMISSION_ALREADY_ASSIGNED(HttpStatus.BAD_REQUEST, "Permission already assigned"),
//	PERMISSION_NOT_ASSIGNED(HttpStatus.BAD_REQUEST, "Permission not assigned"),
//	INVALID_PERMISSION(HttpStatus.BAD_REQUEST, "Invalid permission"),
//
//	// ROLE PERMISSION
//	ROLE_PERMISSION_CONFLICT(HttpStatus.BAD_REQUEST, "Role permission conflict"),
//	ROLE_HAS_NO_PERMISSIONS(HttpStatus.BAD_REQUEST, "Role has no permissions"),
//	// ROLE
//	ROLE_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "Role already exists"),
//	INVALID_ROLE(HttpStatus.BAD_REQUEST, "Invalid role"),
//	ROLE_IN_USE(HttpStatus.BAD_REQUEST, "Role is currently assigned to users"),
//	ROLE_CANNOT_BE_DELETED(HttpStatus.BAD_REQUEST, "This role cannot be deleted"),
//	ROLE_ALREADY_ASSIGNED(HttpStatus.BAD_REQUEST, "Role already assigned"),
//	ROLE_NOT_ASSIGNED(HttpStatus.BAD_REQUEST, "Role not assigned"),
//	INVALID_ROLE_NAME(HttpStatus.BAD_REQUEST, "Invalid role name");
//
//	private final HttpStatus status;
//	private final String message;
//}

package com.hange.booking.auth.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

	/*
	 * ================================================= USER
	 * =================================================
	 */
	USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found"), USER_NOT_ACTIVE(HttpStatus.FORBIDDEN, "User is not active"),
	USER_LOCKED(HttpStatus.LOCKED, "User is locked due to multiple failed attempts"),
	ACCOUNT_DISABLED(HttpStatus.FORBIDDEN, "Account is disabled"),

	/*
	 * ================================================= EMAIL
	 * =================================================
	 */
	EMAIL_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "Email already exists"),
	EMAIL_NOT_VERIFIED(HttpStatus.FORBIDDEN, "Email is not verified"),
	EMAIL_ALREADY_VERIFIED(HttpStatus.BAD_REQUEST, "Email already verified"),

	/*
	 * ================================================= AUTH
	 * =================================================
	 */
	INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "Invalid credentials"),
	WRONG_PASSWORD(HttpStatus.BAD_REQUEST, "Wrong password"),
	TOO_MANY_REQUESTS(HttpStatus.TOO_MANY_REQUESTS, "Too many requests"),
	UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "Unauthorized"),

	/*
	 * ================================================= ROLE (GENERAL)
	 * =================================================
	 */
	ROLE_NOT_FOUND(HttpStatus.NOT_FOUND, "Role not found"),
	ROLE_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "Role already exists"),
	INVALID_ROLE(HttpStatus.BAD_REQUEST, "Invalid role"),
	INVALID_ROLE_NAME(HttpStatus.BAD_REQUEST, "Invalid role name"),
	ROLE_IN_USE(HttpStatus.BAD_REQUEST, "Role is currently assigned to users"),
	ROLE_CANNOT_BE_DELETED(HttpStatus.BAD_REQUEST, "This role cannot be deleted"),
	ROLE_ALREADY_ASSIGNED(HttpStatus.BAD_REQUEST, "Role already assigned"),
	ROLE_NOT_ASSIGNED(HttpStatus.BAD_REQUEST, "Role not assigned"),

	/*
	 * ================================================= PERMISSION
	 * =================================================
	 */
	PERMISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "Permission not found"),
	PERMISSION_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "Permission already exists"),
	PERMISSION_ALREADY_ASSIGNED(HttpStatus.BAD_REQUEST, "Permission already assigned"),
	PERMISSION_NOT_ASSIGNED(HttpStatus.BAD_REQUEST, "Permission not assigned"),
	INVALID_PERMISSION(HttpStatus.BAD_REQUEST, "Invalid permission"),

	/*
	 * ================================================= ROLE - PERMISSION RELATION
	 * =================================================
	 */
	ROLE_PERMISSION_CONFLICT(HttpStatus.BAD_REQUEST, "Role permission conflict"),
	ROLE_HAS_NO_PERMISSIONS(HttpStatus.BAD_REQUEST, "Role has no permissions"),

	/*
	 * ================================================= TOKEN (GENERAL)
	 * =================================================
	 */
	INVALID_TOKEN(HttpStatus.BAD_REQUEST, "Invalid token"), TOKEN_EXPIRED(HttpStatus.BAD_REQUEST, "Token expired"),
	TOKEN_REVOKED(HttpStatus.UNAUTHORIZED, "Token revoked"), TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, "Token not found"),
	TOKEN_ALREADY_REVOKED(HttpStatus.CONFLICT, "Token already revoked"),
	INVALID_TOKEN_VERSION(HttpStatus.BAD_REQUEST, "Invalid token version"),
	USED_TOKEN(HttpStatus.BAD_REQUEST, "Token already used"),

	/*
	 * ================================================= REFRESH TOKEN
	 * =================================================
	 */
	INVALID_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "Invalid refresh token"),
	REFRESH_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "Refresh token expired"),
	REFRESH_TOKEN_REVOKED(HttpStatus.UNAUTHORIZED, "Refresh token revoked"),
	REFRESH_TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, "Refresh token not found"),
	REFRESH_TOKEN_ALREADY_REVOKED(HttpStatus.CONFLICT, "Refresh token already revoked"),

	/*
	 * ================================================= FILE
	 * =================================================
	 */
	FILE_EMPTY(HttpStatus.BAD_REQUEST, "File is empty"), INVALID_FILE_NAME(HttpStatus.BAD_REQUEST, "Invalid file name"),
	UNSUPPORTED_EXTENSION(HttpStatus.BAD_REQUEST, "Unsupported file extension"),
	UNSUPPORTED_MIME_TYPE(HttpStatus.BAD_REQUEST, "Unsupported file type"),
	FILE_TOO_LARGE(HttpStatus.CONTENT_TOO_LARGE, "File size exceeds limit"),
	FILE_UPLOAD_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "File upload failed"),
	MISSING_REQUIRED_PARAMS(HttpStatus.BAD_REQUEST, "Missing required parameters"),
	FILE_NOT_FOUND(HttpStatus.NOT_FOUND, "File not found"),

	/*
	 * ================================================= FOLDER
	 * =================================================
	 */
	CREATE_FOLDER_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "Cannot create upload folder"),
	FOLDER_INVALID(HttpStatus.BAD_REQUEST, "Invalid folder path"),
	FOLDER_PERMISSION_DENIED(HttpStatus.FORBIDDEN, "No permission to access folder"),

	/*
	 * ================================================= SECURITY FILE UPLOAD
	 * =================================================
	 */
	FILE_NAME_PATH_TRAVERSAL(HttpStatus.BAD_REQUEST, "File name contains invalid path sequence"),
	FILE_SUSPICIOUS_CONTENT(HttpStatus.BAD_REQUEST, "Suspicious file content detected");

	private final HttpStatus status;
	private final String message;
}