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
import com.nigel.marks.wizsquared.data.model.SelectorBox
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

        //Apply Adapter to RV
        binding.selectorBoxRecycler.layoutManager = LinearLayoutManager(requireContext())
        var adapter = SelectionBoxAdapter(listOf(), requireContext(), mutableMapOf())
        binding.selectorBoxRecycler.adapter = adapter

        //OnItemSelectedListener: Change Shown Objects on Selected Item in Spinner
        binding.ccStepOneRaceSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                val adapterList: List<SelectorBox>
                val saveMap: MutableMap<String,String>
                when (binding.ccStepOneRaceSpinner.selectedItem.toString()) {
                    "Dragonborn" -> {
                        adapterList = viewModel.repository.raceDragonborn
                        saveMap = viewModel.repository.raceMapDragonborn
                    }
                    "Dwarf" -> {
                        adapterList = viewModel.repository.raceDwarf
                        saveMap = viewModel.repository.raceMapDwarf
                    }
                    "Elf" -> {
                        adapterList = viewModel.repository.raceElf
                        saveMap = viewModel.repository.raceMapElf
                    }
                    "Gnome" -> {
                        adapterList = viewModel.repository.raceGnome
                        saveMap = viewModel.repository.raceMapGnome
                    }
                    "Half-Elf" -> {
                        adapterList = viewModel.repository.raceHalfElf
                        saveMap = viewModel.repository.raceMapHalfElf
                    }
                    "Half-Orc" -> {
                        adapterList = viewModel.repository.raceHalfOrc
                        saveMap = viewModel.repository.raceMapHalfOrc
                    }
                    "Halfling" -> {
                        adapterList = viewModel.repository.raceHalfling
                        saveMap = viewModel.repository.raceMapHalfling
                    }
                    "Human" -> {
                        adapterList = viewModel.repository.raceHuman
                        saveMap = viewModel.repository.raceMapHuman
                    }
                    "Tiefling" -> {
                        adapterList = viewModel.repository.raceTiefling
                        saveMap = viewModel.repository.raceMapTiefling
                    }
                    else -> {
                        adapterList = listOf()
                        saveMap = mutableMapOf()
                    }
                }
                adapter = SelectionBoxAdapter(adapterList, requireContext(), saveMap)
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
            findNavController().navigate(R.id.cc_step1_cancel)
        }
        //Next Button
        binding.ccStepOneNextButton.setOnClickListener{
            findNavController().navigate(R.id.cc_step1_next)
        }


        return view
    }

    //to prevent memory leakage
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}