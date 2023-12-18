package com.example.ecsite2023.service;

import com.example.ecsite2023.controller.form.ItemForm;
import com.example.ecsite2023.repository.CartRepository;
import com.example.ecsite2023.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;
    public void addItem(ItemForm itemForm) {

    }
}
