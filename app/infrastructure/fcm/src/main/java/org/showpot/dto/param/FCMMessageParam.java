package org.showpot.dto.param;

import com.google.firebase.messaging.Notification;
import lombok.Builder;
import org.example.message.MessageParam;

@Builder
public record FCMMessageParam(
    String title,
    String body,
    String imageURL
) {

    public static FCMMessageParam from(MessageParam param) {
        return FCMMessageParam.builder()
            .title(param.title())
            .body(param.body())
            .imageURL(param.imageURL())
            .build();
    }

    public Notification toNotification() {
        return Notification.builder()
            .setTitle(title())
            .setBody(body())
            .setImage(imageURL())
            .build();
    }
}
