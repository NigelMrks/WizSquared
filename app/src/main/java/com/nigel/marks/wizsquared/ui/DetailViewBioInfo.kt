package com.nigel.marks.wizsquared.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.nigel.marks.wizsquared.MainViewModel
import com.nigel.marks.wizsquared.R
import com.nigel.marks.wizsquared.databinding.FragmentDetailViewBioInfoBinding

class DetailViewBioInfo : Fragment() {

    private var _binding: FragmentDetailViewBioInfoBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailViewBioInfoBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.dvBioAlignment.text = viewModel.viewedPlayerCharacter.alignment
        binding.dvBioAge.text = viewModel.viewedPlayerCharacter.age
        binding.dvBioHeight.text = viewModel.viewedPlayerCharacter.height
        binding.dvBioWeight.text = viewModel.viewedPlayerCharacter.weight
        binding.dvBioEyes.text = viewModel.viewedPlayerCharacter.eyes
        binding.dvBioHair.text = viewModel.viewedPlayerCharacter.hair
        binding.dvBioSkin.text = viewModel.viewedPlayerCharacter.skin


        binding.dvBack3.setOnClickListener {
            findNavController().navigate(R.id.dv3dv2)
        }

        return view
    }

    //to prevent memory leakage
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}