package com.x1.chan.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class LoginLog {
    private Long logSeq;
    private Date regDate;
    private String login_id;
    private LoginDescription loginDescription;

    public LoginLog(){
    }
}


