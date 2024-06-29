package com.ksprogramming.equipment.repository;

import com.ksprogramming.equipment.entities.UserAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserAuthorityRepository extends JpaRepository<UserAuthority, Integer> {
    @Query("from UserAuthority u where u.user.id = :id")
    public List<UserAuthority> findByUserId(Long id);
    @Transactional
    @Modifying
    @Query("delete UserAuthority u where u.user.id = :id")
    public void deleteByUserId(Long id);
    @Query("FROM UserAuthority u where u.user.id = :id and u.authority = :authority")
    public Optional<UserAuthority> findByIdAndAuthority(Long id, String authority);
}
