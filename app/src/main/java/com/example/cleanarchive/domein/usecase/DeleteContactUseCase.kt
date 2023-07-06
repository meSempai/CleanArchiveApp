package com.example.cleanarchive.domein.usecase

import com.example.cleanarchive.data.models.ContactEntity
import com.example.cleanarchive.domein.repositories.ContactRepository

class DeleteContactUseCase(
    private val contactRepository: ContactRepository
) {
    suspend fun deleteContact(contactEntity: ContactEntity) {
        contactRepository.deleteContact(contactEntity)
    }
}