package com.example.mangoandroidtest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mangoandroidtest.ui.viewmodel.UserViewModel
import com.example.mangoandroidtest.util.FormatUtils
import com.example.mangoandroidtest.util.obtainViewModel
import online.example.mangoandroidtest.R
import online.example.mangoandroidtest.databinding.FragmentUserProfileBinding
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

        var birthday = "2023-05-20"
        var tmp = "2023-05-20T20:38:08.241Z"

        val date = LocalDate.parse(birthday, FormatUtils.dateFormat)
        val dateTime = ZonedDateTime.parse(tmp).toLocalDateTime()

        initObservers()
//        initViews()
//        initListeners()
    }

    private fun initObservers() {

    }

}