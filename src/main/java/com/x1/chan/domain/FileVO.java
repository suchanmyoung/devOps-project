package com.x1.chan.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FileVO {
    private String originalFileName;
    private String storedFileName;
    private Long fileSize;
    private String loginId;
    private String fileExt;
    private String filePath;

    public FileVO(){

    }
}
