package com.nigel.marks.wizsquared.ui

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nigel.marks.wizsquared.MainViewModel
import com.nigel.marks.wizsquared.R
import com.nigel.marks.wizsquared.adapter.EquipmentAdapter
import com.nigel.marks.wizsquared.data.model.Equipment
import com.nigel.marks.wizsquared.databinding.FragmentCharacterCreationStepFourBinding


class CharacterCreationStepFour : Fragment() {

    private var _binding: FragmentCharacterCreationStepFourBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterCreationStepFourBinding.inflate(inflater, container, false)
        val view = binding.root

        //Set Number of Steps
        binding.ccStepFourStepCount.text = resources.getStringArray(R.array.cc_steps)[3]

        //Code for Spinner
        //Create Adapter for Spinner with text layout and List to use
        val spinners = listOf<Spinner>(
            binding.langOneSpinner,
            binding.langTwoSpinner,
            binding.skillOneSpinner,
            binding.skillTwoSpinner
        )

        for (spinner in spinners) {
            val spinnerEntries: MutableList<String> = if (spinners.indexOf(spinner) in 0..1) {
                resources.getStringArray(R.array.languages).toMutableList()
            } else resources.getStringArray(R.array.skill_choices_bard).toMutableList()
            spinnerEntries.add(0, "None")
            spinner.adapter = ArrayAdapter<Any?>(
                requireContext(),
                R.layout.spinner_list,
                spinnerEntries as List<Any?>
            )
        }
        //setOnItemSelectedListeners for all Spinners
        binding.skillOneSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (binding.skillOneSpinner.selectedItem == binding.skillTwoSpinner.selectedItem && position != 0) {
                    binding.skillTwoSpinner.setSelection(viewModel.characterTempSave.bgSkillOne)
                }
                viewModel.characterTempSave.bgSkillOne = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        binding.skillTwoSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (binding.skillOneSpinner.selectedItem == binding.skillTwoSpinner.selectedItem && position != 0) {
                    binding.skillOneSpinner.setSelection(viewModel.characterTempSave.bgSkillTwo)
                }
                viewModel.characterTempSave.bgSkillTwo = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        binding.langOneSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (binding.langOneSpinner.selectedItem == binding.langTwoSpinner.selectedItem && position != 0) {
                    binding.langTwoSpinner.setSelection(viewModel.characterTempSave.bgLangOne)
                }
                viewModel.characterTempSave.bgLangOne = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        binding.langTwoSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (binding.langOneSpinner.selectedItem == binding.langTwoSpinner.selectedItem && position != 0) {
                    binding.langOneSpinner.setSelection(viewModel.characterTempSave.bgLangTwo)
                }
                viewModel.characterTempSave.bgLangTwo = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        //OnClickListener Add Equipment Button
        binding.equipmentAddButton.setOnClickListener {
            if(binding.equipmentAddEdit.text != null && binding.equipmentAddEdit.text.toString() != "") {
                var eqAmount = if (binding.equipmentAmountEdit.text != null) {
                    try {
                        binding.equipmentAmountEdit.text.toString().toInt()
                    }
                    catch (e: Exception) {1}
                } else 1

                val eqName = binding.equipmentAddEdit.text.toString()

                addEquipment(eqAmount,eqName)
            }
        }

        //Recyclerview Adapter
        binding.ccStepThreeEqRecycler.layoutManager = LinearLayoutManager(requireContext())
        val adapter = EquipmentAdapter(viewModel.characterTempSave.backgroundEquipment, requireContext(), true)
        binding.ccStepThreeEqRecycler.adapter = adapter

        viewModel.characterTempSave.backgroundEquipment.observe(viewLifecycleOwner) {
            adapter.resubmitList()
        }

        //Set SpinnerSelections
        spinners[0].setSelection(viewModel.characterTempSave.bgLangOne)
        spinners[1].setSelection(viewModel.characterTempSave.bgLangTwo)
        spinners[2].setSelection(viewModel.characterTempSave.bgSkillOne)
        spinners[3].setSelection(viewModel.characterTempSave.bgSkillTwo)

        //Set Background Name File
        binding.backgroundNameEdit.setText(viewModel.characterTempSave.backgroundName)

        //Navigation-Buttons
        //Back Button
        binding.ccStepFourBackButton.setOnClickListener{
            if (binding.backgroundNameEdit.text != null) {
                viewModel.characterTempSave.backgroundName = binding.backgroundNameEdit.text.toString()
            }
            findNavController().navigate(CharacterCreationStepFourDirections.actionCharacterCreationStepFourToCharacterCreationStepThree())
        }
        //Cancel Button
        binding.ccStepFourCancelButton.setOnClickListener {
            viewModel.resetCharacterTempSave()
            findNavController().navigate(CharacterCreationStepFourDirections.actionCharacterCreationStepFourToCharacterList())
        }
        //Next Button
        binding.ccStepFourNextButton.setOnClickListener {
            if (binding.backgroundNameEdit.text != null) {
                viewModel.characterTempSave.backgroundName = binding.backgroundNameEdit.text.toString()
            }
            findNavController().navigate(CharacterCreationStepFourDirections.actionCharacterCreationStepFourToCharacterCreationStepFive())
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        //Observer for if keyboard is opened or closed

        // if there is any problems with this code make an OnClickListener
        //on the Edit Texts

        binding.rootView.viewTreeObserver.addOnGlobalLayoutListener {
            val r = Rect();
            try {
                binding.rootView.getWindowVisibleDisplayFrame(r);
                val screenHeight = binding.rootView.rootView.height;

                val keypadHeight = screenHeight - r.bottom;

                if (keypadHeight > screenHeight * 0.15) {
                    // keyboard is opened
                    binding.ccStepFourBackButton.visibility = View.GONE
                    binding.ccStepFourCancelButton.visibility = View.GONE
                    binding.ccStepFourNextButton.visibility = View.GONE
                    binding.ccStepFourStepCount.visibility = View.GONE
                    binding.ccStepFourTitleText.visibility = View.GONE
                }
                else {
                    // keyboard is closed
                    binding.ccStepFourBackButton.visibility = View.VISIBLE
                    binding.ccStepFourCancelButton.visibility = View.VISIBLE
                    binding.ccStepFourNextButton.visibility = View.VISIBLE
                    binding.ccStepFourStepCount.visibility = View.VISIBLE
                    binding.ccStepFourTitleText.visibility = View.VISIBLE
                }
            }
            catch (_: Exception) {}
        }
    }

    private fun addEquipment(eqAmount:Int, eqName:String) {
        var amount=eqAmount

        if (amount > 99) {
            addEquipment((amount-99),eqName)
            amount = 99
        }

        viewModel.characterTempSave.backgroundEquipment.value?.add(
            Equipment(eqName, amount)
        )
        viewModel.characterTempSave.backgroundEquipment.value =
            viewModel.characterTempSave.backgroundEquipment.value
    }


    //to prevent memory leakage
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}