package com.example.cleanarchive.presentation.ui.fragments.contact.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarchive.data.models.ContactEntity
import com.example.cleanarchiveapp.databinding.ItemContactBinding
import kotlin.reflect.KFunction1

class ContactAdapter(
    private val onClick: KFunction1<ContactEntity, Unit>
) : RecyclerView.Adapter<ContactAdapter.FamilyViewHolder>() {
    private val contactList = arrayListOf<ContactEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FamilyViewHolder {
        return FamilyViewHolder(
            ItemContactBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addTasks(family: List<ContactEntity>) {
        contactList.clear()
        contactList.addAll(family)
        notifyDataSetChanged()
    }

    fun getContact(position : Int): ContactEntity {
        return contactList[position]
    }

    override fun getItemCount(): Int = contactList.size

    override fun onBindViewHolder(holder: FamilyViewHolder, position: Int) {
        holder.onBind(contactEntity = contactList[position])
    }

    inner class FamilyViewHolder(private val binding: ItemContactBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(contactEntity: ContactEntity) {
            with(binding){
                tvName.text = contactEntity.name
                tvNumber.text = contactEntity.number
                tvAddress.text = contactEntity.address
            }
            itemView.setOnClickListener {
                onClick(getContact(adapterPosition))
            }
        }

        private fun onClick(contact: ContactEntity) {

        }
    }
}