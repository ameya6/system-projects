package com.shrt.service;

import com.shrt.dao.UserDao;
import com.shrt.model.User;
import com.shrt.model.request.UserRequest;
import com.shrt.model.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public UserResponse save(UserRequest userRequest) {
        User user = createUser(userRequest);
        userDao.save(user);
        return createUserResponse(user);
    }

    public UserResponse findById(Long id) {
        User user = userDao.findById(id);
        return createUserResponse(user);
    }

    private User createUser(UserRequest userRequest) {
        return User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .username(userRequest.getUsername())
                .build();
    }

    private UserResponse createUserResponse(User user) {
        String message = "User " + user.getFirstName() + " " + user.getLastName() + " with username " + user.getUsername() + " created";
        return UserResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .message(message)
                .build();
    }
}
