package com.ksprogramming.equipment.repository;

import com.ksprogramming.equipment.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TokenRepository extends JpaRepository<Token, Long> {
    @Query("FROM Token t where t.value = :token")
    Token findByToken(String token);
}
