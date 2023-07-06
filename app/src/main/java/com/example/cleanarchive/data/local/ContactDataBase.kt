package com.example.cleanarchive.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cleanarchive.data.models.ContactEntity

@Database(entities = [ContactEntity::class], version = 1)
abstract class ContactDataBase: RoomDatabase() {
    abstract fun contactDao(): ContactDao
}