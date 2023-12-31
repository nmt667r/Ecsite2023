package com.example.ecsite2023.repository;

import com.example.ecsite2023.repository.entity.Cart;
import com.example.ecsite2023.repository.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    @Transactional
    public void deleteByUserId(Integer userId);

}
