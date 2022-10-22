package com.uid.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "uid")
@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
public class DistributedUID {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @Column(insertable=false, nullable = false)
    private String uid;

    @Column(name = "distributed_uid", nullable = false)
    private Long distributedUID;

    @JsonIgnore
    @Column(name = "created_at", insertable=false)
    private LocalDateTime createdAt;

    @JsonIgnore
    @Column(name = "duid_timestamp", nullable = false)
    private Long DUIDTimestamp;

    @JsonIgnore
    @Column(name = "duid_mid_id", nullable = false)
    private Long DUIDMidID;

    @JsonIgnore
    @Column(name = "duid_sequence", nullable = false)
    private Long DUIDSequence;
}