package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  Credentials Service
 */
@Service
public class CredentialsService {
    /**
     * Bean {@link CredentialsMapper}
     */
    @Autowired
    private CredentialsMapper credentialsMapper;

    /**
     * Bean {@link EncryptionService}
     */
    @Autowired
    private EncryptionService encryptionService;

    private Credentials encryptPassword(Credentials credential) {
        String key = RandomStringUtils.random(16, true, true);
        credential.setKey(key);
        credential.setPassword(encryptionService.encryptValue(credential.getPassword(), key));
        return credential;
    }

    public Credentials decryptPassword(Credentials credential) {
        credential.setPassword(encryptionService.decryptValue(credential.getPassword(), credential.getKey()));
        return credential;
    }

    public List<Credentials> getAllCredentials(int userId) throws Exception {
        List<Credentials> credentials = credentialsMapper.findByUserId(userId);
        if (credentials == null) {
            throw new Exception();
        }
        return credentials;
    }

    public void addCredential(Credentials credential, int userId) {
        credentialsMapper.insertCredentials(encryptPassword(credential), userId);
    }

    public void updateCredential(Credentials credential) {
        credentialsMapper.updateCredentials(encryptPassword(credential));
    }

    public void deleteCredential(int credentialId) {
        credentialsMapper.deleteCredentials(credentialId);
    }
}
