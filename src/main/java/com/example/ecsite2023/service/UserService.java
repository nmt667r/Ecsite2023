package com.example.ecsite2023.service;

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
}