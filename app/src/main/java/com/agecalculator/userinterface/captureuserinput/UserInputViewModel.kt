package com.agecalculator.userinterface.captureuserinput

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.agecalculator.model.PersonInfo
import com.agecalculator.utility.calculateAgeInYearsMonthsDays

class UserInputViewModel: ViewModel() {
    private val _personInfoMutableLiveData: MutableLiveData<PersonInfo> =
        MutableLiveData()
    val personInfoLiveData: LiveData<PersonInfo>
        get() =_personInfoMutableLiveData

    fun calculateAge(firstName: String, lastName: String, dateOfBirth: String) {
        _personInfoMutableLiveData.value = PersonInfo(
            firstName = firstName, lastName = lastName,
            dateOfBirth = dateOfBirth, age = calculateAgeInYearsMonthsDays(dateOfBirth)
        )
    }
}