package com.example.demo;


import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
class DemoApplicationTests {

    public static void main(String[] args) {
        test();
    }
    @Test
    public static void test(){
        int[] a = {1,2,3,4,5,6,7,8,9};
        int[] b = new int[a.length];
        for (int i = 0; i < a.length; i++){
            int temp = (int)(Math.random()*(a.length - i));
             System.out.println(temp);
            b[i] = a[temp];

            int change = a[a.length - i - 1];
            a[a.length -i -1] = a[temp];
            a[temp] = change;
        }
        System.out.println("bbbb"+ Arrays.toString(b));
    }
}
