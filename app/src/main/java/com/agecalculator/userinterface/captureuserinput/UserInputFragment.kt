package com.agecalculator.userinterface.captureuserinput

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.agecalculator.R
import com.agecalculator.userinterface.showuserage.ShowUserAgeFragment
import com.agecalculator.userinterface.showuserage.TAG_SHOW_USER_AGE_FRAGMENT
import com.agecalculator.utility.DATE_FORMAT
import kotlinx.android.synthetic.main.layout_capture_user_info.*

const val TAG_USER_INPUT_FRAGMENT = "tagUserInputFragment"
class UserInputFragment : Fragment(R.layout.layout_capture_user_info) {

    private lateinit var editTextDatePicker: EditTextDatePicker
    private lateinit var userInputViewModel: UserInputViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userInputViewModel = ViewModelProvider(this)
            .get(UserInputViewModel::class.java)
        editTextDatePicker = EditTextDatePicker(edtDateOfBirth, DATE_FORMAT)
        setButtonClickListener()
        observeLiveData()
    }

    private fun setButtonClickListener() {
        btnCalculateAge.setOnClickListener {
            if (edtFirstName?.text.isNullOrBlank()) {
                edtFirstName.error = getString(R.string.error_field_required)
                return@setOnClickListener
            }

            if (edtLastName?.text.isNullOrBlank()) {
                edtLastName.error = getString(R.string.error_field_required)
                return@setOnClickListener
            }

            if (edtDateOfBirth?.text.isNullOrBlank()) {
                edtDateOfBirth.error = getString(R.string.error_date_of_birth_required)
                return@setOnClickListener
            }

            userInputViewModel.calculateAge(
                edtFirstName.text.toString(),
                edtLastName.text.toString(),
                edtDateOfBirth.text.toString()
            )
        }
    }

    private fun observeLiveData() {
        userInputViewModel.personInfoLiveData.observe(
            viewLifecycleOwner,
            { personInfo ->
                requireActivity().supportFragmentManager.beginTransaction()
                    .add(R.id.container, ShowUserAgeFragment.getInstance(personInfo))
                    .addToBackStack(TAG_SHOW_USER_AGE_FRAGMENT)
                    .commit()
            }
        )
    }
}