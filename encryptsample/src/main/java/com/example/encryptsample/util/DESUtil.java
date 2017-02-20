package com.example.encryptsample.util;

import android.util.Log;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * DES（Data Encryption Standard，数据加密标准，对称加密算法）
 * 生成密钥
 * DES加密
 * DES解密
 */
public class DESUtil {
    private DESUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    private static byte[] key;

    static {
        try {
            key = initKey();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("DESUtil", "des initKey error " + e.getMessage());
        }
    }

    /**
     * 加密
     *
     * @param dataStr 需要加密的数据
     * @return 加密后的数据
     */
    public static String encrypt(String dataStr) {
        try {
            byte[] data = dataStr.getBytes();
            byte[] encryptData = encrypt(data, key);
            return ConvertUtils.bytes2HexString(encryptData);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("DESUtil", "des encrypt error " + e.getMessage());
        }
        return null;
    }

    /**
     * 解密
     *
     * @param dataStr 需要解密的数据
     * @return 解密后的数据
     */
    public static String decrypt(String dataStr) {
        try {
            byte[] data = ConvertUtils.hexString2Bytes(dataStr);
            byte[] decryptData = decrypt(data, key);
            return new String(decryptData);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("DESUtil", "des decrypt error " + e.getMessage());
        }
        return null;
    }

    /*
     * 生成密钥
     */
    private static byte[] initKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("DES");      //密钥生成器
        keyGen.init(56);                                            //初始化密钥生成器
        SecretKey secretKey = keyGen.generateKey();                 //生成密钥
        return secretKey.getEncoded();                              //密钥字节数组
    }

    /*
     * DES 加密
     */
    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key, "DES");        //恢复密钥

        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding"); //Cipher完成加密或解密工作类
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);                //对Cipher初始化，加密模式
        byte[] cipherBytes = cipher.doFinal(data);                  //加密data
        return cipherBytes;
    }

    /*
     * DES 解密
     */
    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key, "DES");        //恢复密钥

        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding"); //Cipher完成加密或解密工作类
        cipher.init(Cipher.DECRYPT_MODE, secretKey);                //对Cipher初始化，解密模式
        byte[] plainBytes = cipher.doFinal(data);                   //解密data
        return plainBytes;
    }
}
