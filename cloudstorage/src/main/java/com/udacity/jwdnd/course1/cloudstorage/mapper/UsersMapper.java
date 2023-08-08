package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Users;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Users Mapper
 */
@Mapper
@Repository
public interface UsersMapper {
    /**
     * Get users information by username
     *
     * @param username username
     * @return Users
     */
    @Select("SELECT * FROM USERS WHERE username = #{username}")
    Users getUser(String username);

    /**
     *
     * @param user
     * @return
     */
    @Insert("INSERT INTO USERS (username, salt, password, firstname, lastname) VALUES(#{username}, #{salt}, #{password}, #{firstName}, #{lastName})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insertUser(Users user);
}
