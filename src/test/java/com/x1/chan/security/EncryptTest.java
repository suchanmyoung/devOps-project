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
            String salt2 = Salt.createSalt();
            String encryptSha256 = Encrypt.encryptSha256("hello", salt);
            String encryptSha257 = Encrypt.encryptSha256("hello", salt2);
            assertEquals(encryptSha256, encryptSha257);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void encryptTest2(){
        try {
            String salt = Salt.createSalt();

            String encryptSha256 = Encrypt.encryptSha256("hello", salt);
            String encryptSha257 = Encrypt.encryptSha256("hello", salt);
            String encryptSha258 = Encrypt.encryptSha256("hello", salt);
            assertEquals(encryptSha256, encryptSha257, encryptSha258);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}