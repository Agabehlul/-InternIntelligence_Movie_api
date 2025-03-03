package com.aga.intern_intelligence_movie_api.repository;

import com.aga.intern_intelligence_movie_api.entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Integer> {
    boolean existsByToken(String token);
}
