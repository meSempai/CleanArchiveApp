package com.example.cleanarchive.data.repositories

import com.example.cleanarchive.data.local.ContactDao
import com.example.cleanarchive.data.mappers.toContact
import com.example.cleanarchive.data.mappers.toEntity
import com.example.cleanarchive.data.models.ContactEntity
import com.example.cleanarchive.domein.models.Contacts
import com.example.cleanarchive.domein.repositories.ContactRepository
import com.example.cleanarchive.domein.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ContactRepositoryImpl @Inject constructor(private val contactDao: ContactDao) :
    ContactRepository {

    override suspend fun addContact(contactEntity: ContactEntity) {
        contactDao.addContact(contactEntity.toEntity())
    }


    override suspend fun updateContact(contactEntity: ContactEntity) {
        contactDao.updateContact(contactEntity.toEntity())
    }

    override suspend fun deleteContact(contactEntity: ContactEntity) {
        contactDao.deleteContact(contactEntity.toEntity())
    }

    override suspend fun getContacts(): Flow<Resource<List<Contacts>>> {
        return flow {
            emit(Resource.Loading())
            try {
                val list = contactDao.getContact()
                val data = list.map { it.toContact() }
                emit(Resource.Success(data))
            } catch (e: Exception) {
                emit(Resource.Error(e.localizedMessage ?: "unknown error"))
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun getContactFromLast(): Flow<Resource<List<Contacts>>> {
        return flow {
            emit(Resource.Loading())
            try {
                val list = contactDao.getContactFromLast()
                val data = list.map { it.toContact() }
                emit(Resource.Success(data))
            } catch (e: Exception) {
                emit(Resource.Error(e.localizedMessage ?: "unknown error"))
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun getContactSortByName(): Flow<Resource<List<Contacts>>> {
        return flow {
            emit(Resource.Loading())
            try {
                val list = contactDao.getContactSortByName()
                val data = list.map { it.toContact() }
                emit(Resource.Success(data))
            } catch (e: Exception) {
                emit(Resource.Error(e.localizedMessage ?: "unknown error"))
            }
        }.flowOn(Dispatchers.IO)
    }
}