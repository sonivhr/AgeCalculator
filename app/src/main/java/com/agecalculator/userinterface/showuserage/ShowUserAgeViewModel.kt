package com.agecalculator.userinterface.showuserage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.agecalculator.model.PersonInfo

class ShowUserAgeViewModel: ViewModel() {
    private val _personInfoMutableLiveData: MutableLiveData<PersonInfo> =
        MutableLiveData()
    val personInfoLiveData: LiveData<PersonInfo>
        get() =_personInfoMutableLiveData

    fun setPersonInfo(personInfo: PersonInfo?) {
        _personInfoMutableLiveData.value = personInfo
    }
}