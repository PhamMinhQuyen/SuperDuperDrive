package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Users;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Home Controller
 */
@Controller
public class HomeController {

    /**
     * Bean {@link NotesService}
     */
    @Autowired
    private NotesService notesService;

    /**
     * Bean {@link UsersService}
     */
    @Autowired
    private UsersService usersService;

    /**
     * Bean {@link FilesService}
     */
    @Autowired
    private FilesService filesService;

    /**
     * Bean {@link CredentialsService}
     */
    @Autowired
    private CredentialsService credentialsService;

    @Autowired
    private EncryptionService encryptionService;

    @GetMapping (value ="/home")
    public String getHomePage(Authentication authentication, Model model) throws Exception {
        String userName = (String) authentication.getPrincipal();
        Users user = usersService.getUser(userName);
        model.addAttribute("files", filesService.getAllFiles(user.getUserId()));
        model.addAttribute("notes", notesService.getListNotes(user.getUserId()));
        model.addAttribute("credentials", credentialsService.getAllCredentials(user.getUserId()));
        model.addAttribute("encryptionService", encryptionService);
        return "home";
    }
    @GetMapping("/result")
    public String result(){
        return "result";
    }
}
