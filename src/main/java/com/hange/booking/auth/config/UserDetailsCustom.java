package com.hange.booking.auth.config;

import java.time.LocalDateTime;
import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hange.booking.auth.entity.user.AccountStatusEnum;
import com.hange.booking.auth.exception.AppRuntimeException;
import com.hange.booking.auth.exception.ErrorCode;
import com.hange.booking.auth.service.user.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsCustom implements UserDetailsService {
	private final UserService userService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		com.hange.booking.auth.entity.user.User user = userService.getUserByEmail(email);

		if (user.getAccountStatus() != AccountStatusEnum.ACTIVE) {
			throw new AppRuntimeException(ErrorCode.USER_NOT_ACTIVE);
		}

		if (!Boolean.TRUE.equals(user.getEmailVerified())) {
			throw new AppRuntimeException(ErrorCode.EMAIL_NOT_VERIFIED);
		}
		if (user.getRole() == null) {
			throw new AppRuntimeException(ErrorCode.ROLE_NOT_FOUND);
		}

		if (user.getLockedUntil() != null && user.getLockedUntil().isAfter(LocalDateTime.now())) {

			throw new AppRuntimeException(ErrorCode.USER_LOCKED);
		}

		String role = user.getRole().getName();

		return new User(user.getEmail(), user.getPasswordHash(),
				Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role))

		);

	}

}
