package com.example.cleanarchive.data.mappers

import com.example.cleanarchive.data.models.ContactEntity
import com.example.cleanarchive.domein.models.Contacts


fun ContactEntity.toEntity() : Contacts{
    return Contacts(id = id, name = name, number = number, address = address)
}

fun ContactEntity.toContact() : Contacts{
    return Contacts(id = id, name = name, number = number, address = address)
}