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
@Table(name = "artist_subscription")
public class ArtistSubscription extends BaseEntity {

    @Column(nullable = false)
    private String userFcmToken;

    @Column(nullable = false)
    private UUID artistId;

    @Builder
    public ArtistSubscription(String userFcmToken, UUID artistId) {
        this.userFcmToken = userFcmToken;
        this.artistId = artistId;
    }

    public void subscribe() {
        this.revive();
    }

    public void unsubscribe() {
        this.softDelete();
    }
}
