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
import com.nigel.marks.wizsquared.databinding.FragmentCharacterCreationStepTwoBinding

class CharacterCreationStepTwo : Fragment() {
    private var _binding: FragmentCharacterCreationStepTwoBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterCreationStepTwoBinding.inflate(inflater, container, false)
        val view = binding.root
        val layoutManager = LinearLayoutManager(context).apply {
            recycleChildrenOnDetach = false
        }


        //Set Number of Steps
        binding.ccStepTwoStepCount.text = resources.getStringArray(R.array.cc_steps)[1]

        //Code for Spinner
        //Create Adapter for Spinner with text layout and List to use
        binding.ccStepTwoClassSpinner.adapter = ArrayAdapter<Any?>(
            requireContext(),
            R.layout.spinner_list,
            resources.getStringArray(R.array.classes_selection_spinner)
        )
        //Recyclerview Adapter
        binding.selectorBoxRecyclerStepTwo.layoutManager = LinearLayoutManager(requireContext())
        var adapter = SelectionBoxAdapter(listOf(), requireContext(), mutableMapOf())
        binding.selectorBoxRecyclerStepTwo.adapter = adapter
        binding.selectorBoxRecyclerStepTwo.layoutManager = layoutManager
        //OnItemSelectedListener: Change Shown Objects on Selected Item in Spinner
        binding.ccStepTwoClassSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                val adapterList: List<SelectorBox>
                val saveMap: MutableMap<String,String>
                when (binding.ccStepTwoClassSpinner.selectedItem.toString()) {
                    "Barbarian" -> {
                        adapterList = viewModel.repository.classBarbarian
                        saveMap = viewModel.repository.classMapBarbarian
                    }
                    "Bard" -> {
                        adapterList = viewModel.repository.classBard
                        saveMap = viewModel.repository.classMapBard
                    }
                    "Cleric" -> {
                        adapterList = viewModel.repository.classCleric
                        saveMap = viewModel.repository.classMapCleric
                    }
                    "Druid" -> {
                        adapterList = viewModel.repository.classDruid
                        saveMap = viewModel.repository.classMapDruid
                    }
                    "Fighter" -> {
                        adapterList = viewModel.repository.classFighter
                        saveMap = viewModel.repository.classMapFighter
                    }
                    "Monk" -> {
                        adapterList = viewModel.repository.classMonk
                        saveMap = viewModel.repository.classMapMonk
                    }
                    "Paladin" -> {
                        adapterList = viewModel.repository.classPaladin
                        saveMap = viewModel.repository.classMapPaladin
                    }
                    "Ranger" -> {
                        adapterList = viewModel.repository.classRanger
                        saveMap = viewModel.repository.classMapRanger
                    }
                    "Rogue" -> {
                        adapterList = viewModel.repository.classRogue
                        saveMap = viewModel.repository.classMapRogue
                    }
                    "Sorcerer" -> {
                        adapterList = viewModel.repository.classSorcerer
                        saveMap = viewModel.repository.classMapSorcerer
                    }
                    "Warlock" -> {
                        adapterList = viewModel.repository.classWarlock
                        saveMap = viewModel.repository.classMapWarlock
                    }
                    "Wizard" -> {
                        adapterList = viewModel.repository.classWizard
                        saveMap = viewModel.repository.classMapWizard
                    }
                    else -> {
                        adapterList = listOf()
                        saveMap = mutableMapOf()
                    }
                }
                adapter = SelectionBoxAdapter(adapterList, requireContext(), saveMap)
                viewModel.clearWealth()
                binding.selectorBoxRecyclerStepTwo.adapter = adapter
                adapter.notifyDataSetChanged()

                //Save Selection to TempSave
                viewModel.characterTempSave.selectedClass = position
                //HasSpells
                viewModel.characterTempSave.hasSpells =
                    viewModel.characterTempSave.selectedClass !in listOf(0,1,5,6,7,8,9)
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // your code here
            }
        }
        //Set Spinner to previously selected Item
        binding.ccStepTwoClassSpinner.setSelection(viewModel.characterTempSave.selectedClass)

        //Navigation-Buttons
        //Back Button
        binding.ccStepTwoBackButton.setOnClickListener{
            findNavController().navigate(R.id.cc_step2_back)
        }
        //Cancel Button
        binding.ccStepTwoCancelButton.setOnClickListener {
            viewModel.resetCharacterTempSave()
            findNavController().navigate(R.id.cc_step2_cancel)
        }
        //Next Button
        binding.ccStepTwoNextButton.setOnClickListener {
            findNavController().navigate(R.id.cc_step2_next)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}