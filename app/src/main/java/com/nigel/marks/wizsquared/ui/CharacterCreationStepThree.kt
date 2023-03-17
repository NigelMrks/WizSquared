package com.nigel.marks.wizsquared.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.card.MaterialCardView
import com.nigel.marks.wizsquared.MainViewModel
import com.nigel.marks.wizsquared.R
import com.nigel.marks.wizsquared.adapter.SelectionBoxAdapter
import com.nigel.marks.wizsquared.databinding.FragmentCharacterCreationStepOneBinding
import com.nigel.marks.wizsquared.databinding.FragmentCharacterCreationStepThreeBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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

        val spinners = listOf<Spinner>(
            binding.spinnerAbilityOne,
            binding.spinnerAbilityTwo,
            binding.spinnerAbilityThree,
            binding.spinnerAbilityFour,
            binding.spinnerAbilityFive,
            binding.spinnerAbilitySix
        )

        //Set Number of Steps
        binding.ccStepThreeStepCount.text = resources.getStringArray(R.array.cc_steps)[2]

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
        for (spinner in spinners) {
            spinner.adapter = abilityScoreSpinnerAdapter
        }

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
                viewModel.characterTempSave.abilityMethod = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        //setOnItemSelectedListeners for all Ability Spinners
        for (spinner in spinners) {
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    abilitySpinnersListener(spinners.indexOf(spinner), position)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }

        //Set initial Ability-Spinner Positions
        binding.ccStepThreeAbilitySpinner.setSelection(viewModel.characterTempSave.abilityMethod)
        for (i in 0..5) {
            spinners[i].setSelection(viewModel.characterTempSave.abilityScores[i])
        }

        //Roll-Button
        binding.rollButton.setOnClickListener {
            viewModel.characterTempSave.rolledAbilities = viewModel.rollForStats()
            setVisibilities(1)
        }

        //Navigation-Buttons
        //Back Button
        binding.ccStepThreeBackButton.setOnClickListener{
            findNavController().navigate(CharacterCreationStepThreeDirections.actionCharacterCreationStepThreeToCharacterCreationStepTwo())
        }
        //Cancel Button
        binding.ccStepThreeCancelButton.setOnClickListener {
            viewModel.resetCharacterTempSave()
            findNavController().navigate(CharacterCreationStepThreeDirections.actionCharacterCreationStepThreeToCharacterList())
        }
        //Next Button
        binding.ccStepThreeNextButton.setOnClickListener {
            findNavController().navigate(CharacterCreationStepThreeDirections.actionCharacterCreationStepThreeToCharacterCreationStepFour())
        }

        return view
    }

    fun setVisibilities(selected: Int) {
        val abilityScores: List<Int>
        val textViews: List<TextView> = listOf(
            binding.abilityOne,
            binding.abilityTwo,
            binding.abilityThree,
            binding.abilityFour,
            binding.abilityFive,
            binding.abilitySix
        )
        val cardViews: List<MaterialCardView> = listOf(
            binding.abilityScoreCard1,
            binding.abilityScoreCard2,
            binding.abilityScoreCard3,
            binding.abilityScoreCard4,
            binding.abilityScoreCard5,
            binding.abilityScoreCard6
        )
        for (textView in textViews) textView.text = "00"
        when (selected) {
            0 -> {
                for (cardView in cardViews) {
                    cardView.visibility = View.GONE
                }
                binding.rollButton.visibility = View.GONE
            }
            1 -> {
                abilityScores = viewModel.getAbilityScores("roll")
                if (abilityScores.isEmpty()) {
                    binding.rollButton.visibility = View.VISIBLE
                    for (cardView in cardViews) {
                        cardView.visibility = View.GONE
                    }
                }
                else {
                    for (cardView in cardViews) {
                        cardView.visibility = View.VISIBLE
                    }
                    binding.rollButton.visibility = View.GONE

                    /** Couroutine startet hier */
                    lifecycleScope.launch {
                        for (i in 0..5) {
                            loadScoreAnimation(textViews[i], abilityScores[i])
                            delay((abilityScores[i]*100+100).toLong())
                        }
                    }
                    /** Coroutine endet hier */
                }
            }
            2 -> {
                abilityScores = viewModel.getAbilityScores("standard")

                for (cardView in cardViews) {
                    cardView.visibility = View.VISIBLE
                }
                binding.rollButton.visibility = View.GONE

                /** Couroutine startet hier */
                lifecycleScope.launch {
                    for (i in 0..5) {
                        loadScoreAnimation(textViews[i], abilityScores[i])
                        delay((abilityScores[i]*100+100).toLong())
                    }
                }
                /** Coroutine endet hier */
            }
        }

    }

    private fun loadScoreAnimation(textView: TextView, score: Int) {
        /** Couroutine startet hier */
        lifecycleScope.launch {
            for (i in 0..score) {
                textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.primary_light))
                textView.text = String.format("%02d", i)
                delay(80)
                textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.text_beige))
                delay(20)
            }
        }
        /** Coroutine endet hier */
    }

    fun abilitySpinnersListener(index: Int, position: Int) {
        val spinners = listOf<Spinner>(
            binding.spinnerAbilityOne,
            binding.spinnerAbilityTwo,
            binding.spinnerAbilityThree,
            binding.spinnerAbilityFour,
            binding.spinnerAbilityFive,
            binding.spinnerAbilitySix
        )
        for (spinner in spinners) {
            if (spinners.indexOf(spinner) != index && spinners[index].selectedItem == spinner.selectedItem) {
                spinner.setSelection(viewModel.characterTempSave.abilityScores[index])
                viewModel.characterTempSave.abilityScores[spinners.indexOf(spinner)] =
                    viewModel.characterTempSave.abilityScores[index]
            }
        }
        viewModel.characterTempSave.abilityScores[index] = position
    }

    //to prevent memory leakage
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}