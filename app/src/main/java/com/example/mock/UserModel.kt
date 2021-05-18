package com.example.mock

/**
 * Created by zhengwei on 2021/5/18.
 */
open class UserModel {

    /**
     * 获取用户列表
     * sex: 1-Male, 2-Female
     */
    fun getUserList(sex: Int): List<String> {
        return if (sex == 1) {
            getMaleList()
        } else {
            getFemaleList()
        }
    }

    private fun getMaleList(): List<String> {
        return arrayListOf("Jack")
    }

    private fun getFemaleList(): List<String> {
        return arrayListOf("Lily")
    }
}