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
import com.nigel.marks.wizsquared.MainViewModel
import com.nigel.marks.wizsquared.R
import com.nigel.marks.wizsquared.databinding.FragmentDetailViewBinding

class DetailView : Fragment() {

    private var _binding: FragmentDetailViewBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailViewBinding.inflate(inflater, container, false)
        val view = binding.root

        var currentFragment = 0

        val characterIndex = requireArguments().getInt("character_index")
        viewModel.viewedPlayerCharacter = viewModel.repository.characterList[characterIndex]

        val character = viewModel.viewedPlayerCharacter

        if (character.characterName != "") {
            binding.dvNameText.text = character.characterName
        }
        else binding.dvNameText.text = "(No Name)"

        if (character.selClass != "") {
            if (character.subclass != "") {
                binding.dvClassText.text = character.subclass + " " + character.selClass
            }
            else binding.dvClassText.text = character.selClass
        }
        else binding.dvClassText.text = "(No Class)"

        var race = "(No Race)"
        if (character.race != "") {
            race = if (character.subrace != "") {
                character.subrace + " " + character.race
            } else character.race
        }

        var background = ""
        if (character.background != "") {
            background = character.background
        }

        binding.dvRaceBgText.text = "$race $background"

        val categories = listOf(
            "Abilities and Skills",
            "Features",
            "Equipment",
            "Bio & Info"
        )

        binding.dvBackButton.setOnClickListener {
            findNavController().navigate(R.id.detailview_to_home)
        }


        return view
    }

    //to prevent memory leakage
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}