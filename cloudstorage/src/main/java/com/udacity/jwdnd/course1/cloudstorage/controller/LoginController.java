package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Login Controller
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    /**
     * Bean {@link UsersService}
     */
    @Autowired
    private UsersService usersService;

    /**
     * Truy cập vô trang login
     *
     * @return login page.
     */
    @GetMapping()
    public String loginPage() {
        return "login";
    }
}
