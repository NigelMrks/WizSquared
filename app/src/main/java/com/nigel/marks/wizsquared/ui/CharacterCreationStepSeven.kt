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
import com.nigel.marks.wizsquared.databinding.FragmentCharacterCreationStepSevenBinding

class CharacterCreationStepSeven : Fragment() {
    private var _binding: FragmentCharacterCreationStepSevenBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterCreationStepSevenBinding.inflate(inflater, container, false)
        val view = binding.root

        //Set Number of Steps
        binding.ccStepSevenStepCount.text = resources.getStringArray(R.array.cc_steps)[6]

        //Navigation-Buttons
        //Back Button
        binding.ccStepSevenBackButton.setOnClickListener{
            findNavController().navigate(R.id.cc_step7_back)
        }
        //Cancel Button
        binding.ccStepSevenCancelButton.setOnClickListener {
            viewModel.resetCharacterTempSave()
            findNavController().navigate(R.id.cc_step7_cancel)
        }
        //Next Button
        binding.ccStepSevenNextButton.setOnClickListener {
            findNavController().navigate(R.id.cc_step7_next)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}