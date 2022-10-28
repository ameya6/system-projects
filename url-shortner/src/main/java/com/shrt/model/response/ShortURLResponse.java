package com.shrt.model.response;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@AllArgsConstructor
@Builder
public class ShortURLResponse {
    private String longUrl;
    private String shortUrl;
    private LocalDateTime expireAt;
    private String exception;
}
