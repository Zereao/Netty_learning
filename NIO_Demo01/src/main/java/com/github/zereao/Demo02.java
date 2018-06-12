package com.github.zereao;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Zereao
 * @version 2018/06/12/19:03
 */
@SuppressWarnings("WeakerAccess")
public class Demo02 {
    public static void main(String[] args) {
        try {
            new Demo02().useOfBuffer2();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void useOfBuffer() throws Exception {
        RandomAccessFile aFile = new RandomAccessFile("E:/nio-data.txt", "rw");
        FileChannel inChannel = aFile.getChannel();
        //create buffer with capacity of 48 bytes
        ByteBuffer buf = ByteBuffer.allocate(48);
        int bytesRead = inChannel.read(buf); //read into buffer.
        while (bytesRead != -1) {
            buf.flip();  //make buffer ready for read
            while (buf.hasRemaining()) {
                System.out.print((char) buf.get()); // read 1 byte at a time
            }
            buf.clear(); //make buffer ready for writing
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
    }

    public void useOfBuffer2() {
        ByteBuffer buffer = ByteBuffer.allocate(512);
        buffer.put("ceshi".getBytes());
        buffer.flip();
        while (buffer.hasRemaining()) {
            System.out.print((char) (buffer.get()));
        }
        System.out.println();

        buffer.rewind();
        byte[] a = new byte[100];
        int num = 0;
        while (buffer.hasRemaining()) {
            a[num] = buffer.get();
            num++;
        }
        System.out.println(new String(a));
    }
}
