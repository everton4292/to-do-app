package com.resende.todolist.presentation.greetings

import androidx.lifecycle.MutableLiveData
import com.resende.todolist.data.model.AccessTokenResponse
import com.resende.todolist.presentation.BaseViewModel
import kotlinx.coroutines.launch

class GreetingsViewModel(
    private val greetingRepository: GreetingRepository
) : BaseViewModel() {

    val tokenAccess = MutableLiveData<AccessTokenResponse>()

    fun getAccessToken(code: String?, clientId: String, clientSecret: String) {
        try {
            scope.launch {
                if (code != null) {
                    val token = greetingRepository.getAccessToken(code, clientId, clientSecret)
                    tokenAccess.value = token
                }
            }
        } catch (e: Exception) {
            tokenAccess.value = null
            e.printStackTrace()
        }
    }

}