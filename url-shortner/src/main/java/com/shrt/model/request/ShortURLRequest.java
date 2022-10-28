package com.shrt.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class ShortURLRequest {
    private Long userId;
    private String longURL;
    private LocalDateTime expireAt;
}
