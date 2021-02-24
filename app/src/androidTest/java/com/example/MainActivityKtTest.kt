package com.example

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.tdd.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by zhengwei on 2021/2/24.
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityKtTest {
    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testStartActivity() {
        val view = ViewMatchers.withText("Hello World!")
        Espresso.onView(view).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}