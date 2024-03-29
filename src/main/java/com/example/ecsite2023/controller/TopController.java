package com.example.ecsite2023.controller;

import com.example.ecsite2023.controller.form.CartForm;
import com.example.ecsite2023.controller.form.ItemForm;
import com.example.ecsite2023.controller.form.UserForm;
import com.example.ecsite2023.repository.entity.User;
import com.example.ecsite2023.service.CartService;
import com.example.ecsite2023.service.ItemService;
import com.example.ecsite2023.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
    public ModelAndView viewTop() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("loginUser",session.getAttribute("loginUser"));
        List<ItemForm> items = itemService.findAllItems();
        mav.addObject("items", items);
        mav.setViewName("/top");
        return mav;
    }

    @GetMapping("/itemAdd")
    public ModelAndView viewItem() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("loginUser",session.getAttribute("loginUser"));
        ItemForm itemForm = new ItemForm();
        mav.addObject("ItemForm", itemForm);
        mav.setViewName("/itemAdd");
        return mav;
    }

    @PostMapping("/itemAdd")
    public ModelAndView addItem(@ModelAttribute("ItemForm") @Validated ItemForm itemForm, @RequestParam MultipartFile file, BindingResult result) {

        if (result.hasErrors()) {
            ModelAndView mav = new ModelAndView();
            mav.addObject("loginUser",session.getAttribute("loginUser"));
            mav.addObject("ItemForm", itemForm);
            mav.setViewName("/itemAdd");
            return mav;
        }
        itemForm.setCreateDate(new Date());
        itemForm.setUpdateDate(new Date());
        itemService.saveLocalImage("image/", file);
        itemService.saveItem(itemForm, file);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/item/{id}")
    public ModelAndView viewItemDetail(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("loginUser",session.getAttribute("loginUser"));
        ItemForm itemForm = itemService.findItemById(id).get(0);
        CartForm cartForm = new CartForm();
        cartForm.setItemId(itemForm.getId());
        cartForm.setName(itemForm.getName());
        cartForm.setPrice(Integer.parseInt(itemForm.getPrice()));
        mav.addObject("cartForm", cartForm);
        mav.setViewName("/itemAbout");
        return mav;
    }

    @GetMapping("/login")
    public ModelAndView viewLogin() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("loginUser",session.getAttribute("loginUser"));
        UserForm userForm = new UserForm();
        mav.addObject("UserForm", userForm);
        mav.setViewName("/login");
        return mav;
    }

    @PostMapping("/login")
    public ModelAndView executeLogin(@ModelAttribute("UserForm") @Validated UserForm userForm, BindingResult result) {
        ModelAndView mav = new ModelAndView();
        if (result.hasErrors()) {
            mav.addObject("loginUser",session.getAttribute("loginUser"));
            mav.addObject("UserForm", userForm);
            mav.setViewName("/login");
            return mav;
        }
        List<User> findUsers = userService.findUser(userForm);
        if (findUsers.size() != 1) {
            result.addError(new FieldError(result.getObjectName(), "account", null, false, null, null, "アカウントとパスワードの組み合わせが不適切です"));
            mav.addObject("UserForm", userForm);
            mav.setViewName("/login");
            return mav;
        }
        session.setAttribute("loginUser", findUsers.get(0));
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/logout")
    public ModelAndView Logout() {
        session.invalidate();
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/signup")
    public ModelAndView viewSignup() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("loginUser",session.getAttribute("loginUser"));
        UserForm userForm = new UserForm();
        mav.addObject("UserForm", userForm);
        mav.setViewName("/signup");
        return mav;
    }

    @PostMapping("/signup")
    public ModelAndView executeSignup(@ModelAttribute("UserForm") @Validated UserForm userForm, BindingResult result) {
        if (!result.hasFieldErrors("password") && !result.hasFieldErrors("passwordConfirm")) {
            if(!userForm.getPassword().equals(userForm.getPasswordConfirm())){
                result.addError(new FieldError(result.getObjectName(), "password", null, false, null, null, "パスワードと確認用パスワードが一致しません"));
            }
        }

        if (result.hasErrors()) {
            ModelAndView mav = new ModelAndView();
            mav.addObject("loginUser",session.getAttribute("loginUser"));
            mav.addObject("UserForm", userForm);
            mav.setViewName("/signup");
            return mav;
        } else if (!userForm.getPassword().equals(userForm.getPasswordConfirm())) {

            ModelAndView mav = new ModelAndView();
            mav.addObject("loginUser",session.getAttribute("loginUser"));
            mav.addObject("UserForm", userForm);
            mav.setViewName("/signup");
            return mav;
        }

        userForm.setCreateDate(new Date());
        userForm.setUpdateDate(new Date());
        userService.createUser(userForm);
        return new ModelAndView("redirect:/");
    }

    @PostMapping("/addCart")
    public ModelAndView executeAddCart(@ModelAttribute("cartForm") CartForm cartForm, BindingResult result, @RequestParam Integer amount) {
        Integer userId = ((User) session.getAttribute("loginUser")).getId();
        cartForm.setUserId(userId);
        cartService.saveItem(cartForm);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/editCart")
    public ModelAndView viewCart() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("loginUser",session.getAttribute("loginUser"));
        Integer userId = ((User) session.getAttribute("loginUser")).getId();
        List<CartForm> cartForm = cartService.findCartByUserId(userId);
        mav.addObject("cartForm", cartForm);
        mav.setViewName("/cart");
        return mav;
    }

    @PostMapping("/editCart")
    public ModelAndView executeEditCart(@ModelAttribute("cartForm") CartForm cartForm) {
        cartForm.setUpdateDate(new Date());
        Integer userId = ((User) session.getAttribute("loginUser")).getId();
        cartForm.setUserId(userId);
        cartService.saveItem(cartForm);
        return new ModelAndView("redirect:/");
    }

    @PostMapping("/deleteCart")
    public ModelAndView executeDeleteCart(@RequestParam Integer id) {
        cartService.deleteItem(id);
        return new ModelAndView("redirect:/");
    }

    @PostMapping("/purchase")
    public ModelAndView executePurchase() {
        Integer userId = ((User) session.getAttribute("loginUser")).getId();
        cartService.deleteAllItem(userId);
        return new ModelAndView("redirect:/");
    }

}