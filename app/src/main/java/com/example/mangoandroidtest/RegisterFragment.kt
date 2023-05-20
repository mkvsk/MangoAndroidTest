package com.example.mangoandroidtest

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mangoandroidtest.callback.ResultCallback
import com.example.mangoandroidtest.service.response.RegisterResponse
import com.example.mangoandroidtest.ui.viewmodel.RegisterViewModel
import com.example.mangoandroidtest.util.Constants.USERNAME_REGEX
import com.example.mangoandroidtest.util.obtainViewModel
import online.example.mangoandroidtest.databinding.FragmentRegisterBinding
import java.util.regex.Pattern


class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private val registerViewModel by lazy { obtainViewModel(RegisterViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        initListeners()
    }

    private fun initListeners() {
        binding.etName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0!!.isNotEmpty()) {
                    checkData()
                }
            }

        })

        binding.etUsername.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
//                if (p0!!.length > 4 && usernameValidate(binding.etUsername)) {
//                    checkData()
//                } else {
//                    binding.btnRegister.isEnabled = false
//                }
                if (p0!!.isNotEmpty()) {
                    checkData()
                }

            }

        })

        binding.btnRegister.setOnClickListener {

            if (usernameValidate(binding.etUsername)) {
                Toast.makeText(requireContext(), "Ok", Toast.LENGTH_LONG)
                        .show()
            } else {
                Toast.makeText(requireContext(), "Bad idea", Toast.LENGTH_LONG)
                    .show()
            }


//            registerViewModel.setPhone(binding.etPhone.text.toString())
//            registerViewModel.setName(binding.etName.text.toString())
//            registerViewModel.setUsername(binding.etUsername.text.toString())
//
//            registerViewModel.register(object : ResultCallback<RegisterResponse> {
//                override fun onResult(value: RegisterResponse?) {
////                    value?.let {
////                        if (it.)
////                    }
//                }
//
//                override fun onFailure(value: RegisterResponse?) {
//                    Toast.makeText(requireContext(), "Network error", Toast.LENGTH_LONG)
//                        .show()
//                }
//
//            })
        }
    }

    private fun initObservers() {
        registerViewModel.phone.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                binding.etPhone.setText(it.toString())
            }
        }
    }

    private fun checkData() {
        if ((!binding.etName.text.isNullOrEmpty()) && (!binding.etUsername.text.isNullOrEmpty())) {
            binding.btnRegister.isEnabled = true
        } else {
            binding.btnRegister.isEnabled = false
        }
    }

    private fun usernameValidate(et: EditText): Boolean {
        return Pattern.compile(USERNAME_REGEX)
            .matcher(et.text.toString()).matches()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}