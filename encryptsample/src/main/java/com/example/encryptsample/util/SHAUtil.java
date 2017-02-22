package com.example.encryptsample.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * SHA（Secure Hash Algorithm，安全散列算法）
 * SHA-1 加密 不可逆
 */
public class SHAUtil {
    private SHAUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static String encryptSHA(String dataStr) {
        return encryptSHA(dataStr.getBytes());
    }

    /**
     * SHA-512 加密
     *
     * @param data
     * @return
     */
    private static String encryptSHA(byte[] data) {
        MessageDigest sha = null;
        try {
            sha = MessageDigest.getInstance("SHA-512");
            sha.update(data);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] resultBytes = sha.digest();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < resultBytes.length; i++) {
            if (Integer.toHexString(0xFF & resultBytes[i]).length() == 1) {
                builder.append("0").append(
                        Integer.toHexString(0xFF & resultBytes[i]));
            } else {
                builder.append(Integer.toHexString(0xFF & resultBytes[i]));
            }
        }
        return builder.toString();
    }
}
