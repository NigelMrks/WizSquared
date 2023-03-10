package com.nigel.marks.wizsquared.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.R
import androidx.navigation.fragment.findNavController
import com.nigel.marks.wizsquared.MainViewModel
import com.nigel.marks.wizsquared.databinding.FragmentCharacterListBinding

/**
 * A simple [Fragment] subclass.
 * Use the [CharacterList.newInstance] factory method to
 * create an instance of this fragment.
 */
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

        //code goes here
        binding.listNewButton.setOnClickListener {
            findNavController().navigate(CharacterListDirections.actionCharacterListToCharacterCreationStepOne())
        }

        return view
    }

    //to prevent memory leakage
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}