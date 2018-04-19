package com.yuri.keepass.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Yuri on 2018/4/3.
 */

public class TestUtils {


    public static String SHA256(String text) {
        String result = null;

        if (text != null && text.length() > 0) {
            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                digest.update(text.getBytes());

                byte[] byteBuffer = digest.digest();

                StringBuffer buffer = new StringBuffer();
                for (int i = 0; i < byteBuffer.length; i++) {
                    String hex = Integer.toHexString(0xff & byteBuffer[i]);
                    if (hex.length() == 1) {
                        buffer.append(0);
                    }
                    buffer.append(hex);
                }

                result = buffer.toString();

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }

        return  result;
    }

    //073a9806fd00a08c61f9fc9517955b3e868394d1ae53c128bda65aa9c787
    public static boolean isValid(String hash) {
        String subString = hash.substring(0, 5);
//        System.out.println("subString:" + subString);

        try {
            int integer = Integer.valueOf(subString);
            if (integer == 0) {
                return  true;
            }
        } catch (NumberFormatException e) {

        }
        return  false;
    }

}
