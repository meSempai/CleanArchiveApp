package com.example.cleanarchive.presentation.ui.fragments.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchive.data.models.ContactEntity
import com.example.cleanarchive.domein.usecase.UpdateContactUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val updateContactUseCase: UpdateContactUseCase,
    private val deleteContactUseCase: UpdateContactUseCase
) : ViewModel() {

    suspend fun updateContact(contactEntity: ContactEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            updateContactUseCase.execute(contactEntity = contactEntity)
        }
    }

    suspend fun deleteContact(contactEntity: ContactEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteContactUseCase.execute(contactEntity = contactEntity)
        }
    }
}