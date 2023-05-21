package com.example.mangoandroidtest.ui.view

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mangoandroidtest.core.User
import com.example.mangoandroidtest.network.request.UserUpdateAvatarRequest
import com.example.mangoandroidtest.ui.viewmodel.UserViewModel
import com.example.mangoandroidtest.util.obtainViewModel
import online.example.mangoandroidtest.R
import online.example.mangoandroidtest.databinding.FragmentUserEditProfileBinding
import java.io.ByteArrayOutputStream


class EditUserProfileFragment : Fragment() {
    private var _binding: FragmentUserEditProfileBinding? = null
    private val binding get() = _binding!!

    private val userViewModel by lazy { obtainViewModel(UserViewModel::class.java) }
    var user = User()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserEditProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMenu()
        initObservers()
        initListeners()
    }

    private fun setupMenu() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initListeners() {
        binding.btnSave.setOnClickListener {
            user.phone = binding.etPhone.text.toString()
            user.username = binding.etUsername.text.toString()
            user.name = binding.etName.text.toString()
            user.status = binding.etStatus.text.toString()
            user.birthday = binding.etBirthday.text.toString()
            user.city = binding.etCity.text.toString()
            user.vk = binding.etVk.text.toString()
            user.instagram = binding.etInstagram.text.toString()

            userViewModel.setCurrentUser(user)
            imageToBase64()
            userViewModel.updateCurrentUser()
        }
    }

    private fun imageToBase64() {
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.ava)
        val byteStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteStream)
        val byteArray: ByteArray = byteStream.toByteArray()
        val baseString: String = Base64.encodeToString(byteArray, Base64.DEFAULT)

        userViewModel.setUserUpdateAvatar(
            UserUpdateAvatarRequest(
                resources.getResourceName(R.drawable.ava),
                baseString
            )
        )
    }

    private fun initObservers() {
        userViewModel.currentUser.observe(viewLifecycleOwner) {
            if (it != null) {
                user = userViewModel.currentUser.value!!
                drawData()
            } else {
                Toast.makeText(context, "Something went wrong...", Toast.LENGTH_LONG).show()
            }
        }

        userViewModel.error.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(context, "Something went wrong...", Toast.LENGTH_LONG).show()
                userViewModel.setError(false)
            }
        }

        userViewModel.closeView.observe(viewLifecycleOwner) {
            if (it) {
                userViewModel.setCloseView(false)
                findNavController().popBackStack()
            }
        }
    }

    private fun drawData() {
        binding.etPhone.setText(user.phone)
        binding.etUsername.setText(user.username)

        if (user.name.isNotEmpty()) {
            binding.etName.setText(user.name)
        }

        if (!user.status.isNullOrBlank()) {
            binding.etStatus.setText(user.status)
        }

        if (!user.birthday.isNullOrBlank()) {
            binding.etBirthday.setText(user.birthday)
        }

        if (!user.city.isNullOrBlank()) {
            binding.etCity.setText(user.city)
        }

        if (!user.vk.isNullOrBlank()) {
            binding.etVk.setText(user.vk)
        }

        if (!user.instagram.isNullOrBlank()) {
            binding.etInstagram.setText(user.instagram)
        }
    }

}