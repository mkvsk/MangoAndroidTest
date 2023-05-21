package com.example.mangoandroidtest

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mangoandroidtest.core.User
import com.example.mangoandroidtest.ui.viewmodel.UserViewModel
import com.example.mangoandroidtest.util.FormatUtils
import com.example.mangoandroidtest.util.obtainViewModel
import online.example.mangoandroidtest.R
import online.example.mangoandroidtest.databinding.FragmentUserProfileBinding
import retrofit2.Callback
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZonedDateTime


class UserProfileFragment : Fragment() {
    private var _binding: FragmentUserProfileBinding? = null
    private val binding get() = _binding!!

    private val userViewModel by lazy { obtainViewModel(UserViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        var tmp = "2023-05-20T20:38:08.241Z"
//
//        val date = LocalDate.parse(birthday, FormatUtils.dateFormat)
//        val dateTime = ZonedDateTime.parse(tmp).toLocalDateTime()
//
//        val t = date.dayOfMonth
//
//        FormatUtils.identifyZodiacSign(date.dayOfMonth, date.month)
        initObservers()
        initViews()
//        initListeners()
        userViewModel.getCurrentUser()
    }

    private fun initViews() {
    }

    private fun initObservers() {
        userViewModel.currentUser.observe(viewLifecycleOwner) {
            if (it != null) {
                drawData(userViewModel.currentUser.value!!)
            } else {
                Toast.makeText(context, "Something went wrong...", Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun drawData(user: User) {
        binding.tvUsername.text = user.username
        binding.tvPhone.text = user.phone
        Log.d("TAG", "drawData: ${user}")
        binding.tvCity.text = user.city
//        binding.tvBday.text = user.birthday.ifEmpty { "" }
//
//
//        if (user.birthday.isEmpty()) {
//            binding.tvZodiac.text = ""
//        } else {
//            val dateOfBirth = LocalDate.parse(user.birthday, FormatUtils.dateFormat)
//            binding.tvZodiac.text =
//                FormatUtils.identifyZodiacSign(dateOfBirth.dayOfMonth, dateOfBirth.month)
//        }
    }

}