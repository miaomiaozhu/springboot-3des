package com.example.springboot3des.utils;

/**
 * @ClassName TripleDES
 * @Author Jumy
 * @Description //TODO
 * @Date 2019/6/11 17:01
 * @Version 1.0
 **/







import org.apache.commons.codec.binary.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


public class TripleDESUtil {

    //key为加密密钥，长度为24字节
    private static final String Algorithm = "DESede"; //定义 加密算法,可用 DES,DESede,Blowfish
    //加密,输入输出均为二进制
    public static byte[] encrypt(byte[] keybyte,byte[] data){
        try {
            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, deskey);
            return cipher.doFinal(data);
        } catch (Exception ex) {
            //加密失败，打日志
            ex.printStackTrace();
        }
        return null;
    }
    //解密,输入输出均为二进制
    public static byte[] decrypt(byte[] keybyte,byte[] data){
        try {
            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE,deskey);
            return cipher.doFinal(data);
        } catch (Exception ex) {
            //解密失败，打日志
            ex.printStackTrace();
        }
        return null;
    }
    //加密,输入输出均为字符串
    public static String encrypt(String key,String mData){
        try {
            byte[] keybyte = key.getBytes("utf-8");
            byte[] data = mData.getBytes("utf-8");
            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, deskey);
            String cData = Base64.encodeBase64String(cipher.doFinal(data));
            return cData;
        } catch (Exception ex) {
            //加密失败，打日志
            ex.printStackTrace();
        }
        return null;
    }
    //解密,输入输出均为字符串
    public static String decrypt(String key,String cData){
        try {
            byte[] keybyte = key.getBytes("utf-8");
            byte[] data = Base64.decodeBase64(cData);
            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE,deskey);
            String mData = new String(cipher.doFinal(data));
            return mData;
        } catch (Exception ex) {
            //解密失败，打日志
            ex.printStackTrace();
        }
        return null;
    }

}
