package com.example.mock

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

/**
 * Created by zhengwei on 2021/5/18.
 */
class UserModelTest {
    private lateinit var mockUserModel: UserModel

    @Before
    fun setUp() {
        mockUserModel = Mockito.mock(UserModel::class.java)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun testGetUserList_male() {
        var result = mockUserModel.getUserList(1)
        println(result)
    }

    @Test
    fun testGetUserList_female() {
        var result = mockUserModel.getUserList(2)
        println(result)
    }
}