package com.example.ecsite2023.service;

import com.example.ecsite2023.controller.form.LoginForm;
import com.example.ecsite2023.controller.form.SignupForm;
import com.example.ecsite2023.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ecsite2023.controller.form.UserForm;
import com.example.ecsite2023.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<UserForm> findAllUser() {
        List<User> results = userRepository.findAll();
        List<UserForm> users = setUserForm(results);
        return users;
    }
    private List<UserForm> setUserForm(List<User> results) {
        List<UserForm> users = new ArrayList<>();

        for (int i = 0; i < results.size(); i++) {
            User result = results.get(i);
            UserForm user = new UserForm();

            user.setId(result.getId());
            users.add(user);
        }
        return users;
    }

    public List<User> findUser(LoginForm loginForm) {
        User selectUser = setSelectUserEntity(loginForm);
        return userRepository.findByAccountAndPassword(selectUser.getAccount(), selectUser.getPassword());
    }

    private User setSelectUserEntity(LoginForm loginForm) {
        User user = new User();
        user.setAccount(loginForm.getAccount());
        user.setPassword(loginForm.getPassword());
        return user;
    }

    public void createUser(SignupForm signupForm) {
        User createUser = setInsertUserEntity(signupForm);
        userRepository.save(createUser);
    }

    private User setInsertUserEntity(SignupForm signupForm) {
        User user = new User();
        user.setAccount(signupForm.getAccount());
        user.setName(signupForm.getName());
        user.setPassword(signupForm.getPassword());
        user.setCreateDate(signupForm.getCreateDate());
        user.setUpdateDate(signupForm.getUpdateDate());
        return user;
    }
}