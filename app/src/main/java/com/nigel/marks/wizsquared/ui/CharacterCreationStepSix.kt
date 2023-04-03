package com.nigel.marks.wizsquared.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nigel.marks.wizsquared.MainViewModel
import com.nigel.marks.wizsquared.R
import com.nigel.marks.wizsquared.adapter.EquipmentAdapter
import com.nigel.marks.wizsquared.adapter.SpellAdapter
import com.nigel.marks.wizsquared.data.entities.Spell
import com.nigel.marks.wizsquared.databinding.FragmentCharacterCreationStepSixBinding
import kotlinx.coroutines.launch

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

        //Filter Spells
        var filteredSpellList = viewModel.getSpells(
            resources.getStringArray(R.array.classes_selection_spinner)[viewModel.characterTempSave.selectedClass],
            1
        )

        //Recyclerview Adapter

        filteredSpellList.observe(viewLifecycleOwner) {
            if (filteredSpellList.value?.isNotEmpty() == true) {
                binding.ccStepSixSpellRecycler.layoutManager = LinearLayoutManager(requireContext())
                val adapter = SpellAdapter(filteredSpellList, requireContext())
                binding.ccStepSixSpellRecycler.adapter = adapter
            }
        }

        /*
        viewModel.spellList.observe(viewLifecycleOwner) {
            binding.ccStepSixSpellRecycler.layoutManager = LinearLayoutManager(requireContext())
            val adapter = SpellAdapter(viewModel.spellList, requireContext())
            binding.ccStepSixSpellRecycler.adapter = adapter
        }
        */


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