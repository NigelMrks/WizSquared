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
import com.nigel.marks.wizsquared.databinding.FragmentCharacterCreationReviewBinding
import com.nigel.marks.wizsquared.databinding.FragmentCharacterCreationStepOneBinding

class CharacterCreationReview : Fragment() {

    private var _binding: FragmentCharacterCreationReviewBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterCreationReviewBinding.inflate(inflater, container, false)
        val view = binding.root

        //Parse TempSave into PlayerCharacter
        val newCharacter = viewModel.compileCharacterTempSave()

        //Set Texts
        if (newCharacter.characterName != "") binding.characterNameText.text = newCharacter.characterName
        else binding.characterNameText.text = "No Name Input"

        if (newCharacter.race != "") {
            if (newCharacter.subrace != "") {
                binding.characterRaceText.text = newCharacter.subrace + " " + newCharacter.race
            }
            else binding.characterRaceText.text = newCharacter.race
        }
        else binding.characterRaceText.text = "No Race selected"

        if (newCharacter.selClass != "") {
            if (newCharacter.subclass != "") {
                binding.characterClassText.text = newCharacter.subclass + " " + newCharacter.selClass
            }
            else binding.characterClassText.text = newCharacter.selClass
        }
        else binding.characterClassText.text = "No Class selected"

        if (viewModel.characterTempSave.isUsingClassEquipment) {
            binding.eqmethodText.text = "Class & Background"
        }
        else binding.eqmethodText.text = "Starting Wealth"

        if (newCharacter.background != "") binding.backgroundText.text = newCharacter.background
        else binding.backgroundText.text = "No Background input"

        binding.strScore.text = newCharacter.strength.toString()
        binding.dexScore.text = newCharacter.dexterity.toString()
        binding.conScore.text = newCharacter.constitution.toString()
        binding.intScore.text = newCharacter.intelligence.toString()
        binding.wisScore.text = newCharacter.wisdom.toString()
        binding.chaScore.text = newCharacter.charisma.toString()

        //Button-Navigation
        binding.reviewButtonBack.setOnClickListener {
            findNavController().navigate(R.id.review_back)
        }
        binding.reviewButtonDiscard.setOnClickListener {
            viewModel.resetCharacterTempSave()
            findNavController().navigate(R.id.review_final)
        }
        binding.reviewButtonSave.setOnClickListener {
            viewModel.resetCharacterTempSave()
            viewModel.repository.characterList.add(newCharacter)
            findNavController().navigate(R.id.review_final)
        }

        return view
    }

    //to prevent memory leakage
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}