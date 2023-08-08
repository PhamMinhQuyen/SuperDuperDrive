package com.udacity.jwdnd.course1.cloudstorage.model;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseFile {

    private int fileId;
    private String fileName;
    private String dataURL;
}
