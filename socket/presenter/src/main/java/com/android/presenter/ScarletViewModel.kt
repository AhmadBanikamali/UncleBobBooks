package com.android.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.domain.use_case.GetEmployee
import com.android.domain.use_case.ReceiveData
import com.android.domain.use_case.SendData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScarletViewModel @Inject constructor(
    private val sendData: SendData,
    private val receiveData: ReceiveData,
    private val getEmployee: GetEmployee,
) : ViewModel() {



    init {
        viewModelScope.launch {
            receiveData().receiveAsFlow().collect{
                println(it)
            }
        }
    }

    fun testSocket() {
        viewModelScope.launch(IO) {
            sendData("A")
        }
    }


    fun testRest() {
        viewModelScope.launch(IO) {
            getEmployee(1).onSuccess {
                println()
            }.onFailure {
                println()
            }
        }
    }
}
