package com.x1.chan.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
@Setter
public class NaverLoginDTO {
    private String email;
    private String profileImage;
    private Long loginIdx;
    private String name;

    public NaverLoginDTO(){
    }
}
