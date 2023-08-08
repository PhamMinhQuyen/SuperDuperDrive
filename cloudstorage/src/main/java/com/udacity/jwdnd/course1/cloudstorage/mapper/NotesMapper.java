package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface NotesMapper {
    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES (#{note.noteTitle}, #{note.noteDescription}, #{userId})")
    public int insertNote(Notes note, int userId);

    @Select("SELECT * FROM NOTES")
    public List<Notes> findAll();

    @Select("SELECT * FROM NOTES WHERE userid = #{userId}")
    public List<Notes> findByUserId(int userId);

    @Delete("DELETE FROM NOTES WHERE noteid = #{noteId}")
    public int deleteNote(int noteId);

    @Update("UPDATE NOTES SET notetitle = #{noteTitle}, notedescription = #{noteDescription} WHERE noteid = #{noteId}")
    public int updateNote(Notes note);
}
