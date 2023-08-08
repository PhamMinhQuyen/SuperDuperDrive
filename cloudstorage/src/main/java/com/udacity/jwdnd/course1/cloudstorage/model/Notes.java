package com.udacity.jwdnd.course1.cloudstorage.model;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notes {

    private Integer noteId;
    private String noteTitle;
    private String noteDescription;
}
