package com.nigel.marks.wizsquared.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
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
    private lateinit var inputs: List<EditText>
    private val keys = listOf<String>(
        "name",
        "age",
        "height",
        "weight",
        "eyes",
        "hair",
        "skin"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterCreationStepSevenBinding.inflate(inflater, container, false)
        val view = binding.root

        inputs = listOf<EditText>(
            binding.ccCharacterNameEdit,
            binding.ccAgeEdit,
            binding.ccHeightEdit,
            binding.ccWeightEdit,
            binding.ccEyesEdit,
            binding.ccHairEdit,
            binding.ccSkinEdit
        )

        //Set Number of Steps
        binding.ccStepSevenStepCount.text = resources.getStringArray(R.array.cc_steps)[6]

        loadInputs()

        //Navigation-Buttons
        //Back Button
        binding.ccStepSevenBackButton.setOnClickListener{
            saveInputs()
            findNavController().navigate(R.id.cc_step7_back)
        }
        //Cancel Button
        binding.ccStepSevenCancelButton.setOnClickListener {
            viewModel.resetCharacterTempSave()
            findNavController().navigate(R.id.cc_step7_cancel)
        }
        //Next Button
        binding.ccStepSevenNextButton.setOnClickListener {
            saveInputs()
            findNavController().navigate(R.id.cc_step7_next)
        }

        return view
    }
    private fun saveInputs() {
        for (input in inputs) {
            if (input.text != null && input.text.toString() != "") {
                val key = keys[inputs.indexOf(input)]
                viewModel.characterTempSave.bio[key]= input.text.toString()
            }
        }
    }
    private fun loadInputs() {
        for (input in inputs) {
            val key = keys[inputs.indexOf(input)]
            input.setText(viewModel.characterTempSave.bio[key])
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}