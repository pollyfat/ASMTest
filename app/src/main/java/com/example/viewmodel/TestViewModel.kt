package com.example.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 *
 * @author: hmei
 * @date: 2021/11/11
 * @email: huangmei@haohaozhu.com
 *
 */
class TestViewModel : ViewModel() {
    var testBean = MutableLiveData<TestBean>()
    var changeCount = 0

    fun changeTestBean() {
        testBean.postValue(TestBean("has been change ${++changeCount} time(s)"))
    }
}

data class TestBean(var title: String = "")