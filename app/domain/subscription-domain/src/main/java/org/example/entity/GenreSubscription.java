package org.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "genre_subscription")
public class GenreSubscription extends BaseEntity {

    @Column(nullable = false)
    private String userFcmToken;

    @Column(nullable = false)
    private UUID genreId;

    @Column(nullable = false)
    private String genreName;

    @Builder
    private GenreSubscription(String userFcmToken, UUID genreId, String genreName) {
        this.userFcmToken = userFcmToken;
        this.genreId = genreId;
        this.genreName = genreName;
    }

    public void subscribe() {
        this.revive();
    }

    public void unsubscribe() {
        this.softDelete();
    }
}
