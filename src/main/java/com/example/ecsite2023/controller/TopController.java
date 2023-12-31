package com.example.ecsite2023.controller;

import com.example.ecsite2023.controller.form.CartForm;
import com.example.ecsite2023.controller.form.ItemForm;
import com.example.ecsite2023.controller.form.LoginForm;
import com.example.ecsite2023.controller.form.SignupForm;
import com.example.ecsite2023.repository.entity.User;
import com.example.ecsite2023.service.CartService;
import com.example.ecsite2023.service.ItemService;
import com.example.ecsite2023.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TopController {
    private final ItemService itemService;
    private final UserService userService;
    private final CartService cartService;
    private final HttpSession session;

    @GetMapping
    public ModelAndView top() {
        ModelAndView mav = new ModelAndView();
        List<ItemForm> items = itemService.findAllItems();
        mav.addObject("items", items);
        mav.setViewName("/top");
        return mav;
    }

    @GetMapping("/itemAdd")
    public ModelAndView viewItem() {
        ModelAndView mav = new ModelAndView();
        ItemForm itemForm = new ItemForm();
        mav.addObject("itemForm", itemForm);
        mav.setViewName("/itemAdd");
        return mav;
    }

    @PostMapping("/itemAdd")
    public ModelAndView addItem(@ModelAttribute("itemForm") ItemForm itemForm, BindingResult result) {
        ModelAndView mav = new ModelAndView();
        itemForm.setCreateDate(new Date());
        itemForm.setUpdateDate(new Date());
        itemService.saveItem(itemForm);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/item/{id}")
    public ModelAndView viewItemAbout(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView();
        ItemForm itemForm = itemService.findByItem(id).get(0);
        CartForm cartForm = new CartForm();
        cartForm.setItemId(itemForm.getId());
        cartForm.setName(itemForm.getName());
        cartForm.setPrice(itemForm.getPrice());
        mav.addObject("cartForm", cartForm);
        mav.setViewName("/itemAbout");
        return mav;
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
        if (findUsers.size() != 1) {
            mav.addObject("loginForm", loginForm);
            mav.setViewName("/login");
            return mav;
        }
        session.setAttribute("loginUser", findUsers.get(0));
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/logout")
    public ModelAndView executeOut() {
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

    @PostMapping("/addCart")
    public ModelAndView executeAddCart(@ModelAttribute("cartForm") CartForm cartForm, BindingResult result, @RequestParam Integer amount) {
        ModelAndView mav = new ModelAndView();
        User user = (User) session.getAttribute("loginUser");
        cartForm.setUserId(user.getId());
        cartService.saveItem(cartForm);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/editCart")
    public ModelAndView viewCart() {
        ModelAndView mav = new ModelAndView();
        User user = (User) session.getAttribute("loginUser");
        List<CartForm> cartForm = cartService.findByCart(user.getId());
        mav.addObject("cartForm", cartForm);
        mav.setViewName("/cart");
        return mav;
    }

    @PostMapping("/editCart")
    public ModelAndView executeEditCart(@ModelAttribute("cartForm") CartForm cartForm) {
        ModelAndView mav = new ModelAndView();
        cartForm.setUpdateDate(new Date());
        User user = (User) session.getAttribute("loginUser");
        cartForm.setUserId(user.getId());
        cartService.saveItem(cartForm);
        return new ModelAndView("redirect:/");
    }

    @PostMapping("/deleteCart")
    public ModelAndView executeDeleteCart(@RequestParam Integer id) {
        ModelAndView mav = new ModelAndView();
        cartService.deleteItem(id);
        return new ModelAndView("redirect:/");
    }

    @PostMapping("/purchase")
    public ModelAndView executePurchase() {
        ModelAndView mav = new ModelAndView();
        User user = (User) session.getAttribute("loginUser");
        cartService.deleteAllItem(user.getId());
        return new ModelAndView("redirect:/");
    }

}