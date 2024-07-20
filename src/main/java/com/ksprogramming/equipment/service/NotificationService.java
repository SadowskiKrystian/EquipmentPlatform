package com.ksprogramming.equipment.service;

import com.ksprogramming.equipment.data.NotificationData;
import com.ksprogramming.equipment.entities.Notification;
import com.ksprogramming.equipment.repository.NotificationRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService implements NotificationServiceInterface{
    private NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }
    public List<NotificationData> findNotificationsByReceiverId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return notificationsEntityToData(notificationRepository.findAllByReceiverLogin(authentication.getName()));

    }
    public Long countUnseenNotifications(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return notificationRepository.findAllUnseenByReceiver(authentication.getName());
    }
    public void updateSeenNotification(Long id){
        Optional<Notification> byId = notificationRepository.findById(id);
        Notification notification = byId.get();
        if(notification.getSeenDateTime() == null) {
            notification.setSeenDateTime(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
            notificationRepository.save(notification);
        }
    }

    private List<NotificationData> notificationsEntityToData(List<Notification> notifications) {
        List<NotificationData> notificationList = new ArrayList<>();
        notifications.forEach(notification -> {
            notificationList.add(new NotificationData(notification.getId(), notification.getSenderLogin(), notification.getReceiver(),
                    notification.getTitle(), notification.getContent(), notification.getCreateDateTime(), notification.getSeenDateTime(),
                    notification.getDeleteDateTime()));
        });
        return notificationList;
    }

    public NotificationData createNotification(NotificationData notificationData) {
        return notificationEntityToData(notificationRepository.save(notificationDataToEntity(notificationData)));

    }

    private NotificationData notificationEntityToData(Notification notification) {
        return new NotificationData(notification.getId(), notification.getSenderLogin(), notification.getReceiver(),
                notification.getTitle(), notification.getContent(), notification.getCreateDateTime(), notification.getSeenDateTime(),
                notification.getDeleteDateTime());
    }

    private Notification notificationDataToEntity(NotificationData notificationData) {
        return new Notification(notificationData.getId(), notificationData.getSenderLogin(), notificationData.getReceiverId(),
                notificationData.getTitle(), notificationData.getContent(), notificationData.getCreateDateTime(), notificationData.getSeenDateTime(),
                notificationData.getDeleteDateTime());
    }
}
