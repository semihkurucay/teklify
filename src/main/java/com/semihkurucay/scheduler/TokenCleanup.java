package com.semihkurucay.scheduler;

import com.semihkurucay.repository.RefreshTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@Transactional(readOnly = true)
@AllArgsConstructor
public class TokenCleanup {

    private final RefreshTokenRepository refreshTokenRepository;

    @Scheduled(cron = "0 0 5 * * ?")
    @Transactional
    public void deleteExpiredTokens() {
        refreshTokenRepository.deleteByExpTimeBefore(LocalDateTime.now());
    }
}
