package com.github.zereao.netty.demo03.marshalling.dto;

/**
 * @author Zereao
 * @version 2018/06/14/9:19
 */
public class Request {
    private int id;
    private String name;
    private String message;
    private byte[] attachment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public byte[] getAttachment() {
        return attachment;
    }

    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }
}
