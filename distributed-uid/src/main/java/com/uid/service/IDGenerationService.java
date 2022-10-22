package com.uid.service;

import com.uid.model.DistributedUID;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class IDGenerationService {
    private static final int NODE_ID_BITS = 10;
    // Custom Epoch (January 1, 2015 Midnight UTC = 2015-01-01T00:00:00Z)
    private static final long DEFAULT_CUSTOM_EPOCH = 1420070400000L;
    private static final int SEQUENCE_BITS = 13;
    private static final long maxSequence = (1L << SEQUENCE_BITS) - 1;
    private static final long maxNodeId = (1L << NODE_ID_BITS) - 1;
    private volatile long lastTimestamp = -1L;
    private volatile long sequence = 0L;

    private final String str = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";


    public DistributedUID uid() {
        return nextId();
    }

    public synchronized DistributedUID nextId() {

        long currentTimestamp = timestamp();

        if(currentTimestamp < lastTimestamp) {
            throw new IllegalStateException("Invalid System Clock!");
        }

        if (currentTimestamp == lastTimestamp) {
            sequence = (sequence + 1) & maxSequence;
            if(sequence == 0) {
                // Sequence Exhausted, wait till next millisecond.
                currentTimestamp = waitNextMillis(currentTimestamp);
            }
        } else {
            // reset sequence to start with zero for the next millisecond
            sequence = 0;
        }

        lastTimestamp = currentTimestamp;
        long midId = randomMidId();
        long id = currentTimestamp << (NODE_ID_BITS + SEQUENCE_BITS)
                | (midId << SEQUENCE_BITS)
                | sequence;

        return DistributedUID.builder()
                .DUIDTimestamp(currentTimestamp)
                .distributedUID(Math.abs(id))
                .DUIDMidID(midId)
                .DUIDSequence(sequence)
                .build();
    }

    private long timestamp() {
        return System.nanoTime() - DEFAULT_CUSTOM_EPOCH;
    }

    private Long randomMidId() {
        return Long.valueOf(Math.abs(UUID.randomUUID().toString().hashCode())) & maxNodeId;
    }

    private long waitNextMillis(long currentTimestamp) {
        while (currentTimestamp == lastTimestamp) {
            currentTimestamp = timestamp();
        }
        return currentTimestamp;
    }
}
