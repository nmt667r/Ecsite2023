package com.example.ecsite2023.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TopController {
    @GetMapping
    public ModelAndView top() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/top");
        return mav;
    }
}
