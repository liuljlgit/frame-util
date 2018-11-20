package com.cloud.common.consistencyhash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * hash函数，根据key生成hash
 */
public class HashFunction {

    /**
     * 用MD5压缩算法，生成hashmap的key值
     * @param key
     * @return
     * @throws NoSuchAlgorithmException
     */
    public String hash(String key) {
        String s = null;

        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(key.getBytes());
            // MD5的结果：128位的长整数，存放到tmp中
            byte tmp[] = md.digest();
            s = toHex(tmp);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return s;
    }

    /**
     * 将二进制的长整数转换为16进制的数字，以字符串表示
     * @param bytes
     * @return
     */
    public String toHex(byte[] bytes) {
        // hexDigits的用处是：将任意字节转换为十六进制的数字
        char hexDigits[] = "0123456789abcdef".toCharArray();
        // MD5的结果：128位的长整数，用字节表示就是16个字节，用十六进制表示的话，使用两个字符，所以表示成十六进制需要32个字符
        char str[] = new char[16 * 2];
        int k = 0;
        for (int i = 0; i < 16; i++) {
            byte b = bytes[i];
            // 逻辑右移4位，与0xf（00001111）相与，为高四位的值，然后再hexDigits数组中找到对应的16进制值
            str[k++] = hexDigits[b >>> 4 & 0xf];
            // 与0xf（00001111）相与，为低四位的值，然后再hexDigits数组中找到对应的16进制值
            str[k++] = hexDigits[b & 0xf];

        }
        String s = new String(str);
        return s;
    }

}
