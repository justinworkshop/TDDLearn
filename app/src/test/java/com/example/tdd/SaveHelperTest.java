package com.example.tdd;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build.VERSION_CODES;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.reflect.Whitebox;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

/**
 * Created by zhengwei on 2021/2/24.
 */
@RunWith(RobolectricTestRunner.class)
@Config(sdk = VERSION_CODES.O_MR1, application = MockApplication.class)
public class SaveHelperTest {

    private SharedPreferences sharedPreferences;

    @Before
    public void setUp() throws Exception {
        sharedPreferences = MockApplication.getApplication().getSharedPreferences("pref_name", Context.MODE_PRIVATE);
    }

    @Test
    public void testPutAndGet() {
        sharedPreferences.edit().putString("key", "Hello").apply();
        String value = sharedPreferences.getString("key", "");
        Assert.assertEquals(value, "Hello");
    }

    @Test
    public void testTitle_1() {
        SaveHelper saveHelper = new SaveHelper();
        saveHelper.setData();
        Assert.assertEquals(SaveHelper.DEFAULT_TITLE, saveHelper.getTitle());

        String title = "AllOne";
        Whitebox.setInternalState(saveHelper, "mTitle", title);
        Assert.assertEquals(title, saveHelper.getTitle());
    }

    @Test
    public void testTitle_2() {
        String title = "AllOne";
        SaveHelper spy = PowerMockito.spy(new SaveHelper());
        PowerMockito.doReturn(title).when(spy).getTitle();
        spy.setData();
        System.out.println("title: " + spy.getTitle());
        Assert.assertEquals(title, spy.getTitle());
    }

    @After
    public void tearDown() throws Exception {

    }
}