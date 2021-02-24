package com.example.tdd;

public class NormalClassA {

    /**
     * Mockito演示中被测函数
     */
    public int testPlus(int a, int b) {
        return a + b;
    }

    /**
     * Mockito演示中被测函数
     */
    public void voidMethod(int a, int b) {
        System.out.println("a + b = " + (a + b));
    }

    /**
     * PowerMock演示中被测函数
     */
    public int publicAdd(int a, int b) {
        return privateAdd(a, b);
    }

    /**
     * PowerMock演示中被测函数
     */
    private int privateAdd(int a, int b) {
        return a + b;
    }

}
