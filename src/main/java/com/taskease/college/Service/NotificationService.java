package com.taskease.college.Service;


import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.taskease.college.Model.Faculty.IMPNotice;
import com.taskease.college.PayLoad.ImpNoticeDTO;
import com.taskease.college.Repository.IMPNoticeRepo;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final IMPNoticeRepo impNoticeRepo;

    public NotificationService(IMPNoticeRepo impNoticeRepo) {
        this.impNoticeRepo = impNoticeRepo;
    }

    public String sendNotificationToAll(String title, String description) {
        try {
            Notification notification = Notification.builder()
                    .setTitle(title)
                    .setBody(description)
                    .build();

            Message message = Message.builder()
                    .setTopic("all")
                    .setNotification(notification)
                    .putData("title", title)
                    .putData("description", description)
                    .build();

            IMPNotice impNotice = new IMPNotice();
            impNotice.setTitle(title);
            impNotice.setDescription(description);
            this.impNoticeRepo.save(impNotice);
            String response = FirebaseMessaging.getInstance().send(message);
            return "Notification sent successfully: " + response;
        } catch (Exception e) {
            throw new RuntimeException("Failed to send notification: " + e.getMessage());
        }
    }
}
