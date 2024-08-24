package org.example.message;

public class PushMessageTemplate {

    public static MessageParam getTicketingAlertMessageBefore1Hours() {
        return MessageParam.builder()
            .title("0000 티켓팅이 1시간 남았어요!")
            .body("1시간 후, 0000 예매가 시작됩니다!\n"
                + "티켓팅을 잊지 말고 준비하세요\uD83C\uDF9F")
            .build();
    }

    public static MessageParam getTicketingAlertMessageBefore6Hours() {
        return MessageParam.builder()
            .title("0000 티켓팅이 6시간 남았어요!")
            .body("6시간 후, 0000 예매가 오픈됩니다!\n"
                + "성공적인 티켓팅을 쇼팟이 응원해요\uD83E\uDD70")
            .build();
    }

    public static MessageParam getTicketingAlertMessageBefore24Hours() {
        return MessageParam.builder()
            .title("0000 티켓팅이 24시간 남았어요!")
            .body("24시간 후, 0000 예매가 오픈됩니다.\n"
                + "놓치지 말고 티켓팅을 준비하세요\uD83D\uDE00")
            .build();
    }

    public static MessageParam getSubscribedArtistVisitKoreaAlertMessage() {
        return MessageParam.builder()
            .title("속보! 0000의 내한 소식이 발표되었어요\uD83C\uDF8A")
            .body("쇼팟에서 상세한 내한 정보를 확인해보세요!")
            .build();
    }

    public static MessageParam getSubscribedGenreVisitKoreaAlertMessage() {
        return MessageParam.builder()
            .title("구독하신 00 장르의 공연 소식이 발표되었어요\uD83C\uDF88")
            .body("쇼팟에서 상세한 공연 정보를 확인해보세요!")
            .build();
    }
}
