package com.example.cleanarchive.presentation.ui.fragments.contact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchive.data.models.ContactEntity
import com.example.cleanarchive.domein.usecase.AddContactUseCase
import com.example.cleanarchive.domein.usecase.GetContactUseCase
import com.example.cleanarchive.domein.utils.Resource
import com.example.cleanarchive.presentation.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(
    private val getContactUseCase: GetContactUseCase,
    private val addContactUseCase: AddContactUseCase,
) : ViewModel() {

    private val _getAllContactStates = MutableStateFlow<UiState<List<ContactEntity>>>(UiState.EmptyState())
    val getAllContactStates = _getAllContactStates.asStateFlow()

    fun addContact(contactEntity: ContactEntity){
        viewModelScope.launch(Dispatchers.IO) {
            addContactUseCase.execute(contactEntity = contactEntity)
        }
    }



    fun getAllContact() {
        viewModelScope.launch {
            getContactUseCase.execute().collect {
                when (it) {
                    is Resource.Loading -> {
                        _getAllContactStates.value = UiState.Loading()
                    }

                    is Resource.Success -> {
                        _getAllContactStates.value = UiState.Success(it.data as List<ContactEntity>)
                    }

                    is Resource.Error -> {
                        _getAllContactStates.value = UiState.Error(it.message ?: "")
                    }
                }
            }
        }
    }
}


