package com.redis.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@Builder
@ToString
public class ShortURL {
    private Long id;
    private UUID shortURLID;
    private Long distributedUID;
    private String longURL;
    private String shortCode;
    private String shortURL;
    private String urlHash;
    private LocalDateTime createdAt;
    private LocalDateTime expireAt;
}
