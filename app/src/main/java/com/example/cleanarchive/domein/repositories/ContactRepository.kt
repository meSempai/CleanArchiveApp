package com.example.cleanarchive.domein.repositories

import com.example.cleanarchive.data.models.ContactEntity
import com.example.cleanarchive.domein.models.Contacts
import com.example.cleanarchive.domein.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ContactRepository {
    suspend fun addContact(contactEntity: ContactEntity)

    suspend fun getContacts(): Flow<Resource<List<Contacts>>>

    suspend fun updateContact(contactEntity: ContactEntity)

    suspend fun deleteContact(contactEntity: ContactEntity)
    fun getContactFromLast(): Flow<Resource<List<Contacts>>>
    fun getContactSortByName(): Flow<Resource<List<Contacts>>>
}