package com.apnamart.photolibrary.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.apnamart.photolibrary.R
import com.apnamart.photolibrary.databinding.FragmentPhotosBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotosFragment : Fragment() {

    private val viewModel: PhotosViewModel by viewModels()
    private lateinit var binding: FragmentPhotosBinding
    private val photosAdapter = PhotosAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhotosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {

        viewModel.dataLoading.observe(viewLifecycleOwner) { loading ->
            when (loading) {
                true -> {
                    binding.pbLoading.isVisible = true
                    binding.rvPhotos.isVisible = false
                }
                false -> {
                    binding.pbLoading.isVisible = false
                    binding.rvPhotos.isVisible = true
                }
            }
        }

        binding.rvPhotos.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = photosAdapter
        }

        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(
                requireContext(), getString(R.string.an_error_has_occurred, it),
                Toast.LENGTH_SHORT
            ).show()
        }

        viewModel.photos.observe(viewLifecycleOwner) {
            photosAdapter.submitUpdate(it)
        }

        viewModel.getPhotos()
    }
}