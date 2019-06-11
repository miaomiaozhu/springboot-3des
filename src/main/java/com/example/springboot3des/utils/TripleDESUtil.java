package com.example.springboot3des.utils;

/**
 * @ClassName TripleDES
 * @Author Jumy
 * @Description //TODO
 * @Date 2019/6/11 17:01
 * @Version 1.0
 **/

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class TripleDESUtil {

    //key 根据实际情况对应的修改
    private final byte[] keybyte="abcdefghijklmnopqrstuvwx".getBytes(); //keybyte为加密密钥，长度为24字节
    private static final String Algorithm = "DESede"; //定义 加密算法,可用 DES,DESede,Blowfish
    private SecretKey deskey;
    //生成密钥
    public TripleDESUtil(){
        deskey = new SecretKeySpec(keybyte, Algorithm);
    }
    //加密
    public byte[] encrypt(byte[] data){
        try {
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, deskey);
            return cipher.doFinal(data);
        } catch (Exception ex) {
            //加密失败，打日志
            ex.printStackTrace();
        }
        return null;
    }
    //解密
    public byte[] decrypt(byte[] data){
        try {
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE,deskey);
            return cipher.doFinal(data);
        } catch (Exception ex) {
            //解密失败，打日志
            ex.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        TripleDESUtil des=new TripleDESUtil();
        //加密
        String input ="abc";
        System.out.println("明文："+input);
        byte[] srcData=input.getBytes("utf-8");
        byte[] encryptData=des.encrypt(srcData);
        System.out.println(new String(encryptData));
        String output = Base64.getEncoder().encodeToString(encryptData);
        System.out.println("密文="+output);
        //解密
        System.out.println("密文="+output);
        byte[] decode = Base64.getDecoder().decode(output);
        System.out.println(new String(decode));
        byte[] decrypt = des.decrypt(decode);
        System.out.println("明文="+new String(decrypt));
    }
}
