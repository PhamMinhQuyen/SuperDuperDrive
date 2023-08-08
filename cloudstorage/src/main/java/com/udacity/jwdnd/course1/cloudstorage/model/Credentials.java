package com.udacity.jwdnd.course1.cloudstorage.model;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Credentials {
    private Integer credentialId;
    private String url;
    private String userName;
    private String key;
    private String password;
}
