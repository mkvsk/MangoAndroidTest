package com.example.mangoandroidtest

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mangoandroidtest.callback.ResultCallback
import com.example.mangoandroidtest.service.response.SendAuthCodeResponse
import com.example.mangoandroidtest.ui.viewmodel.AuthenticationViewModel
import com.example.mangoandroidtest.util.Constants
import com.example.mangoandroidtest.util.hideKeyboard
import com.example.mangoandroidtest.util.obtainViewModel
import okhttp3.internal.trimSubstring
import online.example.mangoandroidtest.R
import online.example.mangoandroidtest.databinding.FragmentAuthBinding


class AuthFragment : Fragment() {
    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!

    private val authViewModel by lazy { obtainViewModel(AuthenticationViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initListeners()
    }

    private fun initListeners() {
        binding.btnCheckUser.setOnClickListener {
            hideKeyboard(binding.etPhoneNumber)
            authViewModel.setPhone(
                binding.countryCodePicker.selectedCountryCode.toString()
                        + binding.etPhoneNumber.text.toString()
            )

            authViewModel.sendAuthCode(object : ResultCallback<SendAuthCodeResponse> {
                override fun onResult(value: SendAuthCodeResponse?) {
                    value?.let {
                        if (it.is_success) {
                            findNavController().navigate(R.id.action_go_to_verify_code)
                        } else {
                            Toast.makeText(
                                requireContext(), "Validation error", Toast.LENGTH_LONG
                            )
                                .show()
                        }
                    }

                }

                override fun onFailure(value: SendAuthCodeResponse?) {
                    Toast.makeText(requireContext(), "Network error", Toast.LENGTH_LONG)
                        .show()
                }
            })

        }

        binding.etPhoneNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                if (!p0.isNullOrEmpty() &&
                    p0.length.plus(binding.countryCodePicker.selectedCountryCode.length) <= Constants.MAX_PHONE_LENGTH
                ) {
                    binding.btnCheckUser.isEnabled = true
                } else if (p0.isNullOrEmpty() ||
                    p0.length.plus(binding.countryCodePicker.selectedCountryCode.length) > Constants.MAX_PHONE_LENGTH
                ) {
                    binding.btnCheckUser.isEnabled = false
                } else if (p0.length == 30) {
                    hideKeyboard(binding.etPhoneNumber)
                }
            }

        })

    }

    private fun initObservers() {
        authViewModel.phone.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                val code = binding.countryCodePicker.selectedCountryCode.toString().length
                binding.etPhoneNumber.setText(it.toString().trimSubstring(code, it.length))
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}