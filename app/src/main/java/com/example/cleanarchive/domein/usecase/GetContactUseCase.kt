package com.example.cleanarchive.domein.usecase

import com.example.cleanarchive.data.models.ContactEntity
import com.example.cleanarchive.domein.models.Contacts
import com.example.cleanarchive.domein.repositories.ContactRepository
import com.example.cleanarchive.domein.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetContactUseCase(
    private val contactRepository : ContactRepository
) {
suspend fun execute(contactEntity: ContactEntity){

}

    suspend fun getContacts(): Flow<Resource<List<Contacts>>> {
        return contactRepository.getContacts()
    }
}