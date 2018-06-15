package com.github.zereao.test;

/**
 * @author 何雨伦
 * @version 2018/06/14/16:07
 */
public class Dto {
    private int id;

    public Dto test1() {
        return this;
    }

    public Dto test2() {
        return this;
    }


    public Dto test3() {
        return self();
    }

    public Dto test4() {
        return self();
    }

    public Dto self() {
        return this;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
