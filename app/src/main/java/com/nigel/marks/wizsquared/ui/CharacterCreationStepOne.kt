package com.nigel.marks.wizsquared.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nigel.marks.wizsquared.MainViewModel
import com.nigel.marks.wizsquared.R
import com.nigel.marks.wizsquared.adapter.SelectionBoxAdapter
import com.nigel.marks.wizsquared.databinding.FragmentCharacterCreationStepOneBinding

class CharacterCreationStepOne : Fragment() {

    private var _binding: FragmentCharacterCreationStepOneBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterCreationStepOneBinding.inflate(inflater, container, false)
        val view = binding.root

        //Set Number of Steps
        binding.ccStepOneStepCount.text = resources.getStringArray(R.array.cc_steps)[0]

        //Code for Spinner
        //Create Adapter for Spinner with text layout and List to use
        binding.ccStepOneRaceSpinner.adapter = ArrayAdapter<Any?>(
            requireContext(),
            R.layout.spinner_list,
            resources.getStringArray(R.array.races_selection_spinner)
        )
        //Apply Adapter to Spinner
        binding.selectorBoxRecycler.layoutManager = LinearLayoutManager(requireContext())
        var adapter = SelectionBoxAdapter(viewModel.repository.raceNoneSelected, requireContext())
        binding.selectorBoxRecycler.adapter = adapter

        //OnItemSelectedListener: Change Shown Objects on Selected Item in Spinner
        binding.ccStepOneRaceSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                adapter = when (binding.ccStepOneRaceSpinner.selectedItem.toString()) {
                    "Choose" -> {
                        SelectionBoxAdapter(viewModel.repository.raceNoneSelected, requireContext())
                    }
                    "Dragonborn" -> {
                        SelectionBoxAdapter(viewModel.repository.raceDragonborn, requireContext())
                    }
                    "Dwarf" -> {
                        SelectionBoxAdapter(viewModel.repository.raceDwarf, requireContext())
                    }
                    else -> {
                        SelectionBoxAdapter(listOf(), requireContext())
                    }
                }
                binding.selectorBoxRecycler.adapter = adapter
                adapter.notifyDataSetChanged()

                //Save Selection to TempSave
                viewModel.characterTempSave.selectedRace = position
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // your code here
            }
        }

        //Set Spinner to previously selected Item
        binding.ccStepOneRaceSpinner.setSelection(viewModel.characterTempSave.selectedRace)

        //Navigation-Buttons
        //Cancel Button
        binding.ccStepOneCancelButton.setOnClickListener{
            viewModel.resetCharacterTempSave()
            findNavController().navigate(CharacterCreationStepOneDirections.actionCharacterCreationStepOneToCharacterList())
        }
        //Next Button
        binding.ccStepOneNextButton.setOnClickListener{
            findNavController().navigate(CharacterCreationStepOneDirections.actionCharacterCreationStepOneToCharacterCreationStepTwo())
        }


        return view
    }

    //to prevent memory leakage
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}