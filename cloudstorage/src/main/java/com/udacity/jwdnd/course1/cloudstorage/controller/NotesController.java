package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.model.Users;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import com.udacity.jwdnd.course1.cloudstorage.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/notes")
public class NotesController {

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
     * Create or Update Note
     *
     * @param authentication authentication
     * @param note Notes
     * @return save to DB
     */
    @PostMapping()
    public String createOrUpdateNote(Authentication authentication, Notes note) {
        String userName = (String) authentication.getPrincipal();
        Users user = usersService.getUser(userName);

        if (note.getNoteId() > 0) {
            notesService.updateNote(note);
        } else {
            notesService.createNote(note, user.getUserId());
        }
        return "redirect:/result?success";
    }

    @GetMapping("/delete")
    public String deleteNote(@RequestParam("id") int noteId) {
        if (noteId > 0) {
            notesService.deleteNote(noteId);
            return "redirect:/result?success";
        }
        return "redirect:/result?error";
    }
}
