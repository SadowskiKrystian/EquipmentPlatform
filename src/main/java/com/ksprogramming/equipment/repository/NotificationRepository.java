package com.ksprogramming.equipment.repository;

import com.ksprogramming.equipment.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    @Query("FROM Notification n where n.receiver.login = :login")
    List<Notification> findAllByReceiverLogin(String login);
    @Query("SELECT count(*) FROM Notification n where n.receiver.login = :receiverId and n.seenDateTime is null ")
    Long findAllUnseenByReceiver(String receiverId);
}
