package com.example.cleanarchive.data.repositories

import com.example.cleanarchive.data.local.ContactDao
import com.example.cleanarchive.data.models.ContactEntity
import com.example.cleanarchive.domein.repositories.ContactRepository

class ContactRepositoryImpl(
    private val contactDao: ContactDao
): ContactRepository {
    override suspend fun addContact(contactEntity: ContactEntity) {
        contactDao.addContact(contactEntity)
    }

    override suspend fun getContacts(): List<ContactEntity> {
        return contactDao.getContacts()
    }

    override suspend fun updateContact(contactEntity: ContactEntity) {
        contactDao.updateContact(contactEntity)
    }

    override suspend fun deleteContact(contactEntity: ContactEntity) {
        contactDao.deleteContact(contactEntity)
    }
}