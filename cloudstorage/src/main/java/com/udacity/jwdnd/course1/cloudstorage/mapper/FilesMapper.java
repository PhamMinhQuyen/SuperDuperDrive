package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FilesMapper {

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, filedata, userid) VALUES (#{file.fileName}, #{file.contentType}, " +
            "#{file.fileSize}, #{file.fileData}, #{userId})")
    public int insertFile(Files file, int userId);

    @Select("SELECT * FROM FILES WHERE userid = #{userId}")
    public List<Files> findByUserId(int userId);

    @Delete("DELETE FROM FILES WHERE fileid = #{fileId}")
    public int deleteFile(int fileId);

    @Select("SELECT * FROM FILES WHERE filename like #{fileName}")
    public List<Files> findByName(String fileName);
}
