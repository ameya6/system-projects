package com.shrt.service;

import com.google.gson.Gson;
import com.shrt.dao.ShortURLDAO;
import com.shrt.dao.UserDao;
import com.shrt.model.DistributedUID;
import com.shrt.model.ShortURL;
import com.shrt.model.User;
import com.shrt.model.request.ShortURLRequest;
import com.shrt.model.response.ShortURLResponse;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Log4j2
@Service
public class URLShortnerService {

    @Autowired
    private Gson gson;

    @Value("${http.server.uid}")
    private String uidServer;

    @Value("${domain.url}")
    private String domainURL;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ShortURLDAO shortURLDAO;

    @Autowired
    private SaveService saveService;

    private final String map = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public ShortURLResponse                               shortURL(ShortURLRequest shortURLRequest) throws URISyntaxException, IOException, InterruptedException {
        //log.info("Short url request : " + shortURLRequest);
        ShortURL shortURL = createShortURL(shortURLRequest);
        //log.info("Short url  : " + shortURL);
        //shortURLDAO.save(shortURL);
        saveService.redisSave(shortURL);
        return ShortURLResponse
                .builder()
                .longUrl(shortURLRequest.getLongURL())
                .expireAt(shortURL.getExpireAt())
                .shortUrl(shortURL.getShortURL())
                .build();
    }

    private ShortURL createShortURL(ShortURLRequest shortURLRequest) throws URISyntaxException, IOException, InterruptedException {
        Long uid = distributedUID();
        String shortCode = base62(uid);
        String shortUrl = domainURL + shortCode;
        LocalDateTime expireAt = shortURLRequest.getExpireAt() == null ? LocalDateTime.now().plusYears(10) : shortURLRequest.getExpireAt();
        String urlHash = DigestUtils.sha256Hex(shortURLRequest.getLongURL());
        /**
         *  ! Design options
         * * User can be fetched reactively
         * * frequent User can be stored in redis and can be fetched reactively
         *
         */
        User user = userDao.findById(shortURLRequest.getUserId());

        return ShortURL.builder()
                .longURL(shortURLRequest.getLongURL())
                .shortURLID(UUID.randomUUID())
                .distributedUID(uid)
                .urlHash(urlHash)
                .expireAt(expireAt)
                .user(user)
                .shortCode(shortCode)
                .shortURL(shortUrl)
                .build();
    }

    public Long distributedUID() throws URISyntaxException, IOException, InterruptedException {
        //log.info(uidServer);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(uidServer))
                .GET()
                .build();

        HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
        return gson.fromJson(response.body(), DistributedUID.class).getDistributedUID();
    }

    public String base62(long n)
    {
        StringBuilder shorturl = new StringBuilder("");
        for(int i = 0; i < 7; i++)
        {
            int mod = (int)(n % 62);
            shorturl.append(map.charAt(mod));
            n = n/62;
        }
        return shuffle(shorturl.reverse().toString());
    }

    public String shuffle(String input){
        List<Character> characters = new ArrayList<>();
        for(char c:input.toCharArray()){
            characters.add(c);
        }
        StringBuilder output = new StringBuilder(input.length());
        while(characters.size() != 0){
            int randomPicker = (int)(Math.random() * characters.size());
            output.append(characters.remove(randomPicker));
        }
        return output.toString();
    }
}
