package com.agecalculator.userinterface.showuserage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.agecalculator.R
import com.agecalculator.model.PersonInfo
import com.agecalculator.userinterface.captureuserinput.UserInputViewModel
import kotlinx.android.synthetic.main.layout_user_profile.*

const val TAG_SHOW_USER_AGE_FRAGMENT = "tagShowUserAgeFragment"

class ShowUserAgeFragment : Fragment(R.layout.layout_user_profile) {

    private lateinit var showUserAgeViewModel: ShowUserAgeViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showUserAgeViewModel = ViewModelProvider(this)
            .get(ShowUserAgeViewModel::class.java)
        readAndSetExtraValue()
        observeUserAge()
    }

    private fun readAndSetExtraValue() {
        val bundle = this.arguments
        if (bundle != null) {
            showUserAgeViewModel.setPersonInfo(
                bundle.getParcelable(KEY_EXTRA_PERSON_INFO)
            )
        }
    }

    private fun observeUserAge() {
        showUserAgeViewModel.personInfoLiveData.observe(
            viewLifecycleOwner,
            { personInfo ->
                tvValueFirstName?.text = personInfo.firstName
                tvValueLastName?.text = personInfo.lastName
                tvValueAge?.text = personInfo.age
            }
        )
    }

    companion object {
        private const val KEY_EXTRA_PERSON_INFO = "keyExtraPersonInfo"

        fun getInstance(personInfo: PersonInfo): ShowUserAgeFragment {
            val showUserAgeFragment = ShowUserAgeFragment()
            val bundle = Bundle()
            bundle.putParcelable(KEY_EXTRA_PERSON_INFO, personInfo)
            showUserAgeFragment.arguments = bundle
            return showUserAgeFragment
        }
    }
}