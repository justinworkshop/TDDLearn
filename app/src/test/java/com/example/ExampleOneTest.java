package com.example;

import java.util.LinkedList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Created by zhengwei on 2021/1/8.
 */
public class ExampleOneTest {

    @Test
    public void testMethod() {
        //1. 验证某些行为
        List mockedList = Mockito.mock(List.class);
        mockedList.add("one");
        mockedList.clear();

        Mockito.verify(mockedList).add("one");
        Mockito.verify(mockedList).clear();
    }

    @Test
    public void test2() {
        //2. 如何做一些测试桩
        LinkedList linkedList = Mockito.mock(LinkedList.class);
        Mockito.when(linkedList.get(0)).thenReturn("first");
        Mockito.when(linkedList.get(1)).thenThrow(new RuntimeException());
        System.out.println(linkedList.get(0));
//        System.out.println(linkedList.get(1));
        //没有打桩，输出null
        System.out.println(linkedList.get(2));
        Mockito.verify(linkedList).get(0);
    }

    @Test
    public void test3() {
        //3.参数匹配器
        List list = Mockito.mock(List.class);
        Mockito.when(list.get(Mockito.anyInt())).thenReturn("element");
//        Mockito.when(list.contains(Mockito.argThat(Mockito.isNotNull()))).thenReturn("element");
        System.out.println(list.get(2));
        Mockito.verify(list).get(Mockito.anyInt());
    }

    @Test
    public void test4() {
        //4. 验证函数的调用次数
        List list = Mockito.mock(List.class);
        list.add("one");
        list.add("two");
        list.add("two");
        list.add("three");
        list.add("three");
        list.add("three");

        Mockito.verify(list).add("one");
        Mockito.verify(list, Mockito.times(2)).add("two");
    }

    @Test
    public void test5() {
        //5. 为返回值为void的函数通过Stub抛出异常
        List list = Mockito.mock(List.class);
        Mockito.doThrow(new RuntimeException()).when(list).clear();

        list.clear();
    }

    @Test
    public void test6() {
        //6. 验证函数执行顺序
    }

    @Test
    public void testAdd() {
        ExampleOne exampleOne = Mockito.mock(ExampleOne.class);
        int addResult = exampleOne.add(1, 3);
        Assert.assertFalse(addResult == 4);
        Mockito.verify(exampleOne).add(1, 3);
        Mockito.when(exampleOne.add(1, 3)).thenReturn(5);
    }
}
