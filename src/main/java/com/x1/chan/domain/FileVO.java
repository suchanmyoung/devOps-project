package com.x1.chan.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
public class FileVO {
    private Long fileSeq;
    private String originalFileName;
    private String storedFileName;
    private String loginId;
    private String fileExt;
    private String filePath;

    public FileVO(){

    }
}
