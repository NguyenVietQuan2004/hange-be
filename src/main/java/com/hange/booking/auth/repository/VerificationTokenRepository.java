package com.hange.booking.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hange.booking.auth.entity.user.TokenType;
import com.hange.booking.auth.entity.user.VerificationToken;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

	Optional<VerificationToken> findByTokenHash(String tokenHash);

	Optional<VerificationToken> findByTokenHashAndType(String tokenHash, TokenType type);

	Optional<VerificationToken> findByTokenHashAndUsedFalse(String tokenHash);
}