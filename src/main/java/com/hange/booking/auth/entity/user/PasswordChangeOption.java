package com.hange.booking.auth.entity.user;

public enum PasswordChangeOption {
	REVOKE_ALL, // logout all devices
	KEEP_CURRENT, // giữ session hiện tại
	KEEP_ALL // giữ tất cả (không khuyên dùng)
}