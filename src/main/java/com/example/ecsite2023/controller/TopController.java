package com.example.ecsite2023.controller;

import com.example.ecsite2023.controller.form.ItemForm;
import com.example.ecsite2023.controller.form.LoginForm;
import com.example.ecsite2023.controller.form.SignupForm;
import com.example.ecsite2023.repository.entity.User;
import com.example.ecsite2023.service.ItemService;
import com.example.ecsite2023.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class TopController {
    @Autowired
    ItemService itemService;
    @Autowired
    UserService userService;
    @Autowired
    HttpSession session;

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
        itemService.saveItem(itemForm);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/login")
    public ModelAndView viewLogin() {
        ModelAndView mav = new ModelAndView();
        LoginForm loginForm = new LoginForm();
        mav.addObject("loginForm", loginForm);
        mav.setViewName("/login");
        return mav;
    }
    @PostMapping("/login")
    public ModelAndView executeLogin(@ModelAttribute("loginForm") LoginForm loginForm, BindingResult result) {
        ModelAndView mav = new ModelAndView();
        List<User> findUsers = userService.findUser(loginForm);
        if(findUsers.size() != 1){
            mav.addObject("loginForm", loginForm);
            mav.setViewName("/login");
            return mav;
        }
        session.setAttribute("loginUser", findUsers.get(0));
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/logout")
    public ModelAndView executeOut(){
        session.invalidate();
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/top");
        return mav;
    }

    @GetMapping("/signup")
    public ModelAndView viewSignup() {
        ModelAndView mav = new ModelAndView();
        SignupForm signupForm = new SignupForm();
        mav.addObject("signupForm", signupForm);
        mav.setViewName("/signup");
        return mav;
    }

    @PostMapping("/signup")
    public ModelAndView executeLogin(@ModelAttribute("signupForm") SignupForm signupForm, BindingResult result) {
        ModelAndView mav = new ModelAndView();
        signupForm.setCreateDate(new Date());
        signupForm.setUpdateDate(new Date());
        userService.createUser(signupForm);
        return new ModelAndView("redirect:/");
    }
}
