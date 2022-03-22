package com.x1.chan.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Encrypt {

    public static String encryptSha256(String str, String salt) throws  Exception{

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(salt.getBytes(StandardCharsets.UTF_8));
        byte[] bytes = messageDigest.digest(str.getBytes(StandardCharsets.UTF_8));
        StringBuilder encStringBuilder = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            encStringBuilder.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
       return encStringBuilder.toString();
    }
}
