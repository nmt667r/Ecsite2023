package com.example.ecsite2023.service;

import com.example.ecsite2023.controller.form.ItemForm;
import com.example.ecsite2023.repository.ItemRepository;
import com.example.ecsite2023.repository.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ItemService {
    @Autowired
    ItemRepository itemRepository;
    public List<ItemForm> findAllItems() {
        List<Item> results = itemRepository.findAllByOrderByIdDesc();
        return setItemForm(results);
    }

    private List<ItemForm> setItemForm(List<Item> results) {
        List<ItemForm> items = new ArrayList<>();

        for (int i = 0; i < results.size(); i++) {
            ItemForm Item = new ItemForm();
            Item result = results.get(i);
            Item.setId(result.getId());
            Item.setName(result.getName());
            Item.setPrice(result.getPrice());
            Item.setImage(result.getImage());
            Item.setStatus(result.isStatus());
            items.add(Item);

        }
        return items;
    }

    public void saveItem(ItemForm itemForm) {
        Item saveItem = setItemEntity(itemForm);
        itemRepository.save(saveItem);
    }

    private Item setItemEntity(ItemForm itemForm) {
        Item item = new Item();
        item.setId(itemForm.getId());
        item.setName(itemForm.getName());
        item.setPrice(itemForm.getPrice());
        item.setImage(itemForm.getImage());
        item.setStatus(itemForm.isStatus());
        item.setCreateDate(itemForm.getCreateDate());
        item.setUpdateDate(itemForm.getUpdateDate());
        return item;
    }
}