package com.ksprogramming.equipment.service;

import com.ksprogramming.equipment.data.NotificationData;

import java.util.List;

public interface NotificationServiceInterface {
    NotificationData createNotification(NotificationData notificationData);
    List<NotificationData> findNotificationsByReceiverId();
    Long countUnseenNotifications();
    void updateSeenNotification(Long id);
    void deleteNotification(Long id);
    List<NotificationData> findAllNotifications();
}
