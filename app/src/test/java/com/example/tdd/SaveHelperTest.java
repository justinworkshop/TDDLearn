package com.example.tdd;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build.VERSION_CODES;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
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
    public void test(){
        int i = 0;
        int cmd = 10;
        long seq = 1653317479029761L;
        String res = buildWholeMessage("Receive nonconst msg push[%d], seq=%d, command 0x%x", i, seq, cmd);
        System.out.println("res: " + res);
        Log.i("TAG", buildWholeMessage("Receive nonconst msg push[%d], seq=%d, command 0x%x", i, seq, cmd));
    }

    private  String buildWholeMessage(String format, Object... args) {
        if(args != null && args.length != 0) {
            String msg = String.format(format, args);
            return msg;
        } else {
            return format;
        }
    }

    @Test
    public void testPutAndGet() {
        sharedPreferences.edit().putString("key", "Hello").apply();
        String value = sharedPreferences.getString("key", "");
        Assert.assertEquals(value, "Hello");
    }

    @Test
    public void testMap() {
        Map<String, String> map = new HashMap<>();
        map.put("key01", "1");
        map.put("key02", "2");

        Map<String, String> subMap = new HashMap<>();
        map.put("key01", "3");
        map.put("key02", "4");

        map.putAll(subMap);
        Set<Entry<String, String>> entries = map.entrySet();
        for (Entry<String, String> entry : entries) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
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