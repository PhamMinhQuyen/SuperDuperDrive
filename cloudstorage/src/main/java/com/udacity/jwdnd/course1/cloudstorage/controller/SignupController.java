package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Users;
import com.udacity.jwdnd.course1.cloudstorage.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/signup")
public class SignupController {

    @Autowired
    private UsersService usersService;

    @GetMapping()
    public String pageSignup () {
        return "signup";
    }

    @PostMapping()
    public String signupUser(@ModelAttribute Users users, Model model, RedirectAttributes redirectAttributes) {
        String signupError = null;

        if (!usersService.isUsernameAvailable(users.getUsername())) {
            signupError = "The username already exists.";
        }

        if (signupError == null) {
            int rowsAdded = usersService.createUser(users);
            if (rowsAdded < 0) {
                signupError = "There was an error signing you up. Please try again.";
            }
            redirectAttributes.addFlashAttribute("signupSuccess", "You successfully signed up!");
            return "redirect:login";
        }
            // change request
//        if (signupError == null) {
//            model.addAttribute("signupSuccess", true);
//        } else {
//            model.addAttribute("signupError", signupError);
//        }

        if (signupError != null) {
            model.addAttribute("signupError", signupError);
        }

        return "signup";
    }
}
