package com.ksprogramming.equipment.repository;

import com.ksprogramming.equipment.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
