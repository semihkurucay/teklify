package com.semihkurucay.repository;

import com.semihkurucay.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    void deleteByExpTimeBefore(LocalDateTime expTimeBefore);

    Optional<RefreshToken> findByRefreshToken(String refreshToken);

    void deleteByRefreshToken(String refreshToken);

    void deleteByUser_IdAndExpTimeBefore(Long userId, LocalDateTime expTimeBefore);
}
