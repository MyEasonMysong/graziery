package com.sxkj.util;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * com.sxkj.util.MD5
 *
 * @author zwd
 * @Description MD5
 * @Date Create in 2018-07-13 0013 17:16
 * @Modified
 */
public class MD5 {
    public static String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        str +="jsyjb";
        return Base64.getEncoder().encodeToString(MessageDigest.getInstance("MD5").digest(str.getBytes("utf-8")));
    }

    /*public static void main(String[] args) throws Exception{
        System.out.println(EncoderByMd5("tom1"));
    }*/
}
