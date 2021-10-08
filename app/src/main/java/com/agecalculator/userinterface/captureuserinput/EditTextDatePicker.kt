package com.agecalculator.userinterface.captureuserinput

import android.view.View
import android.widget.EditText
import java.text.SimpleDateFormat
import java.util.*
import android.app.DatePickerDialog
import android.widget.DatePicker


class EditTextDatePicker(
    private val editText: EditText,
    private val dateFormat: String
) : View.OnFocusChangeListener, View.OnClickListener, DatePickerDialog.OnDateSetListener{

    private val simpleDateFormat: SimpleDateFormat
    private lateinit var calendar: Calendar

    init {
        editText.setOnClickListener(this)
        editText.onFocusChangeListener = this
        simpleDateFormat = SimpleDateFormat(dateFormat, Locale.getDefault())
    }

    override fun onFocusChange(view: View, hasFocus: Boolean) {
        if (hasFocus) {
            showDatePicker(view)
        }
    }

    override fun onClick(view: View) {
        showDatePicker(view)
    }

    private fun showDatePicker(view: View) {
        if (!::calendar.isInitialized) {
            calendar = Calendar.getInstance()
        }
        val day: Int = calendar.get(Calendar.DAY_OF_MONTH)
        val month: Int = calendar.get(Calendar.MONTH)
        val year: Int = calendar.get(Calendar.YEAR)
        val datePickerDialog = DatePickerDialog(view.context, this, year, month, day)
        datePickerDialog.datePicker.maxDate = Calendar.getInstance().timeInMillis
        datePickerDialog.show()
    }

    override fun onDateSet(datePicker: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        if (::calendar.isInitialized) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            this.editText.error = null
            this.editText.setText(simpleDateFormat.format(calendar.time));
        }
    }
}