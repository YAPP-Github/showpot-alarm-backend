package org.example.message;

public class PushMessageTemplate {

    public static MessageParam getTicketingAlertMessageBefore1Hours(String showTitle) {
        return MessageParam.builder()
            .title("티켓팅이 1시간 남았어요!")
            .body(String.format("\"%s\"", showTitle))
            .build();
    }

    public static MessageParam getTicketingAlertMessageBefore6Hours(String showTitle) {
        return MessageParam.builder()
            .title("티켓팅이 6시간 남았어요!")
            .body(String.format("\"%s\"", showTitle))
            .build();
    }

    public static MessageParam getTicketingAlertMessageBefore24Hours(String showTitle) {
        return MessageParam.builder()
            .title("티켓팅이 24시간 남았어요!")
            .body(String.format("\"%s\"", showTitle))
            .build();
    }

    public static MessageParam getSubscribedArtistVisitKoreaAlertMessage(String artistName) {
        return MessageParam.builder()
            .title(
                String.format("속보! %s의 내한 소식이 발표되었어요\uD83C\uDF8A", artistName)
            )
            .body("쇼팟에서 상세한 내한 정보를 확인해보세요!")
            .build();
    }

    public static MessageParam getSubscribedGenreVisitKoreaAlertMessage(String genreName) {
        return MessageParam.builder()
            .title(
                String.format("구독하신 %s 장르의 공연 소식이 발표되었어요\uD83C\uDF88", genreName)
            )
            .body("쇼팟에서 상세한 공연 정보를 확인해보세요!")
            .build();
    }
}
