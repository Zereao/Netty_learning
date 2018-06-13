package com.github.zereao;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * @author Zereao
 * @version 2018/06/13/10:38
 */
public class Demo03 {
    public static void main(String[] args) {
        try {
            new Demo03().transFrom();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void transFrom() throws Exception {
        RandomAccessFile fromFile = new RandomAccessFile("E:/nio-data.txt", "rw");
        FileChannel fromChannel = fromFile.getChannel();
        RandomAccessFile toFile = new RandomAccessFile("toFile.txt", "rw");
        FileChannel toChannel = toFile.getChannel();
        long position = 0;
        long count = fromChannel.size();
        toChannel.transferFrom(fromChannel, count, position);
    }

    public void transTo() throws Exception {
        RandomAccessFile fromFile = new RandomAccessFile("E:/nio-data.txt", "rw");
        FileChannel fromChannel = fromFile.getChannel();
        RandomAccessFile toFile = new RandomAccessFile("toFile.txt", "rw");
        FileChannel toChannel = toFile.getChannel();
        long position = 0;
        long count = fromChannel.size();
        fromChannel.transferTo(position, count, toChannel);
    }
}
