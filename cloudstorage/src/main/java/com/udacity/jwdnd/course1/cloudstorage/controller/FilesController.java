package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Users;
import com.udacity.jwdnd.course1.cloudstorage.services.FilesService;
import com.udacity.jwdnd.course1.cloudstorage.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/files")
public class FilesController {

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
     * Save file
     *
     * @param authentication authentication
     * @param fileUpload fileUpload
     * @return save file to DB
     * @throws IOException
     */
    @PostMapping()
    public String saveFile(Authentication authentication, MultipartFile fileUpload) throws IOException {
        String userName = (String) authentication.getPrincipal();
        Users user = usersService.getUser(userName);

        if (fileUpload.isEmpty()) {
            return "redirect:/result?error";
        }
        filesService.addFile(fileUpload, user.getUserId());
        return "redirect:/result?success";
    }

    /**
     * Delete file
     *
     * @param fileId fileId
     * @return delete file by fileId
     */
    @GetMapping("/delete")
    public String deleteNote(@RequestParam("id") int fileId) {
        if (fileId > 0) {
            filesService.deleteFile(fileId);
            return "redirect:/result?success";
        }
        return "redirect:/result?error";
    }
}
