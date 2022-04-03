package com.x1.chan.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@ToString
@AllArgsConstructor
@Getter
@Setter
public class NaverLoginDTO implements Serializable {
    private String email;
    private String profileImage;
    private Long loginIdx;
    private String name;

    public NaverLoginDTO(){
    }
}
