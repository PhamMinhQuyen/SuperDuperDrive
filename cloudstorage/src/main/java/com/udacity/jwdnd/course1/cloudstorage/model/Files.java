package com.udacity.jwdnd.course1.cloudstorage.model;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Files {
    private Integer fileId;
    private String fileName;
    private String contentType;
    private String fileSize;
    private byte[] fileData;
}
