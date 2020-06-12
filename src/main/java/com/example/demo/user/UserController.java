package com.example.demo.user;

import com.example.demo.user.dto.CreateUserRequest;
import com.example.demo.user.dto.LoginRequest;
import com.example.demo.user.dto.UpdateUserRequest;
import com.example.demo.user.dto.UserResponse;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

//유저와 관련된 모든 일들의 시작, View와 Controller가 만나는 지점이자, 어떤 도메인의 진입점이다.
@RestController
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("users")
    public UserResponse postAllUsers(@RequestBody CreateUserRequest createUserRequest) {
        return userService.create(createUserRequest);
    }

    @GetMapping("/users/{id}")
    public UserResponse get(@PathVariable Long id) {
        System.out.println("id=" + id);
        return userService.read(id);
    }

    @PutMapping("users")
    public UserResponse putAllUsers(@RequestBody UpdateUserRequest updateUserRequest) {
        return userService.update(updateUserRequest);
    }

    @DeleteMapping("/users/{id}")
    public void deleteAllUsers(@PathVariable Long id) {
        userService.delete(id);
    }

    @PostMapping("users/login")
    public void login(HttpSession httpSession, @RequestBody LoginRequest loginRequest) {
        userService.login(httpSession, loginRequest);
    }

    @PostMapping("users/logout")
    public void logout(HttpSession httpSession) {
        userService.logout(httpSession);
    }
}
