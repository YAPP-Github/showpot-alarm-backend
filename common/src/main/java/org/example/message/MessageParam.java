package org.example.message;

import lombok.Builder;

@Builder
public record MessageParam(
    String title,
    String body
) {

}
