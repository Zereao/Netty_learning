package com.github.zereao.test;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Zereao
 * @version 2018/06/14/16:06
 */
public class TestUtil {


    @Test
    public void test1() {
        Map<String, String> map = new HashMap<>();
        map.put("1", "1");
        map.put("2", "2");
        System.out.println(map);
        System.out.println(map);
        map.remove("2");
        System.out.println(map.remove("2"));
        System.out.println(map);
    }


    public static void main(String[] args) {
        Dto dto = new Dto();

        System.out.println(dto.test1());
        System.out.println(dto.test2());
        System.out.println(dto.test3());
        System.out.println(dto.test4());
        System.out.println("=========================================");
        System.out.println(dto.test1());
        System.out.println(dto.test2());
        System.out.println(dto.test3());
        System.out.println(dto.test4());
    }


}
