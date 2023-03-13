package com.nigel.marks.wizsquared.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nigel.marks.wizsquared.R
import com.nigel.marks.wizsquared.data.model.SelectorBox
import com.nigel.marks.wizsquared.databinding.SelectorItemBinding

class SelectionBoxAdapter(var selectionList:List<SelectorBox>, var context: Context)
    : RecyclerView.Adapter<SelectionBoxAdapter.SelectorBoxHolder>(){

        inner class SelectorBoxHolder(val binding: SelectorItemBinding) : RecyclerView.ViewHolder(binding.root) {

            fun bind(selectorBox: SelectorBox) {
                binding.selectorTitle.text = selectorBox.title

                //Set Spinners to previously selected Items
                binding.selectorSpinnerOne.setSelection(selectorBox.selectionSave.selectionOne)
                binding.selectorSpinnerTwo.setSelection(selectorBox.selectionSave.selectionTwo)
                binding.selectorSpinnerThree.setSelection(selectorBox.selectionSave.selectionThree)
                binding.selectorSpinnerFour.setSelection(selectorBox.selectionSave.selectionFour)

                //Save selected Items on Spinners
                binding.selectorSpinnerOne.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        selectorBox.selectionSave.selectionOne = position
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }

                }
                binding.selectorSpinnerTwo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        selectorBox.selectionSave.selectionTwo = position
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }

                }
                binding.selectorSpinnerThree.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        selectorBox.selectionSave.selectionThree = position
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }

                }
                binding.selectorSpinnerFour.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        selectorBox.selectionSave.selectionFour = position
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }

                }

                //Set ChoiceLists 2-4 to ChoiceList 1 if choicesAreSame == true
                if (selectorBox.choicesAreSame) {
                    when (selectorBox.numberOfChoices) {
                        2 -> selectorBox.choicesTwo = selectorBox.choicesOne
                        3 -> selectorBox.choicesThree = selectorBox.choicesOne
                        4 -> selectorBox.choicesFour = selectorBox.choicesOne
                    }
                }

                //If this SelectorBox has a Choice:
                if (selectorBox.hasChoice) {
                    binding.selectorSpinnerOne.visibility = View.VISIBLE
                    binding.selectorSpinnerDropdownOne.visibility = View.VISIBLE

                    binding.selectorSpinnerOne.adapter = ArrayAdapter<Any?>(
                        context,
                        R.layout.spinner_list,
                        selectorBox.choicesOne
                    )
                    //If this SelectorBox has 2 or more Choices
                    if (selectorBox.numberOfChoices >= 2) {
                        binding.selectorSpinnerTwo.visibility = View.VISIBLE
                        binding.selectorSpinnerDropdownTwo.visibility = View.VISIBLE

                        binding.selectorSpinnerTwo.adapter = ArrayAdapter<Any?>(
                            context,
                            R.layout.spinner_list,
                            selectorBox.choicesTwo
                        )
                        //If this SelectorBox has 3 or more Choices
                        if (selectorBox.numberOfChoices >= 3) {
                            binding.selectorSpinnerThree.visibility = View.VISIBLE
                            binding.selectorSpinnerDropdownThree.visibility = View.VISIBLE

                            binding.selectorSpinnerThree.adapter = ArrayAdapter<Any?>(
                                context,
                                R.layout.spinner_list,
                                selectorBox.choicesThree
                            )
                            // If this SelectorBox has 4 Choices
                            if (selectorBox.numberOfChoices == 4) {
                                binding.selectorSpinnerFour.visibility = View.VISIBLE
                                binding.selectorSpinnerDropdownFour.visibility = View.VISIBLE

                                binding.selectorSpinnerFour.adapter = ArrayAdapter<Any?>(
                                    context,
                                    R.layout.spinner_list,
                                    selectorBox.choicesFour
                                )
                            }
                            //Set View.GONE for Spinner 4 (3 choices)
                            else {
                                binding.selectorSpinnerFour.visibility = View.GONE
                                binding.selectorSpinnerDropdownFour.visibility = View.GONE
                            }
                        }
                        //Set View.GONE for Spinner 3-4 (2 choices)
                        else {
                            binding.selectorSpinnerThree.visibility = View.GONE
                            binding.selectorSpinnerDropdownThree.visibility = View.GONE
                            binding.selectorSpinnerFour.visibility = View.GONE
                            binding.selectorSpinnerDropdownFour.visibility = View.GONE
                        }
                    }
                    //Set View.GONE for Spinner 2-4 (1 choice)
                    else {
                        binding.selectorSpinnerTwo.visibility = View.GONE
                        binding.selectorSpinnerDropdownTwo.visibility = View.GONE
                        binding.selectorSpinnerThree.visibility = View.GONE
                        binding.selectorSpinnerDropdownThree.visibility = View.GONE
                        binding.selectorSpinnerFour.visibility = View.GONE
                        binding.selectorSpinnerDropdownFour.visibility = View.GONE
                    }
                }
                //Set View.GONE for all Spinners (no choices)
                else {
                    binding.selectorSpinnerOne.visibility = View.GONE
                    binding.selectorSpinnerDropdownOne.visibility = View.GONE
                    binding.selectorSpinnerTwo.visibility = View.GONE
                    binding.selectorSpinnerDropdownTwo.visibility = View.GONE
                    binding.selectorSpinnerThree.visibility = View.GONE
                    binding.selectorSpinnerDropdownThree.visibility = View.GONE
                    binding.selectorSpinnerFour.visibility = View.GONE
                    binding.selectorSpinnerDropdownFour.visibility = View.GONE
                }

                //Set Description to selected Item
                if (selectorBox.hasDesc) {
                    if (selectorBox.hasMultipleDesc) {
                        binding.selectorSpinnerOne.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                            override fun onItemSelected(
                                parentView: AdapterView<*>?,
                                selectedItemView: View?,
                                position: Int,
                                id: Long
                            ) {
                                binding.selectorDesc.text =
                                    selectorBox.descriptions[selectorBox.choicesOne.indexOf(
                                        binding.selectorSpinnerOne.selectedItem.toString())]
                            }

                            override fun onNothingSelected(parentView: AdapterView<*>?) {

                            }
                        }
                    }
                    else binding.selectorDesc.text = selectorBox.descriptions[0]
                }

                //If SelectorBox has Description set View.VISIBLE or View.GONE if it doesn't
                if (selectorBox.hasDesc) binding.selectorDesc.visibility = View.VISIBLE
                else binding.selectorDesc.visibility = View.GONE

                //Logic for multiple Spinners with multiple choices of the same List

                if (selectorBox.choicesAreSame) {
                    //Indexes of current selectedItem on all Spinners
                    var spinnerOne = 0
                    var spinnerTwo = 0
                    var spinnerThree = 0
                    var spinnerFour = 0
                    //Spinner 1
                    binding.selectorSpinnerOne.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            if (binding.selectorSpinnerOne.selectedItem.toString() != "Choose") {
                                if (binding.selectorSpinnerOne.selectedItem == binding.selectorSpinnerTwo.selectedItem) {
                                    binding.selectorSpinnerTwo.setSelection(spinnerOne)
                                    spinnerTwo = spinnerOne
                                }
                                if (binding.selectorSpinnerOne.selectedItem == binding.selectorSpinnerThree.selectedItem) {
                                    binding.selectorSpinnerThree.setSelection(spinnerOne)
                                    spinnerThree = spinnerOne
                                }
                                if (binding.selectorSpinnerOne.selectedItem == binding.selectorSpinnerFour.selectedItem) {
                                    binding.selectorSpinnerFour.setSelection(spinnerOne)
                                    spinnerFour = spinnerOne
                                }
                            }
                            spinnerOne = position
                            selectorBox.selectionSave.selectionOne = position
                        }
                        override fun onNothingSelected(parent: AdapterView<*>?) {

                        }
                    }
                    //Spinner 2
                    binding.selectorSpinnerTwo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            if (binding.selectorSpinnerTwo.selectedItem.toString() != "Choose") {
                                if (binding.selectorSpinnerTwo.selectedItem == binding.selectorSpinnerOne.selectedItem) {
                                    binding.selectorSpinnerOne.setSelection(spinnerTwo)
                                    spinnerOne = spinnerTwo
                                }
                                if (binding.selectorSpinnerTwo.selectedItem == binding.selectorSpinnerThree.selectedItem) {
                                    binding.selectorSpinnerThree.setSelection(spinnerTwo)
                                    spinnerThree = spinnerTwo
                                }
                                if (binding.selectorSpinnerTwo.selectedItem == binding.selectorSpinnerFour.selectedItem) {
                                    binding.selectorSpinnerFour.setSelection(spinnerTwo)
                                    spinnerFour = spinnerTwo
                                }
                            }
                            spinnerTwo = position
                        }
                        override fun onNothingSelected(parent: AdapterView<*>?) {

                        }
                    }
                    //Spinner 3
                    binding.selectorSpinnerThree.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            if (binding.selectorSpinnerThree.selectedItem.toString() != "Choose") {
                                if (binding.selectorSpinnerThree.selectedItem == binding.selectorSpinnerOne.selectedItem) {
                                    binding.selectorSpinnerOne.setSelection(spinnerThree)
                                    spinnerOne = spinnerThree
                                }
                                if (binding.selectorSpinnerThree.selectedItem == binding.selectorSpinnerTwo.selectedItem) {
                                    binding.selectorSpinnerTwo.setSelection(spinnerThree)
                                    spinnerTwo = spinnerThree
                                }
                                if (binding.selectorSpinnerThree.selectedItem == binding.selectorSpinnerFour.selectedItem) {
                                    binding.selectorSpinnerFour.setSelection(spinnerThree)
                                    spinnerFour = spinnerThree
                                }
                            }
                            spinnerThree = position
                        }
                        override fun onNothingSelected(parent: AdapterView<*>?) {

                        }
                    }
                    //Spinner 4
                    binding.selectorSpinnerFour.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            if (binding.selectorSpinnerFour.selectedItem.toString() != "Choose") {
                                if (binding.selectorSpinnerFour.selectedItem == binding.selectorSpinnerTwo.selectedItem) {
                                    binding.selectorSpinnerTwo.setSelection(spinnerOne)
                                    spinnerOne = spinnerFour
                                }
                                if (binding.selectorSpinnerFour.selectedItem == binding.selectorSpinnerThree.selectedItem) {
                                    binding.selectorSpinnerThree.setSelection(spinnerOne)
                                    spinnerTwo = spinnerFour
                                }
                                if (binding.selectorSpinnerFour.selectedItem == binding.selectorSpinnerOne.selectedItem) {
                                    binding.selectorSpinnerOne.setSelection(spinnerOne)
                                    spinnerThree = spinnerFour
                                }
                            }
                            spinnerOne = position
                        }
                        override fun onNothingSelected(parent: AdapterView<*>?) {

                        }
                    }
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectorBoxHolder {
        val binding = SelectorItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SelectorBoxHolder(binding)
    }

    override fun getItemCount(): Int {
        return selectionList.size
    }

    override fun onBindViewHolder(holder: SelectorBoxHolder, position: Int) {
        holder.bind(selectionList[position])
        holder.setIsRecyclable(false)
    }
}