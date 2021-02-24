package com.example;

import java.util.LinkedList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

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
        List singleMock = Mockito.mock(List.class);

        singleMock.add("add first");
        singleMock.add("add second");

        InOrder inOrder = Mockito.inOrder(singleMock);

        inOrder.verify(singleMock).add("add first");
        inOrder.verify(singleMock).add("add second");
    }

    @Test
    public void test7(){
        //7.确保交互操作不回执行在mock对象上
        List mockOne = Mockito.mock(List.class);
        mockOne.add("one");

        Mockito.verify(mockOne).add("one");
        Mockito.verify(mockOne, Mockito.never()).add("two");

        Mockito.verifyZeroInteractions(mockOne);
    }

    @Mock private ArticleCalculator calculator;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test8(){
        //8.建议mock对象的创建，使用MockitoAnnotation.initMocks(Class)
        Mockito.verify(calculator).hashCode();
    }

    @Test
    public void test9(){
        //10.为连续的调用做测试桩
        Mockito.when(calculator.calculate("error")).thenReturn("error").thenReturn("Ok");

        System.out.println(calculator.calculate("error"));
        System.out.println(calculator.calculate("error"));
    }

    @Test
    public void test11(){
        //11.为回调做测试桩
        Mockito.when(calculator.calculate(Mockito.anyString())).thenAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                Object mock = invocation.getMock();
                return "called with arguments: " + args.toString();
            }
        });

        System.out.println(calculator.calculate("foo"));
    }

    @Test
    public void test13(){
        //13.监控真实对象
        List list = new LinkedList();
        List spy = Mockito.spy(list);

        //为size函数打桩
        Mockito.when(spy.size()).thenReturn(100);

        //通过spy对象调用真实对象的函数
        spy.add("one");
        spy.add("two");

        System.out.println(spy.size());
        System.out.println(spy.get(0)); //这里size函数打桩返回100，但是如果get(2)就会数组越界，因为真实对象size为2。
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
