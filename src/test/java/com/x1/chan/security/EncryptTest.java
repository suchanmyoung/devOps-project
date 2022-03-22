package com.x1.chan.security;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class EncryptTest {

    @Test
    public void encryptTest(){
        try {
            String salt = Salt.createSalt();
            String encryptSha256 = Encrypt.encryptSha256("hello", salt);
            log.info("salt 값은 = {}", salt);
            log.info("암호화된 문자열은 = {}", encryptSha256);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}