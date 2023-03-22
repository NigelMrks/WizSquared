package com.nigel.marks.wizsquared.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nigel.marks.wizsquared.MainViewModel
import com.nigel.marks.wizsquared.R
import com.nigel.marks.wizsquared.adapter.EquipmentAdapter
import com.nigel.marks.wizsquared.data.model.Equipment
import com.nigel.marks.wizsquared.databinding.FragmentCharacterCreationStepFiveBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CharacterCreationStepFive : Fragment() {
    private var _binding: FragmentCharacterCreationStepFiveBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterCreationStepFiveBinding.inflate(inflater, container, false)
        val view = binding.root

        //Set Number of Steps
        binding.ccStepFiveStepCount.text = resources.getStringArray(R.array.cc_steps)[4]

        //RadioButtons
        binding.eqChoiceRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            if (binding.eqRadioClassAndBg.isChecked) {
                binding.startingWealthCard.visibility = View.GONE
                binding.ccStepFiveEqRecycler.visibility = View.VISIBLE
                viewModel.characterTempSave.isUsingClassEquipment = true
            }
            else {
                binding.startingWealthCard.visibility = View.VISIBLE
                binding.ccStepFiveEqRecycler.visibility = View.GONE
                viewModel.characterTempSave.isUsingClassEquipment = false
            }
        }
        if (viewModel.characterTempSave.isUsingClassEquipment) binding.eqRadioClassAndBg.isChecked = true
        else binding.eqRadioStartingWealth.isChecked = true


        //Recyclerview Adapter
        viewModel.getClassEquipment()
        var fullEquipmentList = MutableLiveData<MutableList<Equipment>>(mutableListOf())
        fullEquipmentList.value?.addAll(viewModel.characterTempSave.backgroundEquipment.value!!)
        fullEquipmentList.value?.addAll(viewModel.characterTempSave.listOfClassEquipment.value!!)
        binding.ccStepFiveEqRecycler.layoutManager = LinearLayoutManager(requireContext())
        val adapter = EquipmentAdapter(fullEquipmentList, requireContext(), false)
        binding.ccStepFiveEqRecycler.adapter = adapter

        //StartingWealth
        var d4s = listOf<Int>(R.drawable.d4_1,R.drawable.d4_2,R.drawable.d4_3,R.drawable.d4_4)
        val startingWealth = viewModel.getClassWealth()
        val diceImages = listOf<ImageView>(binding.d41,binding.d42,binding.d43,binding.d44,binding.d45)
        for (image in diceImages) image.visibility = View.GONE
        for (i in 1..startingWealth.first) diceImages[i-1].visibility = View.VISIBLE
        binding.times10GpText.visibility = if (startingWealth.second) View.VISIBLE else View.GONE
        if (viewModel.characterTempSave.startingWealthRolled.isEmpty()) {
            binding.finalGoldText.visibility = View.GONE
            for (image in diceImages) image.setImageResource(R.drawable.d4_4)
        }
        else {
            for (i in 0 until viewModel.characterTempSave.startingWealthRolled.size-1) {
                diceImages[i].setImageResource(d4s[viewModel.characterTempSave.startingWealthRolled[i]-1])
            }
            binding.finalGoldText.visibility = View.VISIBLE
            binding.finalGoldText.text = "= ${viewModel.characterTempSave.startingWealthRolled.last()}gp"
        }
        //StartingWealth Roll
        binding.goldRollButton.visibility =
            if (viewModel.characterTempSave.startingWealthRolled.isEmpty()) View.VISIBLE
            else View.GONE
        if (viewModel.characterTempSave.startingWealthRolled.isEmpty()) {
            binding.goldRollButton.setOnClickListener {
                binding.goldRollButton.visibility = View.GONE
                viewModel.rollWealth(startingWealth)
                var rolledWealth = viewModel.characterTempSave.startingWealthRolled
                for (image in diceImages) {
                    lifecycleScope.launch {
                        for (i in 1..50) {
                            image.setImageResource(d4s.random())
                            delay(100)
                        }
                        for (i in 0 until rolledWealth.size-1) {
                            diceImages[i].setImageResource(d4s[rolledWealth[i]-1])
                        }
                        binding.finalGoldText.visibility = View.VISIBLE
                        binding.finalGoldText.text = "= ${rolledWealth.last()}gp"
                    }
                }
            }
        }
        //NoClassSelectedWealth
        if (startingWealth.first == 0) {
            binding.noClassSelectedText.visibility = View.VISIBLE
            for (image in diceImages) image.visibility = View.GONE
            binding.times10GpText.visibility = View.GONE
            binding.goldRollButton.visibility = View.GONE
        }
        else binding.noClassSelectedText.visibility = View.GONE


        //Navigation-Buttons
        //Back Button
        binding.ccStepFiveBackButton.setOnClickListener{
            findNavController().navigate(R.id.cc_step_five_back_button)
        }
        //Cancel Button
        binding.ccStepFiveCancelButton.setOnClickListener {
            viewModel.resetCharacterTempSave()
            findNavController().navigate(R.id.cc_step5_cancel)
        }
        //Next Button
        binding.ccStepFiveNextButton.setOnClickListener {
            findNavController().navigate(R.id.cc_step5_next)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}