package com.x1.chan.common.security;

import com.x1.chan.domain.Member;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * Digest : 해시에 의해 암호화된 데이터
 */

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

    public static Member setEncryptPassword(Member member) {
        String encryptPassword = null;
        String salt = null;
        try {
            salt = Salt.createSalt();
            encryptPassword = Encrypt.encryptSha256(member.getPassword(), salt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        member.setSalt(salt);
        member.setPassword(encryptPassword);
        return member;
    }

    public static String setEncryptPassword(String password, String salt) {
                try {
            password = Encrypt.encryptSha256(password, salt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return password;
    }



}
