package com.example.mock;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Created by zhengwei on 2021/5/18.
 */
public class GiftModelTest {

    private GiftModel mockGiftModel;
    private GiftModel spyGiftModel;

    @Before
    public void setUp() throws Exception {
        mockGiftModel = Mockito.mock(GiftModel.class);
        System.out.println("mockGiftModel: " + mockGiftModel);

        spyGiftModel = Mockito.spy(GiftModel.class);
        System.out.println("spyGiftModel: " + spyGiftModel);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetGiftList_mock_normal_gift() {
        List<String> giftList = mockGiftModel.getGiftList(0);
        System.out.println("mock normalGiftList: " + giftList);
    }

    @Test
    public void testGetGiftList_mock_stub_normal_gift() {
        List<String> result = new ArrayList<>();
        result.add("Stub");
        Mockito.when(mockGiftModel.getGiftList(0)).thenReturn(result);
        List<String> giftList = mockGiftModel.getGiftList(0);
        System.out.println("mock normalGiftList: " + giftList);
    }

    @Test
    public void testGetGiftList_callRealMethod_mock_normal_gift() {
        Mockito.doCallRealMethod().when(mockGiftModel).getGiftList(0);
        List<String> giftList = mockGiftModel.getGiftList(0);
        System.out.println("doCallRealMethod normalGiftList: " + giftList);
    }

    @Test
    public void testGetGiftList_spy_luxury_gift() {
        List<String> giftList = spyGiftModel.getGiftList(1);
        System.out.println("spy luxuryGiftList: " + giftList);
    }

    @Test
    public void testGetGiftSum_spy_first_doReturn() {
        Mockito.doReturn(1).when(spyGiftModel).getGiftSum();
        System.out.println("spy getGiftSum: " + spyGiftModel.getGiftSum());
    }

    @Test
    public void testGetGiftSum_spy_first_when() {
        Mockito.when(spyGiftModel.getGiftSum()).thenReturn(2);
        System.out.println("spy getGiftSum: " + spyGiftModel.getGiftSum());
    }
}