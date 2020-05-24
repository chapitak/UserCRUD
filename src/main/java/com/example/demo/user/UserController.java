package com.example.demo.user;

import com.example.demo.user.dto.CreateUserRequest;
import com.example.demo.user.dto.LoginRequest;
import com.example.demo.user.dto.UpdateUserRequest;
import com.example.demo.user.dto.UserResponse;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

//유저와 관련된 모든 일들의 시작, View와 Controller가 만나는 지점이자, 어떤 도메인의 진입점이다.
@RestController
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    private void postConstruct() {
        System.out.println("유저 컨트롤러 잘 만들어졌다");

    }

    //    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @GetMapping("/users/{id}")
    public UserResponse get(@PathVariable Long id) {
        //컨트롤러에서 서비스로 어떻게 가야하지? 1. static으로 만들까? 2. 객체를 갖고 있자!
        System.out.println("id=" + id);
        return userService.read(id);
    }

    @PostMapping("users")   //@RequestBody 안붙여도 된다그러지않았나.. 이보시오
//    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public UserResponse postAllUsers(@RequestBody CreateUserRequest createUserRequest) {
        return userService.create(createUserRequest);
    }

    //    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    @PutMapping("users")
    public UserResponse putAllUsers(@RequestBody UpdateUserRequest updateUserRequest) {
        return userService.update(updateUserRequest);
    }

    @DeleteMapping("/users/{id}")
    //@RequestMapping(value = "/users", method = RequestMethod.DELETE)
    public String deleteAllUsers(@PathVariable Long id) {
        userService.delete(id);
        return "delete";
    }
    @PostMapping("users/login")
    public void login(HttpSession httpSession, @RequestBody LoginRequest loginRequest) {
        userService.login(httpSession, loginRequest);
    }

    @PostMapping("users/logout")
    public void logout(HttpSession httpSession, @RequestBody LoginRequest loginRequest) {
        userService.logout(httpSession);
    }
}
