package com.ksprogramming.equipment.service;

import com.ksprogramming.equipment.data.NotificationData;
import com.ksprogramming.equipment.data.UserData;
import com.ksprogramming.equipment.entities.Notification;
import com.ksprogramming.equipment.entities.User;
import com.ksprogramming.equipment.mapper.NotificationMapper;
import com.ksprogramming.equipment.repository.NotificationRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
// todo Add create notification by admin
@Service
public class NotificationService implements NotificationServiceInterface{
    private NotificationRepository notificationRepository;
    private UserServiceInterface userService;
    public NotificationService(NotificationRepository notificationRepository, UserServiceInterface userService) {
        this.notificationRepository = notificationRepository;
        this.userService = userService;
    }
    public List<NotificationData> findNotificationsByReceiverId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return NotificationMapper.entityToDataList(notificationRepository.findAllByReceiverLogin(authentication.getName()));

    }
    public List<NotificationData> findAllNotifications(){
        return NotificationMapper.entityToDataList(notificationRepository.findAll());
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
    public void deleteNotification(Long id){
        Optional<Notification> byId = notificationRepository.findById(id);
        Notification notification = byId.get();
        notificationRepository.delete(notification);
    }

    public NotificationData createNotification(NotificationData notificationData) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        notificationData.setSenderLogin(authentication.getName());
        notificationData.setCreateDateTime(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        return NotificationMapper.entityToData(notificationRepository.save(NotificationMapper.dataToEntity(notificationData)));
    }
}
