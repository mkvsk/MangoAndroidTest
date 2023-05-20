package com.example.mangoandroidtest

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mangoandroidtest.callback.ResultCallback
import com.example.mangoandroidtest.service.response.CheckAuthCodeResponse
import com.example.mangoandroidtest.ui.viewmodel.AuthenticationViewModel
import com.example.mangoandroidtest.ui.viewmodel.RegisterViewModel
import com.example.mangoandroidtest.util.obtainViewModel
import online.example.mangoandroidtest.R
import online.example.mangoandroidtest.databinding.FragmentVerifyCodeBinding


class VerifyCodeFragment : Fragment() {
    private var _binding: FragmentVerifyCodeBinding? = null
    private val binding get() = _binding!!

    private val authViewModel by lazy { obtainViewModel(AuthenticationViewModel::class.java) }
    private val registerViewModel by lazy { obtainViewModel(RegisterViewModel::class.java) }

    private var codeLength: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVerifyCodeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initListeners()
    }

    private fun initListeners() {
        binding.etVCode.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                if (binding.etVCode.text.length == codeLength) {
                    binding.btnCheckVCode.isEnabled = true
                } else {
                    binding.btnCheckVCode.isEnabled = false
                }
            }

        })

        binding.btnCheckVCode.setOnClickListener {
            authViewModel.checkVCode(
                binding.etVCode.text.toString(),
                object : ResultCallback<CheckAuthCodeResponse> {
                    override fun onResult(value: CheckAuthCodeResponse?) {
                        value?.let {
                            if (it.is_user_exists) {
//                                findNavController().navigate(R.id.action_go_to_profile)
                            } else {
                                registerViewModel.setPhone(authViewModel.phone.value.toString())
                                findNavController().navigate(R.id.action_go_to_register)
                            }
                        }
                    }

                    override fun onFailure(value: CheckAuthCodeResponse?) {
                        Toast.makeText(requireContext(), "Network error", Toast.LENGTH_LONG)
                            .show()
                    }

                })
        }
    }

    private fun initViews() {
        codeLength =
            binding.etVCode.filters.filterIsInstance<InputFilter.LengthFilter>()
                .firstOrNull()?.max!!
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}