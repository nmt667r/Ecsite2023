package com.example.ecsite2023.service;

import com.example.ecsite2023.controller.form.CartForm;
import com.example.ecsite2023.controller.form.ItemForm;
import com.example.ecsite2023.repository.CartRepository;
import com.example.ecsite2023.repository.ItemRepository;
import com.example.ecsite2023.repository.entity.Cart;
import com.example.ecsite2023.repository.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;
    public void addItem(CartForm cartForm) {
        Cart saveCart = setCartEntity(cartForm);
        cartRepository.save(saveCart);
    }
    private Cart setCartEntity(CartForm cartForm) {
        Cart cart = new Cart();
        cart.setItemId(cartForm.getId());
        cart.setUserId(cartForm.getUserId());
        cart.setAmount(cartForm.getAmount());
        cart.setCreateDate(new Date());
        cart.setUpdateDate(new Date());
        return cart;
    }
}
