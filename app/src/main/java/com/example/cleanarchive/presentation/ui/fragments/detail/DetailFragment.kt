package com.example.cleanarchive.presentation.ui.fragments.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.cleanarchive.data.models.ContactEntity
import com.example.cleanarchive.presentation.ui.fragments.contact.ContactFragment.Companion.KEY_CONTACT
import com.example.cleanarchiveapp.R
import com.example.cleanarchiveapp.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private val viewModel by viewModels<DetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getContact()
        initClickListeners()
    }

    private fun getContact() {
        with(binding){
            val contactEntity = (arguments?.getSerializable(KEY_CONTACT)) as ContactEntity
            etName.setText(contactEntity.name)
            etMother.setText(contactEntity.number)
            etFather.setText(contactEntity.address)
        }
    }

    private fun initClickListeners() {
        with(binding) {
            val contactEntity = (arguments?.getSerializable(KEY_CONTACT)) as ContactEntity
            btnUpdate.setOnClickListener {
                viewModel.viewModelScope.launch {
                    viewModel.updateContact(
                        ContactEntity(
                            id = contactEntity.id,
                            name = binding.etName.text.toString(),
                            number = binding.etMother.text.toString(),
                            address = binding.etFather.text.toString()
                        )
                    )
                }
                findNavController().navigateUp()
            }
            btnDelete.setOnClickListener {
                viewModel.viewModelScope.launch {
                    viewModel.deleteContact(contactEntity)
                }
                findNavController().navigateUp()
            }
        }
    }
}