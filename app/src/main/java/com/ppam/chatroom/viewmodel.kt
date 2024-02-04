package com.ppam.chatroom

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.ppam.chatroom.data.UserRepository
import kotlinx.coroutines.launch
import com.ppam.chatroom.data.Result


class AuthViewModel : ViewModel() {
    private val userRepository: UserRepository

    private val _authResult = MutableLiveData<Result<Boolean>>()
    val authResult: LiveData<Result<Boolean>> get() = _authResult

    private var _showSuccessDialog = mutableStateOf(false)
    var showSuccessDialog: State<Boolean>
        get() = _showSuccessDialog
        set(newValue) {
            _showSuccessDialog.value = newValue.value
        }

    object Injection {
        private val instance: FirebaseFirestore by lazy {
            FirebaseFirestore.getInstance()
        }

        fun instance(): FirebaseFirestore {
            return instance
        }
    }

    init {
        userRepository = UserRepository(
            FirebaseAuth.getInstance(),
            Injection.instance()
        )
    }

    // MARK:// Sign-in and Sign-out methods
    fun signUp(email: String, password: String, firstName: String, lastName: String) {
        viewModelScope.launch {
            _authResult.value = userRepository.signUp(email, password, firstName, lastName)
            authResult.value?.let { handleResult(it) }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authResult.value = userRepository.login(email, password)
            authResult.value?.let { handleResult(it) }
        }
    }

    private fun handleResult(result: Result<Boolean>) {
        when (result) {
            is Result.Success -> {
                _showSuccessDialog.value = true
            }

            is Result.Error -> {
                // Handle error if needed
            }
        }
    }
}
