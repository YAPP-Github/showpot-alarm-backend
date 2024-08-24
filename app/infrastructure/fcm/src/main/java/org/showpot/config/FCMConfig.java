package org.showpot.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.showpot.property.FCMProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(FCMProperty.class)
@ComponentScan(basePackages = "org.showpot")
@RequiredArgsConstructor
public class FCMConfig {

    private final FCMProperty fcmProperty;

    @Bean
    public FirebaseMessaging firebaseMessaging() {
        FirebaseApp firebaseApp;

        try {
            firebaseApp = getApp();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return FirebaseMessaging.getInstance(firebaseApp);
    }

    private FirebaseApp getApp() throws IOException {
        List<FirebaseApp> apps = FirebaseApp.getApps();

        if (apps.isEmpty()) {
            return initializeApp();
        }

        for (FirebaseApp app : apps) {
            if (app.getName().equals(FirebaseApp.DEFAULT_APP_NAME)) {
                return app;
            }
        }

        return initializeApp();
    }

    private FirebaseApp initializeApp() throws IOException {
        FirebaseOptions options = FirebaseOptions.builder()
            .setCredentials(
                GoogleCredentials.fromStream(fcmProperty.toInputStream())
            )
            .build();

        return FirebaseApp.initializeApp(options);
    }
}
