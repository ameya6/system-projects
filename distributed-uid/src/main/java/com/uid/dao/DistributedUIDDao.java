package com.uid.dao;

import com.uid.model.DistributedUID;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Log4j2
public class DistributedUIDDao {

    @Autowired
    private Session session;

    @Transactional
    public Long save(DistributedUID duid) {
        //log.debug("Saving " + duid);
        Long id = (Long) session.save(duid);
        //log.info("id " + id);
        return id;
    }

    @Transactional
    public void persist(DistributedUID duid) {
        session.save(duid);
    }
}
