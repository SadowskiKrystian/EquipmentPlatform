package com.ksprogramming.equipment.repository;

import com.ksprogramming.equipment.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("from User e where e.login = :login and e.deleteDate is null")
    public List<User> findUserByLogin(String login);
    @Query("from User e where e.deleteDate is null")
    public List<User> findDidntRemoveUser();

}
