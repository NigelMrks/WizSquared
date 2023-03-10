package com.nigel.marks.wizsquared.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.nigel.marks.wizsquared.MainViewModel
import com.nigel.marks.wizsquared.R
import com.nigel.marks.wizsquared.adapter.SelectionBoxAdapter
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
        var adapter = SelectionBoxAdapter(viewModel.repository.classNoneSelected, requireContext())
        binding.selectorBoxRecyclerStepTwo.adapter = adapter
        //OnItemSelectedListener: Change Shown Objects on Selected Item in Spinner
        binding.ccStepTwoClassSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                adapter = when (binding.ccStepTwoClassSpinner.selectedItem.toString()) {
                    "Choose" -> {
                        SelectionBoxAdapter(viewModel.repository.classNoneSelected, requireContext())
                    }
                    "Barbarian" -> {
                        SelectionBoxAdapter(viewModel.repository.classBarbarian, requireContext())
                    }
                    else -> {
                        SelectionBoxAdapter(listOf(), requireContext())
                    }
                }
                binding.selectorBoxRecyclerStepTwo.adapter = adapter
                adapter.notifyDataSetChanged()

            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // your code here
            }
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}