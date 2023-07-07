package com.example.cleanarchive.presentation.ui.fragments.contact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.cleanarchive.data.models.ContactEntity
import com.example.cleanarchive.presentation.ui.fragments.contact.adapter.ContactAdapter
import com.example.cleanarchive.presentation.utils.UiState
import com.example.cleanarchiveapp.R
import com.example.cleanarchiveapp.databinding.FragmentContactBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ContactFragment : Fragment() {
    private lateinit var binding: FragmentContactBinding
    private val viewModel by viewModels<ContactViewModel>()
    private val adapter = ContactAdapter(onClick = ::onClick)

    private fun onClick(contactEntity: ContactEntity) {
        findNavController().navigate(
            R.id.nav_host_fragment,
            bundleOf(KEY_CONTACT to contactEntity)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContactBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initOnClickListeners()
        viewModelListener()
    }

    private fun initOnClickListeners() {
        with(binding) {
            btnSave.setOnClickListener {
                viewModel.addContact(
                    ContactEntity(
                        id = (0..9999).random(),
                        name = etName.text.toString(),
                        number = etNumber.text.toString(),
                        address = etAddress.text.toString()
                    )
                )
            }
        }
    }

    private fun viewModelListener() {
        viewModel.getAllContact()
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getAllContactStates.collect {
                when (it) {
                    is UiState.EmptyState -> {
                        Toast.makeText(requireContext(), "Empty state", Toast.LENGTH_SHORT).show()
                    }

                    is UiState.Error -> {
                        binding.progressbar.isVisible = false
                        Toast.makeText(requireContext(), "Error ${it.message}", Toast.LENGTH_SHORT)
                            .show()
                    }

                    is UiState.Loading -> {
                        binding.progressbar.isVisible = true
                    }

                    is UiState.Success -> {
                        binding.progressbar.isVisible = false
                        binding.rvContact.adapter = adapter
                        adapter.addTasks(it.data)
                    }
                }
            }
        }
    }

    companion object {
        const val KEY_CONTACT = "keyContactId"
    }
}
