package com.github.zereao.netty.demo03.marshalling.dto;

/**
 * @author Zereao
 * @version 2018/06/14/9:19
 */
public class Response {
    private int id;
    private String name;
    private String respMessage;

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

    public String getRespMessage() {
        return respMessage;
    }

    public void setRespMessage(String respMessage) {
        this.respMessage = respMessage;
    }
}
