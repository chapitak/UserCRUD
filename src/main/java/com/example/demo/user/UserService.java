package com.example.demo.user;

import com.example.demo.user.dto.CreateUserRequest;
import com.example.demo.user.dto.LoginRequest;
import com.example.demo.user.dto.UpdateUserRequest;
import com.example.demo.user.dto.UserResponse;
import lombok.Getter;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Getter
@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse create(CreateUserRequest createUserRequest) {
        User newUser = User.builder()
                .email(createUserRequest.getEmail())
                .password(PasswordEncryptor.encrypt((createUserRequest.getPassword())))
                .name(createUserRequest.getName())
                .build();
        userRepository.save(newUser);

        return getUserResponse(newUser);
    }

    public User get(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 ID의 사용자가 없습니다"));
    }

    public UserResponse read(Long id) {
        return getUserResponse(userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 ID의 사용자가 없습니다")));
    }

    public UserResponse update(UpdateUserRequest updateUserRequest) {
        User updatedUser = userRepository.findById(updateUserRequest.getId()).orElseThrow(() -> new IllegalArgumentException(("해당 ID의 사용자가 없습니다")));
        updatedUser.updateName(updateUserRequest.getName());
        updatedUser.updatePassword(PasswordEncryptor.encrypt(updateUserRequest.getPassword()));
        userRepository.save(updatedUser);

        return getUserResponse(updatedUser);
    }

    public void delete(Long id) {
        userRepository.delete(userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 ID의 사용자가 없습니다")));
    }

    private UserResponse getUserResponse(User newUser) {
        return UserResponse.builder()
                .email(newUser.getEmail())
                .name(newUser.getName())
                .build();
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
    public void logout(HttpSession httpSession) {
        httpSession.removeAttribute("LOGIN_USER");
    }

    private User getByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("해당 ID의 사용자가 없습니다"));
    }

}

