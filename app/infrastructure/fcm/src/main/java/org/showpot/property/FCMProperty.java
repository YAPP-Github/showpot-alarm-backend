package org.showpot.property;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "fcm")
public record FCMProperty(
    String type,
    @JsonProperty("project_id")
    String projectId,
    @JsonProperty("private_key_id")
    String privateKeyId,
    @JsonProperty("private_key")
    String privateKey,
    @JsonProperty("client_email")
    String clientEmail,
    @JsonProperty("client_id")
    String clientId,
    @JsonProperty("auth_uri")
    String authUri,
    @JsonProperty("token_uri")
    String tokenUri,
    @JsonProperty("auth_provider_x509_cert_url")
    String authProviderX509CertUrl,
    @JsonProperty("client_x509_cert_url")
    String clientX509CertUrl,
    @JsonProperty("universe_domain")
    String universeDomain
) {

    public InputStream toInputStream() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(this);

        return new ByteArrayInputStream(jsonString.getBytes(StandardCharsets.UTF_8));
    }
}
