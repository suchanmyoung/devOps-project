package com.x1.chan.security;

import com.x1.chan.common.security.Salt;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class SaltTest {

    @Test
    public void SaltValueCheck(){
        String salt = Salt.createSalt();
        log.info(salt);
    }
}