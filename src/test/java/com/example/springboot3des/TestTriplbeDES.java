package com.example.springboot3des;

import com.example.springboot3des.utils.TripleDESUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @ClassName TestTriplbeDES
 * @Author Jumy
 * @Description //TODO
 * @Date 2019/6/14 10:15
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTriplbeDES {
    @Test
    public void testByte() throws UnsupportedEncodingException {
        String key="abcdefghijklmnopqrstuvwx"; //key为加密密钥，长度为24字节
        //加密
        String input ="abcdefghijklmnopqrstuvwxyz";
        System.out.println("明文："+input);
        byte[] srcData=input.getBytes("utf-8");
        byte[] encryptData=TripleDESUtil.encrypt(key.getBytes("utf-8"),srcData);
        String output = Base64.getEncoder().encodeToString(encryptData);
        System.out.println("密文："+output);
        //解密
        System.out.println("密文："+output);
        byte[] decode = Base64.getDecoder().decode(output);
        byte[] decrypt = TripleDESUtil.decrypt(key.getBytes("utf-8"),decode);
        System.out.println("明文="+new String(decrypt));
    }
    @Test
    public void testString() throws UnsupportedEncodingException {
        String key="abcdefghijklmnopqrstuvwx"; //key为加密密钥，长度为24字节
        TripleDESUtil des=new TripleDESUtil();
        //加密
        String mData ="abcdefghijklmnopqrstuvwxyz";
        System.out.println("明文："+mData);
        String cData = TripleDESUtil.encrypt(key,mData);
        System.out.println("密文："+cData);
        //解密
        System.out.println("密文："+cData);
        String mmData = TripleDESUtil.decrypt(key, cData);
        System.out.println("明文："+mmData);
    }
}
