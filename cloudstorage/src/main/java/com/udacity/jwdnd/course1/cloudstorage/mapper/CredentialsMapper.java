package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CredentialsMapper {

    @Select("SELECT * FROM CREDENTIALS")
    List<Credentials> findAll();
    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userId}")
    public List<Credentials> findByUserId(int userId);

    @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userid) VALUES (#{credential.url}, " +
            "#{credential.userName}, #{credential.key}, #{credential.password}, #{userId})")
    public int insertCredentials(Credentials credential, int userId);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialId}")
    public int deleteCredentials(int credentialId);

    @Update("UPDATE CREDENTIALS SET url = #{url}, username = #{userName}, key = #{key}, " +
            "password = #{password} WHERE credentialid = #{credentialId}")
    public int updateCredentials(Credentials credential);
}
