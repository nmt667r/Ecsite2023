package com.example.ecsite2023.repository;

import com.example.ecsite2023.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findById(int id);
    public List<User> findAll();
    public List<User> findByAccountAndPassword(String account, String password);
}
