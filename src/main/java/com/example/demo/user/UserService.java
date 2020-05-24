package com.example.demo.user;

import com.example.demo.user.dto.CreateUserRequest;
import com.example.demo.user.dto.LoginRequest;
import com.example.demo.user.dto.UpdateUserRequest;
import com.example.demo.user.dto.UserResponse;
import lombok.Getter;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

//얘가 CRUD해야 한다. Controller와 만나는 지점이자 어떤 도메인이 수행할 비즈니스 로직이 존재하는 곳이다.
@Getter
@Service
public class UserService {
    private UserRepository userRepository;
//    private HashMap<Long, User> users = new HashMap<>();
    private Long nextId = 0L;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse create(CreateUserRequest createUserRequest) {
        User newUser = User.builder()
                .id(nextId)
                .email(createUserRequest.getEmail())
                .password(createUserRequest.getPassword())
                .name(createUserRequest.getName())
                .build();
        userRepository.save(newUser);
//        users.put(newUser.getId(), newUser);
        nextId++;

        return getUserResponse(newUser);
    }

    private UserResponse getUserResponse(User newUser) {
        return UserResponse.builder()
                .email(newUser.getEmail())
                .name(newUser.getName())
                .build();
    }

    public User get(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 ID의 사용자가 없습니다"));
    }

    public UserResponse read(Long id) {
        return getUserResponse(userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 ID의 사용자가 없습니다")));
    }

    public UserResponse update(UpdateUserRequest updateUserRequest) {
        User newUser = User
                .builder()
                .id(updateUserRequest.getId())
                .name((updateUserRequest.getName()))
                .password(updateUserRequest.getPassword())
                .build();
        userRepository.save(newUser);

        return getUserResponse(newUser);
    }

    public void delete(Long id) {
        userRepository.delete(userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 ID의 사용자가 없습니다")));
    }

    public void login(HttpSession httpSession, LoginRequest loginRequest){
        Object loginUserAttr = httpSession.getAttribute("LOGIN_USER");
        if (loginUserAttr == null) {
            System.out.println("not logined.");
        } else {
            User loginUser = (User) loginUserAttr;
            System.out.println(loginUser.getEmail());
        }

        String encryptedPasswordQuery = PasswordEncryptor.encrypt(loginRequest.getPassword());
        User user = getByEmail(loginRequest.getEmail());
        if (!user.matchPassword(encryptedPasswordQuery)) {
            throw new IllegalArgumentException("login Failed");
        }
        httpSession.setAttribute("LOGIN_USER", user);

    }

    private User getByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("해당 ID의 사용자가 없습니다"));
    }

    public void logout(HttpSession httpSession) {
        httpSession.removeAttribute("LOGIN_USER");
    }
}

