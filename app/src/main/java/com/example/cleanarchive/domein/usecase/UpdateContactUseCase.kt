package com.example.cleanarchive.domein.usecase

import com.example.cleanarchive.data.models.ContactEntity
import com.example.cleanarchive.domein.repositories.ContactRepository

class UpdateContactUseCase(
    private val contactRepository: ContactRepository
) {
    suspend fun updateContact(contactEntity: ContactEntity) {
        contactRepository.updateContact(contactEntity)
    }
}