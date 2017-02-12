package com.ali.userfront.controller;

import com.ali.userfront.domain.User;
import com.ali.userfront.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FrontController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String home() {
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(Model model) {
        User user = new User();
        model.addAttribute(user);
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signupPost(@ModelAttribute User user, Model model) {
        if (userService.checkUserExists(user.getUsername(), user.getEmail())) {
            if (userService.checkUsernameExists(user.getUsername())) {
                model.addAttribute("usernameExists", true);
            }
            if (userService.checkEmailExists(user.getEmail())) {
                model.addAttribute("emailExists", true);
            }
            return "signup";
        }
        userService.save(user);
        return "redirect:/";
    }
}
