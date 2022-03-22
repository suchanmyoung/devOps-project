package com.x1.chan.security;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class SaltTest {

    @Test
    public void SaltValueCheck(){
        String salt = Salt.createSalt();
        log.info(salt);
    }
}