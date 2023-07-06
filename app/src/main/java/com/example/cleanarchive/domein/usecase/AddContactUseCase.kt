package com.example.cleanarchive.domein.usecase

import com.example.cleanarchive.data.models.ContactEntity
import com.example.cleanarchive.domein.repositories.ContactRepository

class AddContactUseCase(
    private val contactRepository : ContactRepository
) {

    suspend fun addContact(contactEntity: ContactEntity) {
        contactRepository.addContact(contactEntity)
    }

}