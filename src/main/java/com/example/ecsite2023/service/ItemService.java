package com.example.ecsite2023.service;

import com.example.ecsite2023.controller.form.ItemForm;
import com.example.ecsite2023.repository.ItemRepository;
import com.example.ecsite2023.repository.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {
    @Autowired
    ItemRepository itemRepository;
    public List<ItemForm> findAllItems() {
        List<Item> results = itemRepository.findAllByOrderByIdDesc();
        return setItemForm(results);
    }

    public List<ItemForm> findItemById(Integer id) {
        List<Item> results = itemRepository.findById(id.intValue());
        return setItemForm(results);
    }

    private List<ItemForm> setItemForm(List<Item> results) {
        List<ItemForm> items = new ArrayList<>();

        for (int i = 0; i < results.size(); i++) {
            ItemForm Item = new ItemForm();
            Item result = results.get(i);
            Item.setId(result.getId());
            Item.setName(result.getName());
            Item.setPrice(String.valueOf(result.getPrice()));
            Item.setImage(result.getImage());
            Item.setStatus(result.isStatus());
            Item.setCreateDate(result.getCreateDate());
            Item.setUpdateDate(result.getUpdateDate());
            items.add(Item);

        }
        return items;
    }

    public void saveItem(ItemForm itemForm) {
        Item saveItem = setItemEntity(itemForm);
        itemRepository.save(saveItem);
    }

    public void saveItem(ItemForm itemForm, MultipartFile file) {
        Item saveItem = setItemEntity(itemForm);
        saveItem.setImage(file.getOriginalFilename());

        itemRepository.save(saveItem);
    }

    private Item setItemEntity(ItemForm itemForm) {
        Item item = new Item();
        item.setId(itemForm.getId());
        item.setName(itemForm.getName());
        item.setPrice(Integer.parseInt(itemForm.getPrice()));
        item.setStatus(itemForm.isStatus());
        item.setCreateDate(itemForm.getCreateDate());
        item.setUpdateDate(itemForm.getUpdateDate());

        return item;
    }

    public void saveLocalImage(String path, MultipartFile file){
        String fileName = file.getOriginalFilename();
        Path filePath = Paths.get(path + fileName);

        try {
            Files.copy(file.getInputStream(), filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}