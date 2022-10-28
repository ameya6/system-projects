package com.shrt.dao;

import com.shrt.model.ShortURL;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ShortURLDAO {

    @Autowired
    private Session session;

    @Transactional
    public void save(ShortURL shortURL) {
        session.save(shortURL);
    }
}
