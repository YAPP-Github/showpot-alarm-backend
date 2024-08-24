package org.example.message;

public class PushMessageTemplate {

    public static MessageParam getTestMessageTemplate() {
        return MessageParam.builder()
            .title("메세지 모시깽이 타이틀")
            .body("메세지 모시깽이 내용")
            .build();
    }
}
