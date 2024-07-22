package com.example.investapp.ui.profile

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.investapp.databinding.FragmentProfileBinding
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.investapp.Constants
import com.example.investapp.TokenManager

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ProfileViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

//        val tokenManager = TokenManager(context)
        val repository = ProfileRepository(context)
        val viewModelFactory = ProfileViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ProfileViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = requireActivity().getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
        val id = sharedPref.getInt("userId", 1).toString()

        val token  = Constants.TOKEN

        Log.e("hehehe","${token}")

        viewModel.fetchUserProfile(id, token)

        viewModel.userProfile.observe(viewLifecycleOwner) { profile ->
            profile?.let { updateUI(it) }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.error.observe(viewLifecycleOwner) { error ->
            error?.let {
                Toast.makeText(context, "Error: $it", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun updateUI(profile: UserProfile) {
        with(binding) {
            tvName.text = "${profile.firstName} ${profile.middleName ?: ""} ${profile.surname}".trim()
            tvEmail.text = profile.email
            tvAccountNumber.text = profile.accountNumber
            tvNationality.text = profile.nationality
            tvPostalAddress.text = profile.postalAddress
            tvDateOfBirth.text = profile.dateOfBirth
            tvIdPassportNo.text = profile.idpassportNo
            tvAccountName.text = profile.accountName
            tvAccountType.text = profile.accountType
            tvBankName.text = profile.bankName
            tvBranch.text = profile.branch
            tvBankCode.text = profile.bankCode

            // Next of Kin information
            tvNextOfKinName.text = profile.nextOfKin.name
            tvNextOfKinRelationship.text = profile.nextOfKin.relationship
            tvNextOfKinIdPassportNo.text = profile.nextOfKin.idPassportNo
            tvNextOfKinTelephoneNumber.text = profile.nextOfKin.telephoneNumber

            // ID Picture paths
            tvIdPictureFrontPath.text = profile.idPicture.frontSidePath
            tvIdPictureBackPath.text = profile.idPicture.backSidePath
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
