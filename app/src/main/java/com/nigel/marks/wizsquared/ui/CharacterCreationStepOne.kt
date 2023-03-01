package com.nigel.marks.wizsquared.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.nigel.marks.wizsquared.MainViewModel
import com.nigel.marks.wizsquared.R
import com.nigel.marks.wizsquared.databinding.FragmentCharacterCreationStepOneBinding
import java.util.*
import kotlin.collections.ArrayList


/**
 * A simple [Fragment] subclass.
 * Use the [CharacterList.newInstance] factory method to
 * create an instance of this fragment.
 */
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

        //code goes here
        val racesArrayAdapter = ArrayAdapter<Any?>(
            requireContext(),
            R.layout.spinner_list,
            resources.getStringArray(R.array.races_selection_spinner)
        )
        //racesArrayAdapter.setDropDownViewResource(R.layout.spinner_list)
        binding.ccStepOneRaceSpinner.adapter = racesArrayAdapter

        return view
    }

    //to prevent memory leakage
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}