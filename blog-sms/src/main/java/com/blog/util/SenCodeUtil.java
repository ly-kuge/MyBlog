package com.blog.util;

import org.apache.http.HttpResponse;

import java.util.HashMap;
import java.util.Map;

public class SenCodeUtil {
    public static String sendCode(String smsHost, String smsPath, String myAppcode, String phone,String code) {
        String host = smsHost;
        String path = smsPath;
        String method = "POST";
        String appcode = myAppcode;
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("content", "【创信】你的验证码是："+code+"，5分钟内有效！");
        querys.put("mobile", phone);
        Map<String, String> bodys = new HashMap<String, String>();

        try {
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}


