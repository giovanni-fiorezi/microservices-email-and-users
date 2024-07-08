package com.microservices.user.services;

import com.microservices.user.dtos.UserDto;
import com.microservices.user.models.User;
import com.microservices.user.producers.UserProducer;
import com.microservices.user.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository repository;
    private final UserProducer userProducer;

    public UserService(UserRepository repository, UserProducer userProducer) {
        this.repository = repository;
        this.userProducer = userProducer;
    }

    @Transactional(rollbackFor = Exception.class)
    public User save(UserDto userDto) {
        try {
            User user = new User();
            BeanUtils.copyProperties(userDto, user);
            repository.save(user);
            userProducer.publishMessageEmail(user);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
