package com.hange.booking.auth.service.user;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import com.hange.booking.auth.dto.user.RequestChangePasswordDTO;
import com.hange.booking.auth.dto.user.RequestUpdateUserDTO;
import com.hange.booking.auth.entity.role.Role;
import com.hange.booking.auth.entity.user.AccountStatusEnum;
import com.hange.booking.auth.entity.user.PasswordChangeOption;
import com.hange.booking.auth.entity.user.User;
import com.hange.booking.auth.exception.AppRuntimeException;
import com.hange.booking.auth.exception.ErrorCode;
import com.hange.booking.auth.repository.UserRepository;
import com.hange.booking.auth.service.file.FileService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	@Value("${LOCK_TIME}")
	private Long lockTimeSeconds;
	@Value("${MAX_FAILED_LOGIN}")
	private Integer maxFailedLogin;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final TokenService tokenService;
	private final FileService fileService;

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User getUserById(Long id) {
		return userRepository.findById(id).orElseThrow(() -> new AppRuntimeException(ErrorCode.USER_NOT_FOUND));
	}

	public User getUserByEmail(String email) {

		return userRepository.findByEmail(email).orElseThrow(() -> new AppRuntimeException(ErrorCode.USER_NOT_FOUND));
	}

	public void updateLoginSuccess(User user) {
		user.setFailedLoginCount(0);
		user.setLastLoginAt(LocalDateTime.now());
		userRepository.save(user);
	}

	public void increaseFailedLogin(String email) {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new AppRuntimeException(ErrorCode.USER_NOT_FOUND));

		int count = user.getFailedLoginCount() == null ? 0 : user.getFailedLoginCount();
		user.setFailedLoginCount(count + 1);

		if (user.getFailedLoginCount() >= maxFailedLogin) {
			user.setLockedUntil(LocalDateTime.now().plusSeconds(lockTimeSeconds));
		}
		userRepository.save(user);
	}

	public void changePassword(String email, RequestChangePasswordDTO request) {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new AppRuntimeException(ErrorCode.USER_NOT_FOUND));

		if (!passwordEncoder.matches(request.getOldPassword(), user.getPasswordHash())) {
			throw new AppRuntimeException(ErrorCode.WRONG_PASSWORD);
		}

		updatePassword(user, request.getNewPassword());
		PasswordChangeOption option = request.getOption() == null ? PasswordChangeOption.REVOKE_ALL
				: request.getOption();
		tokenService.handleSessionAfterPasswordChange(user, option, request.getRefreshToken());
	}

	public void validateTokenVersion(Jwt jwt) {
		String email = jwt.getSubject();
		Number ver = jwt.getClaim("ver");
		int tokenVersionInToken = ver.intValue();
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new AppRuntimeException(ErrorCode.USER_NOT_FOUND));

		if (!user.getTokenVersion().equals(tokenVersionInToken)) {
			throw new AppRuntimeException(ErrorCode.INVALID_TOKEN_VERSION);
		}
	}

	public void updatePassword(User user, String newPassword) {
		user.setPasswordHash(passwordEncoder.encode(newPassword));
		user.setTokenVersion(user.getTokenVersion() == null ? 1 : user.getTokenVersion() + 1);
		userRepository.save(user);
	}

	public Boolean isEmailExisted(String email) {
		return userRepository.existsByEmail(email);
	}

	public User createUser(String email, String password, Role role) {
		User user = new User();
		user.setEmail(email);
		user.setPasswordHash(passwordEncoder.encode(password));
		user.setRole(role);
		return userRepository.save(user);
	}

	public void activateFromVerificationToken(User user) {
		user.setEmailVerified(true);
		user.setAccountStatus(AccountStatusEnum.ACTIVE);
		userRepository.save(user);
	}

	public User updateUserProfile(String email, RequestUpdateUserDTO request) {

		User user = getUserByEmail(email);

		if (request.getFullName() != null) {
			user.setFullName(request.getFullName());
		}

		if (request.getPhone() != null) {
			user.setPhone(request.getPhone());
		}

		if (request.getAvatarUrl() != null) {
			user.setAvatarUrl(request.getAvatarUrl());
		}

		if (request.getAddress() != null) {
			user.setAddress(request.getAddress());
		}

		return userRepository.save(user);
	}

}