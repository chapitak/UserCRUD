package com.example.demo.service;

import com.example.demo.controller.CreateUserRequest;
import com.example.demo.controller.UpdateUserRequest;
import com.example.demo.domain.User;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.HashMap;

//얘가 CRUD해야 한다. Controller와 만나는 지점이자 어떤 도메인이 수행할 비즈니스 로직이 존재하는 곳이다.
@Getter
@Service
public class UserService {
    private HashMap<Long, User> users = new HashMap<>();
    private Long id = 1L;

    public User create(CreateUserRequest createUserRequest) {
        User newUser = User.of(id, createUserRequest.getName(), createUserRequest.getPassword());
        users.put(id, newUser);
        id++;
        return newUser;
    }

    public User read(Long id) {
        return users.get(id);
    }


    public User update(UpdateUserRequest updateUserRequest) {
        User newUser = User.of(updateUserRequest.getId(), updateUserRequest.getName(), updateUserRequest.getPassword());
        users.put(updateUserRequest.getId(), newUser);

        return users.get(updateUserRequest.getId());
    }

    public void delete(Long id) {
        users.remove(id);
    }
}

