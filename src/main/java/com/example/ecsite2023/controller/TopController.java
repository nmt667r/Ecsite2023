package com.example.ecsite2023.controller;

import com.example.ecsite2023.controller.form.ItemForm;
import com.example.ecsite2023.repository.ItemRepository;
import com.example.ecsite2023.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
public class TopController {
    @Autowired
    ItemService itemService;
    @GetMapping
    public ModelAndView top() {
        ModelAndView mav = new ModelAndView();
        List<ItemForm> items = itemService.findAllItems();
        mav.addObject("items", items);
        mav.setViewName("/top");
        return mav;
    }

    @GetMapping("/item")
    public ModelAndView viewItem() {
        ModelAndView mav = new ModelAndView();
        ItemForm itemForm = new ItemForm();
        mav.addObject("itemForm", itemForm);
        mav.setViewName("/item");
        return mav;
    }
    @PostMapping("/item")
    public ModelAndView addItem(@ModelAttribute("itemForm") ItemForm itemForm, BindingResult result) {
        ModelAndView mav = new ModelAndView();
        itemForm.setCreateDate(new Date());
        itemForm.setUpdateDate(new Date());
        // 投稿をテーブルに格納
        itemService.saveItem(itemForm);
        // rootへリダイレクト
        return new ModelAndView("redirect:/");
    }
}
