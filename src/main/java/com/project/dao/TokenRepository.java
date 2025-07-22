package com.project.dao;

import com.project.object.RefreshToken;
import com.project.object.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<RefreshToken, Long> {

    // Find all valid tokens for a user (not expired or revoked)
    @Query("""
                SELECT t FROM RefreshToken t WHERE t.user = :user AND (t.expired = false AND t.revoked = false)
            """)
    List<RefreshToken> findAllValidTokensByUser(User user);

    // Lookup a specific token (usually used during refresh or access token validation)
    Optional<RefreshToken> findByToken(String token);
}
