package com.example.demo.service;

import com.example.demo.controller.CreateduserRequest;
import com.example.demo.domain.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;

//얘가 CRUD해야 한다. Controller와 만나는 지점이자 어떤 도메인이 수행할 비즈니스 로직이 존재하는 곳이다.
@Service
public class UserService {
    private HashMap<Long, User> users = new HashMap<>();
    private Long id = 1L;

    public User create(CreateduserRequest createduserRequest) {
        User newUser = User.of(id, createduserRequest.getName(), createduserRequest.getPassword());
        users.put(id, newUser);
        id++;
        return newUser;
    }

    public User read(Long id) {
        return users.get(id);
    }


    public User update() {
        return users.get(id);
    }

    public User delete() {
        return users.get(id);
    }
}

