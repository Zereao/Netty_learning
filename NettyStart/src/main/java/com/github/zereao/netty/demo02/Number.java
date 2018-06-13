package com.github.zereao.netty.demo02;

import java.io.Serializable;

/**
 * @author Zereao
 * @version 2018/06/13/16:22
 */
public class Number implements Serializable {
    private static final long serialVersionUID = 7590999461767050471L;

    private int num;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void add() {
        this.num += 1;
    }
}
