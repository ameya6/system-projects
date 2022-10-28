package com.shrt.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "short_url")
public class ShortURL {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "distributed_uid", nullable = false)
    private Long distributedUID;

    @Column(name = "long_url", nullable = false)
    private String longURL;

    @Column(name = "short_code", nullable = false)
    private String shortCode;

    @Column(name = "short_url", nullable = false)
    private String shortURL;

    @JsonIgnore
    @Column(name = "created_at", nullable = false, insertable = false)
    private LocalDateTime createdAt;

    @Column(name = "expire_at", nullable = false)
    private LocalDateTime expireAt;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
