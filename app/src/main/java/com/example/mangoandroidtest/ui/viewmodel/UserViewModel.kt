package com.example.mangoandroidtest.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mangoandroidtest.callback.ResultCallback
import com.example.mangoandroidtest.callback.TokenRefreshCallback
import com.example.mangoandroidtest.core.Avatars
import com.example.mangoandroidtest.core.Token
import com.example.mangoandroidtest.core.User
import com.example.mangoandroidtest.network.RetrofitFactory
import com.example.mangoandroidtest.network.request.UserUpdateAvatarRequest
import com.example.mangoandroidtest.network.request.UserUpdateRequest
import com.example.mangoandroidtest.network.response.GetCurrentUserResponse
import com.example.mangoandroidtest.network.response.RefreshTokenResponse
import com.example.mangoandroidtest.network.response.UserUpdateResponse
import com.example.mangoandroidtest.ui.repository.UserRepository

class UserViewModel : ViewModel() {

    private val repository by lazy { UserRepository() }

    private val _currentUser = MutableLiveData(User())
    val currentUser: LiveData<User?> get() = _currentUser

    fun setCurrentUser(value: User?) {
        _currentUser.value = value
    }

    private val _name = MutableLiveData("")
    val name: LiveData<String> get() = _name

    fun setName(value: String) {
        _name.value = value
    }

    private val _token = MutableLiveData<Token>()
    val token: LiveData<Token> get() = _token

    fun setToken(value: Token) {
        _token.value = value
    }

    private val _username = MutableLiveData("")
    val username: LiveData<String> get() = _username

    fun setUsername(value: String) {
        _username.value = value
    }

    private val _phone = MutableLiveData("")
    val phone: LiveData<String> get() = _phone

    fun setPhone(value: String) {
        _phone.value = value
    }

    private val _avatars = MutableLiveData<Avatars>()
    val avatars: LiveData<Avatars> get() = _avatars

    fun setAvatars(value: Avatars) {
        _avatars.value = value
    }

    private val _userUpdateAvatar = MutableLiveData<UserUpdateAvatarRequest>()
    val userUpdateAvatar: LiveData<UserUpdateAvatarRequest> get() = _userUpdateAvatar

    fun setUserUpdateAvatar(value: UserUpdateAvatarRequest) {
        _userUpdateAvatar.value = value
    }

    private val _error = MutableLiveData(false)
    val error: LiveData<Boolean> get() = _error

    fun setError(value: Boolean) {
        _error.value = value
    }

    private val _result = MutableLiveData<Int>(0)
    val result: LiveData<Int> get() = _result

    fun setResult(value: Int) {
        _result.value = value
    }

    private val _closeView = MutableLiveData(false)
    val closeView: LiveData<Boolean> get() = _closeView

    fun setCloseView(value: Boolean) {
        _closeView.value = value
    }

    fun getCurrentUser() {
        repository.getCurrentUser(object : ResultCallback<GetCurrentUserResponse> {
            override fun onResult(value: GetCurrentUserResponse?) {
                value?.let {
                    setCurrentUser(it.profile_data)
                    setResult(1)
                }
            }

            override fun onFailure(value: GetCurrentUserResponse?) {
                setCurrentUser(null)
                setResult(-1)
            }

            override fun onTokenExpired() {
                refreshToken(object : TokenRefreshCallback<RefreshTokenResponse> {
                    override fun onResult(value: RefreshTokenResponse?) {
                        value?.let {
                            RetrofitFactory.updateAccessJWT(it.access_token)
                            RetrofitFactory.updateRefreshJWT(it.refresh_token)
                            getCurrentUser()
                        }
                    }

                    override fun onFailure(value: RefreshTokenResponse?) {}

                })
            }

        })

    }

    fun updateCurrentUser() {
        val request = UserUpdateRequest(
            currentUser.value!!.name,
            currentUser.value!!.username,
            currentUser.value!!.birthday.toString(),
            currentUser.value!!.city,
            currentUser.value!!.vk,
            currentUser.value!!.instagram,
            currentUser.value!!.status,
            UserUpdateAvatarRequest(
                userUpdateAvatar.value!!.filename,
                userUpdateAvatar.value!!.base_64
            )
        )
        repository.updateCurrentUser(
            request,
            object : ResultCallback<UserUpdateResponse> {
                override fun onResult(value: UserUpdateResponse?) {
                    value?.let {
                        setAvatars(it.avatars)
                        setCloseView(true)
                    }
                }

                override fun onFailure(value: UserUpdateResponse?) {
                    setError(true)
                }

                override fun onTokenExpired() {
                    refreshToken(object : TokenRefreshCallback<RefreshTokenResponse> {
                        override fun onResult(value: RefreshTokenResponse?) {
                            value?.let {
                                RetrofitFactory.updateAccessJWT(it.access_token)
                                RetrofitFactory.updateRefreshJWT(it.refresh_token)
                                updateCurrentUser()
                            }
                        }

                        override fun onFailure(value: RefreshTokenResponse?) {
                        }

                    })
                }
            })

    }

    private fun refreshToken(callback: TokenRefreshCallback<RefreshTokenResponse>) {
        repository.refreshToken(object : TokenRefreshCallback<RefreshTokenResponse> {
            override fun onResult(value: RefreshTokenResponse?) {
                callback.onResult(value)
            }

            override fun onFailure(value: RefreshTokenResponse?) {
                callback.onFailure(null)
            }

        })
    }

}