package com.example.ecsite2023.service;

import com.example.ecsite2023.controller.form.CartForm;
import com.example.ecsite2023.repository.CartRepository;
import com.example.ecsite2023.repository.entity.Cart;
import com.example.ecsite2023.repository.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public CartForm findByCart(Integer userId) {
        List<Cart> results = cartRepository.findByUserId(userId);
        return setCartForm(results).get(0);
    }

    private List<CartForm> setCartForm(List<Cart> results) {
        List<CartForm> carts = new ArrayList<>();
        for (int i = 0; i < results.size(); i++) {
            CartForm Cart = new CartForm();
            Cart result = results.get(i);
            Cart.setId(result.getId());
            Cart.setItemId(result.getItemId());
            Cart.setName(result.getName());
            Cart.setAmount(result.getAmount());
            Cart.setPrice(result.getPrice());
            Cart.setCreateDate(result.getCreateDate());
            Cart.setUpdateDate(result.getUpdateDate());
            carts.add(Cart);
        }
        return carts;
    }
}
