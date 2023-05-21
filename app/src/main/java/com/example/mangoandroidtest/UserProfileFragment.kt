package com.example.mangoandroidtest

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.example.mangoandroidtest.core.User
import com.example.mangoandroidtest.ui.viewmodel.UserViewModel
import com.example.mangoandroidtest.util.FormatUtils
import com.example.mangoandroidtest.util.obtainViewModel
import online.example.mangoandroidtest.R
import online.example.mangoandroidtest.databinding.FragmentUserProfileBinding
import java.time.LocalDate


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
        binding.loader.progressOverlay.visibility = View.VISIBLE
        setupMenu()
        userViewModel.getCurrentUser()
        initObservers()
    }

    private fun setupMenu() {
        binding.toolbar.inflateMenu(R.menu.user_profile_menu)
        binding.toolbar.setOnMenuItemClickListener(this::onOptionsItemSelected)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_user_edit_profile -> {
                findNavController().navigate(R.id.action_go_to_edit_user_profile)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initObservers() {
        userViewModel.result.observe(viewLifecycleOwner) {
            when (it) {
                1 -> {
                    drawData(userViewModel.currentUser.value!!)
                    userViewModel.setResult(0)
                }
                -1 -> {
                    Toast.makeText(context, "Something went wrong...", Toast.LENGTH_LONG).show()
                    userViewModel.setResult(0)
                }
            }

        }

    }

    private fun drawData(user: User) {
        binding.toolbar.title = user.username
        binding.tvName.text = user.name

        if (user.status.isNotEmpty()) {
            binding.tvStatus.text =
                String.format(requireContext().getString(R.string.format_status), user.status)
        } else {
            binding.tvStatus.visibility = View.GONE
        }

        binding.tvPhone.text = user.phone

        if (user.city.isNotEmpty()) {
            binding.tvCity.text = user.city
        } else {
            binding.tvCity.visibility = View.GONE
            binding.icCity.visibility = View.GONE
        }

        if (user.birthday.isNotEmpty()) {
            binding.tvBday.text = user.birthday
            val dateOfBirth = LocalDate.parse(user.birthday, FormatUtils.dateFormat)
            binding.tvZodiac.text =
                FormatUtils.identifyZodiacSign(dateOfBirth.dayOfMonth, dateOfBirth.month)
        } else {
            binding.tvBday.visibility = View.GONE
            binding.icBday.visibility = View.GONE
            binding.tvZodiac.visibility = View.GONE
            binding.icZodiac.visibility = View.GONE
        }

        if (user.vk.isNotEmpty()) {
            binding.tvVk.text = user.vk
        } else {
            binding.tvVk.visibility = View.GONE
            binding.icVk.visibility = View.GONE
        }

        if (user.instagram.isNotEmpty()) {
            binding.tvInstagram.text = user.vk
        } else {
            binding.tvInstagram.visibility = View.GONE
            binding.icInstagram.visibility = View.GONE
        }

        binding.loader.progressOverlay.visibility = View.GONE
    }

}