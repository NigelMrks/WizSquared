package com.nigel.marks.wizsquared.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nigel.marks.wizsquared.MainViewModel
import com.nigel.marks.wizsquared.R
import com.nigel.marks.wizsquared.adapter.SpellAdapter
import com.nigel.marks.wizsquared.databinding.FragmentCharacterCreationStepSixBinding

class CharacterCreationStepSix : Fragment() {
    private var _binding: FragmentCharacterCreationStepSixBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterCreationStepSixBinding.inflate(inflater, container, false)
        val view = binding.root

        //Set Number of Steps
        binding.ccStepSixStepCount.text = resources.getStringArray(R.array.cc_steps)[5]

        //Set SpellRecycler or NoSpellText
        if (viewModel.characterTempSave.hasSpells) {
            binding.ccStepSixNoSpellsText.visibility = View.GONE
            binding.ccStepSixSpellRecycler.visibility = View.VISIBLE
        }
        else {
            binding.ccStepSixNoSpellsText.visibility = View.VISIBLE
            binding.ccStepSixSpellRecycler.visibility = View.GONE
        }
        binding.cantripsSelectedText.visibility = binding.ccStepSixSpellRecycler.visibility
        binding.level1SelectedText.visibility = binding.ccStepSixSpellRecycler.visibility

        //Filter Spells
        val filteredSpellList = viewModel.getSpells(
            resources.getStringArray(R.array.classes_selection_spinner)[viewModel.characterTempSave.selectedClass],
            1
        )

        //Recyclerview Adapter
        filteredSpellList.observe(viewLifecycleOwner) {
            if (filteredSpellList.value?.isNotEmpty() == true) {
                binding.ccStepSixSpellRecycler.layoutManager = LinearLayoutManager(requireContext())
                val adapter = SpellAdapter(filteredSpellList, requireContext(), viewModel.characterTempSave)
                binding.ccStepSixSpellRecycler.adapter = adapter
            }
        }

        //Observers of SavedSpell Lists
        viewModel.characterTempSave.cantrips.observe(viewLifecycleOwner) {
            var i = it.size
            binding.cantripsSelectedText.text = "Cantrips $i/3"
        }
        viewModel.characterTempSave.spells.observe(viewLifecycleOwner) {
            var i = it.size
            binding.level1SelectedText.text = "Level-1 Spells $i/3"
        }

        //Navigation-Buttons
        //Back Button
        binding.ccStepSixBackButton.setOnClickListener{
            findNavController().navigate(R.id.cc_step6_back)
        }
        //Cancel Button
        binding.ccStepSixCancelButton.setOnClickListener {
            viewModel.resetCharacterTempSave()
            findNavController().navigate(R.id.cc_step6_cancel)
        }
        //Next Button
        binding.ccStepSixNextButton.setOnClickListener {
            findNavController().navigate(R.id.cc_step6_next)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}