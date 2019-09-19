package com.blog.util;

import java.util.Random;

public class CodeUtil {

    private static StringBuffer code = new StringBuffer();

    public static String produceCode() {
        for (int i = 0; i < 6; i++) {
            Random random = new Random();
            int num = random.nextInt(10);
            code.append(num);
        }
        return code.toString();
    }

    public static void main(String[] args) {
        System.out.println(produceCode());
    }
}
