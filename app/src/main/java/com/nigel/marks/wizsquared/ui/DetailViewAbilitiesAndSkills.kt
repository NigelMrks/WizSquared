package com.nigel.marks.wizsquared.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nigel.marks.wizsquared.MainViewModel
import com.nigel.marks.wizsquared.R
import com.nigel.marks.wizsquared.adapter.SkillProfAdapter
import com.nigel.marks.wizsquared.databinding.FragmentDetailViewAbilitiesAndSkillsBinding

class DetailViewAbilitiesAndSkills : Fragment() {

    private var _binding: FragmentDetailViewAbilitiesAndSkillsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailViewAbilitiesAndSkillsBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.dvStr.text = "STR - " + viewModel.viewedPlayerCharacter.strength
        binding.dvDex.text = "DEX - " + viewModel.viewedPlayerCharacter.dexterity
        binding.dvCon.text = "CON - " + viewModel.viewedPlayerCharacter.constitution
        binding.dvInt.text = "INT - " + viewModel.viewedPlayerCharacter.intelligence
        binding.dvWis.text = "WIS - " + viewModel.viewedPlayerCharacter.wisdom
        binding.dvCha.text = "CHA - " + viewModel.viewedPlayerCharacter.charisma

        //RecyclerViewAdapter
        binding.skillRecycler.layoutManager = LinearLayoutManager(requireContext())
        val skillProfs = viewModel.viewedPlayerCharacter.skillProficiencies.keys.toList()
        val proficiencies = viewModel.viewedPlayerCharacter.skillProficiencies.values.toList()
        val adapter = SkillProfAdapter(skillProfs,proficiencies,requireContext())
        binding.skillRecycler.adapter = adapter

        binding.dvForward0.setOnClickListener {
            findNavController().navigate(R.id.dv0dv1)
        }

        return view
    }

    //to prevent memory leakage
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}