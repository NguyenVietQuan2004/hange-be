package com.hange.booking.auth.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hange.booking.auth.entity.user.RefreshToken;

import jakarta.transaction.Transactional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

	Optional<RefreshToken> findByTokenHash(String tokenHash);

	List<RefreshToken> findByUserId(Long userId);

	List<RefreshToken> findByUserIdAndIsRevokedFalse(Long userId);

	@Modifying
	@Transactional
	@Query("UPDATE RefreshToken r SET r.isRevoked = true WHERE r.user.id = :userId")
	void revokeAllByUserId(@Param("userId") Long userId);

	@Modifying
	@Transactional
	@Query("""
			    UPDATE RefreshToken t
			    SET t.isRevoked = true
			    WHERE t.user.id = :userId
			      AND t.id <> :currentTokenId
			""")
	int revokeAllExcept(@Param("userId") Long userId, @Param("currentTokenId") Long currentTokenId);

}