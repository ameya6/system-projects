package com.uid.service;

import com.uid.dao.DistributedUIDDao;
import com.uid.model.DistributedUID;
import com.uid.model.DistributedUIDDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class DistributedUIDService {

    @Autowired
    IDGenerationService idGenerationService;

    @Autowired
    DistributedUIDDao distributedUIDDao;

    private DistributedUID distributedUID() {
        DistributedUID distributedUID = idGenerationService.uid();
        //log.info(distributedUID);
        distributedUIDDao.save(distributedUID);
        return distributedUID;
    }

    public DistributedUIDDTO getDistributedUID() {
        DistributedUID distributedUID = distributedUID();
        return DistributedUIDDTO.builder()
                .distributedUID(distributedUID.getDistributedUID())
                .build();
    }
}
