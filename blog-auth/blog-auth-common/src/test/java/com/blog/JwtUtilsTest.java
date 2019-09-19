package com.blog;


import com.blog.entity.UserInfo;
import com.blog.utils.JwtUtils;
import com.blog.utils.RsaUtils;
import org.junit.Before;
import org.junit.Test;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author bystander
 * @date 2018/10/1
 */
public class JwtUtilsTest {

    private static final String publicKeyPath = "G:\\temp\\rsa\\rsa.pub";
    private static final String privateKeyPath = "G:\\temp\\rsa\\rsa.pri";

    private PrivateKey privateKey;
    private PublicKey publicKey;


    @Test
    public void testRsa() throws Exception {
        RsaUtils.generateKey(publicKeyPath, privateKeyPath, "234");
    }

    @Before
    public void testGetRsa() throws Exception {
        privateKey = RsaUtils.getPrivateKey(privateKeyPath);
        publicKey = RsaUtils.getPublicKey(publicKeyPath);
    }

    @Test
    public void generateToken() {
        //生成Token
        String s = JwtUtils.generateToken(new UserInfo("20", "Jack"), privateKey, 5);
        System.out.println("s = " + s);
    }


    @Test
    public void parseToken() {
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6MjAsInVzZXJuYW1lIjoiSmFjayIsImV4cCI6MTU2NzE1NzQ0M30.VNvZK-s4bEeAEKXBN52ri3rZ3LfCC3osxZu-2XeHXxVh4x-vIWayPjz69A1G0CpjNu8xmxiWNaAnNkAC1lnUCniAc2Kg9Qov0WmeKW2Vr0lHsJdtPapCiGsKox60EWDTk6D0Ky99oqlhrVwqvB71whDzxR4dR6q7QY9xPFI3qis";
        UserInfo userInfo = JwtUtils.getUserInfo(publicKey, token);
        System.out.println("id:" + userInfo.getId());
        System.out.println("name:" + userInfo.getName());
    }

    @Test
    public void parseToken1() {
    }

    @Test
    public void getUserInfo() {
    }

    @Test
    public void getUserInfo1() {
    }
}