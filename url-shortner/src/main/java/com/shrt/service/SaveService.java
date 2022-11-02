package com.shrt.service;

import com.redis.dao.ShortURLRedisDao;
import com.shrt.model.ShortURL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveService {

    @Autowired
    public ShortURLRedisDao<ShortURL> shortURLRedisDao;

    public void redisSave(ShortURL shortURL) {
        shortURLRedisDao.set("url_hash:" + shortURL.getUrlHash(), "$", shortURL);
    }
}
