package com.agecalculator.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PersonInfo(
    val firstName: String,
    val lastName: String,
    val dateOfBirth: String,
    val age: String
) : Parcelable
