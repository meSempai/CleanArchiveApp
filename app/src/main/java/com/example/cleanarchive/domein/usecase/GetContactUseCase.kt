package com.example.cleanarchive.domein.usecase

import com.example.cleanarchive.data.models.ContactEntity
import com.example.cleanarchive.domein.repositories.ContactRepository

class GetContactsUseCase(
    private val contactRepository : ContactRepository
) {
    suspend fun getContacts(): List<ContactEntity> {
        return contactRepository.getContacts()
    }
}