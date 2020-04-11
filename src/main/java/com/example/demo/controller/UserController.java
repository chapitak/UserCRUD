package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

//유저와 관련된 모든 일들의 시작, View와 Controller가 만나는 지점이자, 어떤 도메인의 진입점이다.
@RestController
public class UserController {
    private UserService userService;
    @PostConstruct
    private void postConstruct() {
        System.out.println("유저 컨트롤러 잘 만들어졌다");

    }

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @GetMapping("/users/{id}")
    public User get(@PathVariable Long id) {
        //컨트롤러에서 서비스로 어떻게 가야하지? 1. static으로 만들까? 2. 객체를 갖고 있자!
        System.out.println("id=" + id);
        return userService.read(id);
    }

    @PostMapping("users")
//    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String postAllUsers(@RequestBody CreateduserRequest createduserRequest) {
        return "post world";
    }

//    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    @PutMapping("users")
    public String putAllUsers() {
        return "put world";
    }

    @DeleteMapping("users")
    @RequestMapping(value = "/users", method = RequestMethod.DELETE)
    public String deleteAllUsers() {
        return "delete";
    }
}
