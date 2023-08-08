package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UsersMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

/**
 * Users Service
 */
@Service
public class UsersService {

    private final HashService hashService;

    /**
     * Bean {@link UsersMapper}
     */
    @Autowired
    private UsersMapper usersMapper;

    public UsersService(HashService hashService) {
        this.hashService = hashService;
    }

    public boolean isUsernameAvailable(String username) {
        return usersMapper.getUser(username) == null;
    }

    public int createUser(Users user) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);
        return usersMapper.insertUser(new Users(null, user.getUsername(), encodedSalt, hashedPassword, user.getFirstName(), user.getLastName()));
    }

    public Users getUser(String username) {
        return usersMapper.getUser(username);
    }
}
