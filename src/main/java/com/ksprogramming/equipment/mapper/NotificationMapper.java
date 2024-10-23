package com.ksprogramming.equipment.mapper;

import com.ksprogramming.equipment.api.NotificationGetResponse;
import com.ksprogramming.equipment.data.NotificationData;
import com.ksprogramming.equipment.entities.Notification;

import java.util.ArrayList;
import java.util.List;

public class NotificationMapper {

    public static Notification dataToEntity(NotificationData notificationData) {
        return Notification.builder()
                .id(notificationData.getId())
                .senderLogin(notificationData.getSenderLogin())
                .receiver(UserMapper.dataToEntity(notificationData.getReceiverId()))
                .title(notificationData.getTitle())
                .content(notificationData.getContent())
                .createDateTime(notificationData.getCreateDateTime())
                .seenDateTime(notificationData.getSeenDateTime())
                .deleteDateTime(notificationData.getDeleteDateTime())
                .build();
    }

    public static NotificationData entityToData(Notification notification) {
        return NotificationData.builder()
                .id(notification.getId())
                .senderLogin(notification.getSenderLogin())
                .receiverId(UserMapper.entityToData(notification.getReceiver()))
                .title(notification.getTitle())
                .content(notification.getContent())
                .createDateTime(notification.getCreateDateTime())
                .seenDateTime(notification.getSeenDateTime())
                .deleteDateTime(notification.getDeleteDateTime())
                .build();
    }

    public static List<Notification> dataToEntityList(List<NotificationData> notificationDataList) {
        List<Notification> notifications = new ArrayList<>();
        notificationDataList.forEach(notificationData -> {notifications.add(dataToEntity(notificationData));});
        return notifications;
    }

    public static List<NotificationData> entityToDataList(List<Notification> notifications) {
        List<NotificationData> notificationDataList = new ArrayList<>();
        notifications.forEach(notification -> {notificationDataList.add(entityToData(notification));});
        return notificationDataList;
    }

    public static List<NotificationGetResponse> dataToGetRespone(List<NotificationData> notifications) {
        List<NotificationGetResponse> responses = new ArrayList<>();
        notifications.forEach(notification -> {
            responses.add(new NotificationGetResponse(notification.getId(), notification.getSenderLogin(), UserMapper.dataToGetResponse(notification.getReceiverId()),
                    notification.getTitle(), notification.getContent(), notification.getCreateDateTime(), notification.getSeenDateTime()))
            ;
        });
        return responses;
    }
}
