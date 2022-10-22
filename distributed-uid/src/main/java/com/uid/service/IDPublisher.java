package com.uid.service;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class IDPublisher implements Runnable {

    /*Queue<DistributedUID> queue;
    IDGenerationService idGenerationService;

    public IDPublisher(Queue<DistributedUID> queue, IDGenerationService idGenerationService) {
        this.queue = queue;
        this.idGenerationService = idGenerationService;
    }*/

    @Override
    public void run() {
        /*while (true) {
            DistributedUID distributedUID = DistributedUID.builder()
                    .uid(idGenerationService.uid())
                    .build();
            queue.offer(distributedUID);
            log.info("Published " + distributedUID.getUid() + " Queue size " + queue.size() + " Thread " + Thread.currentThread());
        }*/

    }
}
