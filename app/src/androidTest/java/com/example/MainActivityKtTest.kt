package com.example

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.tdd.MainActivity
import com.example.tdd.R
import org.hamcrest.Matchers
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

        //查找, 使用视图匹配器ViewMatchers
        val viewInteraction0 = Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.main_text), ViewMatchers.withText("Hello World!")))
        val viewInteraction1 = Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.main_text), Matchers.not(ViewMatchers.withText("Unwanted"))))

        //对视图进行操作 ViewInteraction ViewActions(定义了操作集合)
        val perform = viewInteraction0.perform(ViewActions.click())
        val perform1 = viewInteraction1.perform(ViewActions.longClick())

        //检查视图断言 ViewAssertions
        //ViewInteraction#check() 检查断言内容
        perform.check(ViewAssertions.matches(ViewMatchers.withText("Hello World!")))
        perform1.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}