package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NotesMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Notes Service
 */
@Service
public class NotesService {

    /**
     * Bean {@link NotesMapper}
     */
    @Autowired
    private NotesMapper notesMapper;

    public void createNote(Notes note, int userid) {
        notesMapper.insertNote(note, userid);
    }

    public List<Notes> getListNotes(int userid) throws Exception {
        List<Notes> notes = notesMapper.findByUserId(userid);
        if (notes == null) {
            throw new Exception();
        }
        return notes;
    }

    public void deleteNote(int noteId) {
        notesMapper.deleteNote(noteId);
    }

    public void updateNote(Notes note) {
        notesMapper.updateNote(note);
    }
}
