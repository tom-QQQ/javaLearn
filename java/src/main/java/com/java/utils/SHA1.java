package com.java.utils;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
 
/**
 * @author yang
 * @Title: SHA1.java
 * @Package 
 * @Description: sha1算法
 * @date 2019年03月20日 10:34
 */
public class SHA1 {

    /**
     * 计算大文件 md5获取getMD5(); SHA1获取getSha1() CRC32获取 getCRC32()
     */
    protected static char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /**
     * 计算SHA1码
     *
     * @return
     * @throws OutOfMemoryError
     * @throws IOException
     * @throws NoSuchAlgorithmException//FileInputStream in,Long size
     */
    public static String getSha1(File file) throws OutOfMemoryError, IOException, NoSuchAlgorithmException {
        MessageDigest messagedigest = MessageDigest.getInstance("SHA-1");
        FileInputStream in = new FileInputStream(file);
        FileChannel ch = in.getChannel();
        MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
        messagedigest.update(byteBuffer);
        return bufferToHex(messagedigest.digest());
    }

    /**
     * @Description 计算二进制数据     * @return String
     */
    private static String bufferToHex(byte bytes[]) {
        return bufferToHex(bytes, 0, bytes.length);
    }


    private static String bufferToHex(byte bytes[], int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }

    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        char c0 = hexDigits[(bt & 0xf0) >> 4];
        char c1 = hexDigits[bt & 0xf];
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }

    //一般用于防篡改签名使用
    public static void main(String[] args) throws Exception {
        File file2 = new File("D:\\learnBySelf\\maven\\dubbo备份\\dubbo-2.6.5\\dubbo-2.6.5.jar");
        String sha1 = SHA1.getSha1(file2);
        System.out.println(sha1);
    }
}
