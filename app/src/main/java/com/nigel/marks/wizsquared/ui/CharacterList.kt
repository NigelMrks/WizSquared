package com.nigel.marks.wizsquared.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.R
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nigel.marks.wizsquared.MainViewModel
import com.nigel.marks.wizsquared.adapter.CharacterAdapter
import com.nigel.marks.wizsquared.adapter.EquipmentAdapter
import com.nigel.marks.wizsquared.databinding.FragmentCharacterListBinding

class CharacterList : Fragment() {

    private var _binding: FragmentCharacterListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel.viewedPlayerCharacter = viewModel.emptyPlayerCharacter

        // Create New Character Button
        binding.listNewButton.setOnClickListener {
            viewModel.repository.resetMaps()
            findNavController().navigate(com.nigel.marks.wizsquared.R.id.home_to_character_creation)
        }

        //RecyclerViewAdapter
        binding.characterRecycler.layoutManager = LinearLayoutManager(requireContext())
        val adapter = CharacterAdapter(viewModel.repository.characterList)
        binding.characterRecycler.adapter = adapter

        return view
    }

    //to prevent memory leakage
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}