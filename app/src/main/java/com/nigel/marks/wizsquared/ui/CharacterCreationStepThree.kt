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
import com.nigel.marks.wizsquared.databinding.FragmentCharacterCreationStepOneBinding
import com.nigel.marks.wizsquared.databinding.FragmentCharacterCreationStepThreeBinding

class CharacterCreationStepThree : Fragment() {

    private var _binding: FragmentCharacterCreationStepThreeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterCreationStepThreeBinding.inflate(inflater, container, false)
        val view = binding.root

        //Set Number of Steps
        binding.ccStepThreeStepCount.text = resources.getStringArray(R.array.cc_steps)[2]

        //Code for Spinner
        //Spinner-Adapters
        binding.ccStepThreeAbilitySpinner.adapter = ArrayAdapter<Any?>(
            requireContext(),
            R.layout.spinner_list,
            resources.getStringArray(R.array.ability_methods)
        )
        var abilityScoreSpinnerAdapter = ArrayAdapter<Any?>(
            requireContext(),
            R.layout.spinner_list,
            resources.getStringArray(R.array.ability_scores)
        )
        binding.spinnerAbilityOne.adapter = abilityScoreSpinnerAdapter
        binding.spinnerAbilityTwo.adapter = abilityScoreSpinnerAdapter
        binding.spinnerAbilityThree.adapter = abilityScoreSpinnerAdapter
        binding.spinnerAbilityFour.adapter = abilityScoreSpinnerAdapter
        binding.spinnerAbilityFive.adapter = abilityScoreSpinnerAdapter
        binding.spinnerAbilitySix.adapter = abilityScoreSpinnerAdapter
        //OnItemSelectedListeners
        //Main Spinner
        binding.ccStepThreeAbilitySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                setVisibilities(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        /*
        //1st Spinner
        binding.spinnerAbilityOne.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                TODO("Not yet implemented")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        //2nd Spinner
        binding.spinnerAbilityTwo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                TODO("Not yet implemented")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        //3rd Spinner
        binding.spinnerAbilityThree.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                TODO("Not yet implemented")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        //4th Spinner
        binding.spinnerAbilityFour.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                TODO("Not yet implemented")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        //5th Spinner
        binding.spinnerAbilityFive.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                TODO("Not yet implemented")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        //6th Spinner
        binding.spinnerAbilitySix.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                TODO("Not yet implemented")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        */

        //Roll-Button
        binding.rollButton.setOnClickListener {
            viewModel.characterTempSave.rolledAbilities = viewModel.rollForStats()
            setVisibilities(1)
        }

        return view
    }

    fun setVisibilities(selected: Int) {
        var abilityScores: List<Int>
        when (selected) {
            0 -> {
                binding.abilityScoreCard1.visibility = View.GONE
                binding.abilityScoreCard2.visibility = View.GONE
                binding.abilityScoreCard3.visibility = View.GONE
                binding.abilityScoreCard4.visibility = View.GONE
                binding.abilityScoreCard5.visibility = View.GONE
                binding.abilityScoreCard6.visibility = View.GONE
                binding.rollButton.visibility = View.GONE
            }
            1 -> {
                abilityScores = viewModel.getAbilityScores("roll")
                if (abilityScores.isEmpty()) {
                    binding.rollButton.visibility = View.VISIBLE
                    binding.abilityScoreCard1.visibility = View.GONE
                    binding.abilityScoreCard2.visibility = View.GONE
                    binding.abilityScoreCard3.visibility = View.GONE
                    binding.abilityScoreCard4.visibility = View.GONE
                    binding.abilityScoreCard5.visibility = View.GONE
                    binding.abilityScoreCard6.visibility = View.GONE
                }
                else {
                    binding.abilityScoreCard1.visibility = View.VISIBLE
                    binding.abilityScoreCard2.visibility = View.VISIBLE
                    binding.abilityScoreCard3.visibility = View.VISIBLE
                    binding.abilityScoreCard4.visibility = View.VISIBLE
                    binding.abilityScoreCard5.visibility = View.VISIBLE
                    binding.abilityScoreCard6.visibility = View.VISIBLE
                    binding.rollButton.visibility = View.GONE

                    binding.abilityOne.text = abilityScores[0].toString()
                    binding.abilityTwo.text = abilityScores[1].toString()
                    binding.abilityThree.text = abilityScores[2].toString()
                    binding.abilityFour.text = abilityScores[3].toString()
                    binding.abilityFive.text = abilityScores[4].toString()
                    binding.abilitySix.text = abilityScores[5].toString()
                }
            }
            2 -> {
                abilityScores = viewModel.getAbilityScores("standard")
                binding.abilityScoreCard1.visibility = View.VISIBLE
                binding.abilityScoreCard2.visibility = View.VISIBLE
                binding.abilityScoreCard3.visibility = View.VISIBLE
                binding.abilityScoreCard4.visibility = View.VISIBLE
                binding.abilityScoreCard5.visibility = View.VISIBLE
                binding.abilityScoreCard6.visibility = View.VISIBLE
                binding.rollButton.visibility = View.GONE

                binding.abilityOne.text = abilityScores[0].toString()
                binding.abilityTwo.text = abilityScores[1].toString()
                binding.abilityThree.text = abilityScores[2].toString()
                binding.abilityFour.text = abilityScores[3].toString()
                binding.abilityFive.text = abilityScores[4].toString()
                binding.abilitySix.text = abilityScores[5].toString()
            }
        }

    }

    //to prevent memory leakage
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}