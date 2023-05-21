package com.example.mangoandroidtest

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
import com.example.mangoandroidtest.core.Token
import com.example.mangoandroidtest.service.RetrofitFactory
import com.example.mangoandroidtest.service.response.CheckAuthCodeResponse
import com.example.mangoandroidtest.ui.viewmodel.AuthViewModel
import com.example.mangoandroidtest.ui.viewmodel.UserViewModel
import com.example.mangoandroidtest.util.obtainViewModel
import online.example.mangoandroidtest.R
import online.example.mangoandroidtest.databinding.FragmentVerifyCodeBinding


class VerifyCodeFragment : Fragment() {
    private var _binding: FragmentVerifyCodeBinding? = null
    private val binding get() = _binding!!

    private val authViewModel by lazy { obtainViewModel(AuthViewModel::class.java) }
    private val userViewModel by lazy { obtainViewModel(UserViewModel::class.java) }

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
        initObservers()
    }

    private fun initObservers() {
        authViewModel.isUserAuthenticated.observe(viewLifecycleOwner) {
            if (it != null) {
                if (it) {
                    findNavController().navigate(R.id.action_go_to_user_profile)
                } else if (!it) {
                    findNavController().navigate(R.id.action_go_to_register)
                }
            }
        }
    }

    private fun initViews() {
        codeLength =
            binding.etVCode.filters.filterIsInstance<InputFilter.LengthFilter>()
                .firstOrNull()?.max!!
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
                        value?.let { it ->
                            if (it.is_user_exists) {
                                userViewModel.setToken(
                                    Token(
                                        it.access_token,
                                        it.refresh_token,
                                        it.user_id!!
                                    )
                                )
                                RetrofitFactory.updateAccessJWT(it.access_token)
                                RetrofitFactory.updateRefreshJWT(it.refresh_token)
                                authViewModel.setIsUserAuthenticated(true)
                            } else {
                                authViewModel.setIsUserAuthenticated(false)
                            }
                        }
                    }

                    override fun onFailure(value: CheckAuthCodeResponse?) {
                        Toast.makeText(requireContext(), "Network error", Toast.LENGTH_LONG)
                            .show()
                    }

                    override fun onTokenExpired() {}
                })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}