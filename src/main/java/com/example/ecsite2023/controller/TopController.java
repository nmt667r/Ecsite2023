package com.example.ecsite2023.controller;

import com.example.ecsite2023.controller.form.ItemForm;
import com.example.ecsite2023.repository.ItemRepository;
import com.example.ecsite2023.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

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
}
