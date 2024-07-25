package com.ksprogramming.equipment.service;

import com.ksprogramming.equipment.data.NotificationData;
import com.ksprogramming.equipment.data.UserData;
import com.ksprogramming.equipment.entities.Notification;
import com.ksprogramming.equipment.entities.User;
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
        return notificationsEntityToData(notificationRepository.findAllByReceiverLogin(authentication.getName()));

    }
    public List<NotificationData> findAllNotifications(){
        return notificationsEntityToData(notificationRepository.findAll());
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
        return notificationEntityToData(notificationRepository.save(notificationDataToEntity(notificationData)));
    }

    private List<NotificationData> notificationsEntityToData(List<Notification> notifications) {
        List<NotificationData> notificationList = new ArrayList<>();
        notifications.forEach(notification -> {
            notificationList.add(new NotificationData(notification.getId(), notification.getSenderLogin(), userEntityToData(notification.getReceiver()),
                    notification.getTitle(), notification.getContent(), notification.getCreateDateTime(), notification.getSeenDateTime(),
                    notification.getDeleteDateTime()));
        });
        return notificationList;
    }

    private NotificationData notificationEntityToData(Notification notification) {
        return new NotificationData(notification.getId(), notification.getSenderLogin(), userEntityToData(notification.getReceiver()),
                notification.getTitle(), notification.getContent(), notification.getCreateDateTime(), notification.getSeenDateTime(),
                notification.getDeleteDateTime());
    }
    private UserData userEntityToData(User user) {
        return new UserData(user.getId(), user.getLogin(), user.getPasswordHash(),
                user.getEmailConfirmed(), user.getLanguage(), user.getRegistrationDate());
    }

    private Notification notificationDataToEntity(NotificationData notificationData) {
        return new Notification(notificationData.getId(), notificationData.getSenderLogin(), userDataToEntity(notificationData.getReceiverId()),
                notificationData.getTitle(), notificationData.getContent(), notificationData.getCreateDateTime(), notificationData.getSeenDateTime(),
                notificationData.getDeleteDateTime());
    }
    private User userDataToEntity(UserData user) {
        return new User(user.getId(), user.getLogin(), user.getPasswordHash(),
                user.getEmailConfirmed(), user.getLanguage(), user.getRegistrationDate());
    }
}
