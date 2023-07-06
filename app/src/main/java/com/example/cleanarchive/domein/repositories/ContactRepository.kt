package com.example.cleanarchive.domein.repositories

import com.example.cleanarchive.data.models.ContactEntity

interface ContactRepository {
    suspend fun addContact(contactEntity: ContactEntity)

    suspend fun getContacts(): List<ContactEntity>

    suspend fun updateContact(contactEntity: ContactEntity)

    suspend fun deleteContact(contactEntity: ContactEntity)
}