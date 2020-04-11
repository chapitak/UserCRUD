package com.example.demo.service;

import com.example.demo.domain.User;
import org.springframework.stereotype.Service;

//얘가 CRUD해야 한다. Controller와 만나는 지점이자 어떤 도메인이 수행할 비즈니스 로직이 존재하는 곳이다.
@Service
public class UserService {
    public User create() {
        return new User();
    }

    public User read(Long id) {
        return new User();
    }


    public User update() {
        return new User();
    }

    public User delete() {
        return new User();
    }
}
