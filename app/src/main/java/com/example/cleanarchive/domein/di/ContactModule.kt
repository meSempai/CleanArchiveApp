package com.example.cleanarchive.domein.di

import android.content.Context
import androidx.room.Room
import com.example.cleanarchive.data.local.ContactDao
import com.example.cleanarchive.data.local.ContactDataBase
import com.example.cleanarchive.data.repositories.ContactRepositoryImpl
import com.example.cleanarchive.domein.repositories.ContactRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ContactModule {

    @Singleton
    @Provides
    fun provideContactDataBase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context = context,
        ContactDataBase::class.java,
        "contact_db"
    ).build()

    @Provides
    fun provideContactDao(contactDataBase: ContactDataBase) = contactDataBase.contactDao()

    @Provides
    fun provideContactRepository(contactDao: ContactDao): ContactRepository {
        return ContactRepositoryImpl(contactDao = contactDao)
    }
}