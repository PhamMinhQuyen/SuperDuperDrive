package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.Users;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Credentials Controller
 */
@Controller
@RequestMapping("/credentials")
public class CredentialsController {

    /**
     * Bean {@link CredentialsService}
     */
    @Autowired
    private CredentialsService credentialsService;

    /**
     * Bean {@link UsersService}
     */
    @Autowired
    private UsersService usersService;

    /**
     * Insert Or Update Credentials
     *
     * @param authentication authentication
     * @param credential credential
     * @return save to DB
     */
    @PostMapping()
    public String saveOrUpdateCredentials(Authentication authentication, Credentials credential) {
        String userName = (String) authentication.getPrincipal();
        Users user = usersService.getUser(userName);

        if (credential.getCredentialId() > 0) {
            credentialsService.updateCredential(credential);
        } else {
            credentialsService.addCredential(credential, user.getUserId());
        }
        return "redirect:/result?success";
    }

    /**
     * Delete Credentials
     *
     * @param credentialId credentialId
     * @return Delete Credentials by credentialId
     */
    @GetMapping("/delete")
    public String deleteCredential(@RequestParam("id") int credentialId) {
        if (credentialId > 0) {
            credentialsService.deleteCredential(credentialId);
            return "redirect:/result?success";
        }
        return "redirect:/result?error";
    }
}
