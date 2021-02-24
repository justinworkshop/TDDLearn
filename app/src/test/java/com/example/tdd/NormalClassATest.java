package com.example.tdd;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Created by zhengwei on 2021/2/24.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({NormalClassA.class})
public class NormalClassATest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Mockito.mock使用，调用空方法
     */
    @Test
    public void testPlus() {
        NormalClassA mock = Mockito.mock(NormalClassA.class);
        int value = mock.testPlus(3, 3);
        Assert.assertThat(value, CoreMatchers.is(0));
        Assert.assertEquals(0, value);
        Mockito.verify(mock, Mockito.times(1)).testPlus(3, 3);

        Mockito.verify(mock, Mockito.times(1)).testPlus(Mockito.anyInt(), Mockito.anyInt());
        Mockito.verify(mock, Mockito.times(1)).testPlus(Mockito.eq(3), Mockito.anyInt());
    }

    /**
     * Mockito.spy使用，调用真实方法
     */
    @Test
    public void testPlus_spy() {
        NormalClassA mock = Mockito.spy(NormalClassA.class);
        int value = mock.testPlus(3, 3);
        Assert.assertThat(value, CoreMatchers.is(6));
        Mockito.verify(mock, Mockito.times(1)).testPlus(3, 3);

        Mockito.verify(mock, Mockito.times(1)).testPlus(Mockito.anyInt(), Mockito.anyInt());
        Mockito.verify(mock, Mockito.times(1)).testPlus(Mockito.eq(3), Mockito.anyInt());

        NormalClassA normalClassA = new NormalClassA();
        NormalClassA spy = Mockito.spy(normalClassA);
        int res = spy.testPlus(2, 4);
        Assert.assertEquals(6, res);
    }

    /**
     * Mockito.mock的条件下，进行函数打桩callRealMethod()
     */
    @Test
    public void testPlus_callRealMethod() {
        NormalClassA mock = Mockito.mock(NormalClassA.class);
        int value = mock.testPlus(3, 3);
        Assert.assertThat(value, CoreMatchers.is(0));
        Mockito.verify(mock, Mockito.times(1)).testPlus(3, 3);

        Mockito.doCallRealMethod().when(mock).testPlus(Mockito.anyInt(), Mockito.anyInt());
        value = mock.testPlus(3, 3);
        Assert.assertEquals(value, 6);
    }

    /**
     * Mockito.mock的条件下，进行函数打桩doReturn()
     */
    @Test
    public void testPlus_doReturn() {
        NormalClassA mock = Mockito.mock(NormalClassA.class);
        int value = mock.testPlus(3, 3);
        Assert.assertThat(value, CoreMatchers.is(0));
        Mockito.verify(mock, Mockito.times(1)).testPlus(3, 3);

        Mockito.doReturn(-1).when(mock).testPlus(Mockito.anyInt(), Mockito.anyInt());
        value = mock.testPlus(3, 3);
        Assert.assertEquals(value, -1);
    }

    /**
     * Mockito.spy的条件下，进行函数打桩doReturn()
     */
    @Test
    public void testPlus_Spy_doReturn() {
        NormalClassA mock = Mockito.spy(NormalClassA.class);
        int value = mock.testPlus(3, 3);
        Assert.assertThat(value, CoreMatchers.is(6));
        Mockito.verify(mock, Mockito.times(1)).testPlus(3, 3);

        Mockito.doReturn(-100).when(mock).testPlus(Mockito.anyInt(), Mockito.anyInt());
        value = mock.testPlus(3, 3);
        Assert.assertEquals(value, -100);
    }

    /**
     * Mockito.spy调用真实函数，mock调用空函数
     */
    @Test
    public void voidMethod() {
        NormalClassA normalClassA = new NormalClassA();
        NormalClassA spy = Mockito.spy(normalClassA);
        spy.voidMethod(1, 1);

        NormalClassA mock = Mockito.mock(NormalClassA.class);
        mock.voidMethod(2, 2);
    }

    /**
     * 注意PowerMockito 两个注解！！！
     * 验证private函数调用， 注意使用 PowerMockito.spy（）
     *
     * @throws Exception
     */
    @Test
    public void testPrivateVerify() throws Exception {
        NormalClassA normalClassA = new NormalClassA();
        NormalClassA spy = PowerMockito.spy(normalClassA);
        spy.publicAdd(1, 2);
        PowerMockito.verifyPrivate(spy, Mockito.times(1)).invoke("privateAdd", 1, 2);
    }

    /**
     * 注意PowerMockito 两个注解！！！
     * 验证private函数调用，注意使用 PowerMockito.spy()
     *
     * @throws Exception
     */
    @Test
    public void testPrivate_verify() throws Exception {
        NormalClassA normalClassA = new NormalClassA();
        NormalClassA spy = PowerMockito.spy(normalClassA);
        spy.publicAdd(3, 4);
        PowerMockito.verifyPrivate(spy, Mockito.times(1)).invoke("privateAdd", Mockito.eq(3), Mockito.anyInt());
    }
}