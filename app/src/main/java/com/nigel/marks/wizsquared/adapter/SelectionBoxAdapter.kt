package com.nigel.marks.wizsquared.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nigel.marks.wizsquared.R
import com.nigel.marks.wizsquared.data.model.CharacterCreationTempSave
import com.nigel.marks.wizsquared.data.model.SelectorBox
import com.nigel.marks.wizsquared.databinding.SelectorItemBinding

class SelectionBoxAdapter(var selectionList:List<SelectorBox>, var context: Context, var saveMap: MutableMap<String,String>)
    : RecyclerView.Adapter<SelectionBoxAdapter.SelectorBoxHolder>(){

        inner class SelectorBoxHolder(private val binding: SelectorItemBinding) : RecyclerView.ViewHolder(binding.root) {

            val spinners = listOf<Spinner>(
                binding.selectorSpinnerOne,binding.selectorSpinnerTwo,
                binding.selectorSpinnerThree,binding.selectorSpinnerFour
            )

            fun bind(selectorBox: SelectorBox) {
                //Set Title of Box
                binding.selectorTitle.text = selectorBox.title

                //Determine number of Choices
                when (selectorBox.numberOfChoices) {
                    1 -> singleSelection(selectorBox)
                    else -> multipleSelection(selectorBox)
                }

                binding.selectorSpinnerDropdownTwo.visibility = spinners[1].visibility
                binding.selectorSpinnerDropdownThree.visibility = spinners[2].visibility
                binding.selectorSpinnerDropdownFour.visibility = spinners[3].visibility
            }

            private fun singleSelection(selectorBox: SelectorBox) {
                //1st Spinner Adapter
                binding.selectorSpinnerOne.adapter = ArrayAdapter<Any?>(
                    context,
                    R.layout.spinner_list,
                    selectorBox.listOfChoicesOne
                )
                //OnItemSelectedListener: Change Shown Objects on Selected Item in Spinner
                binding.selectorSpinnerOne.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parentView: AdapterView<*>?,
                        selectedItemView: View?,
                        position: Int,
                        id: Long
                    ) {
                        saveMap[selectorBox.key] = binding.selectorSpinnerOne.selectedItem.toString()
                    }

                    override fun onNothingSelected(parentView: AdapterView<*>?) {
                        // your code here
                    }
                }
                for (spinner in spinners) spinner.visibility = View.GONE
                spinners[0].visibility = View.VISIBLE
                spinners[0].setSelection(selectorBox.listOfChoicesOne.indexOf(saveMap[selectorBox.key]))
            }
            private fun multipleSelection(selectorBox: SelectorBox) {
                val choiceLists = listOf<List<String>>(
                    selectorBox.listOfChoicesOne,selectorBox.listOfChoicesTwo,
                    selectorBox.listOfChoicesThree,selectorBox.listOfChoicesFour
                )

                for (spinner in spinners) {
                    //Spinner Adapters
                    spinner.adapter = ArrayAdapter<Any?>(
                        context,
                        R.layout.spinner_list,
                        choiceLists[spinners.indexOf(spinner)]
                    )
                    //OnItemSelectedListener
                    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parentView: AdapterView<*>?,
                            selectedItemView: View?,
                            position: Int,
                            id: Long
                        ) {
                            val key = selectorBox.key + ":" + spinners.indexOf(spinner)
                            if (selectorBox.key == "equipment") saveMap[key] = position.toString()
                            else saveMap[key] = spinner.selectedItem.toString()
                        }

                        override fun onNothingSelected(parentView: AdapterView<*>?) {
                            // your code here
                        }
                    }
                }

                for (spinner in spinners) spinner.visibility = View.GONE
                spinners[0].visibility = View.VISIBLE
                if (selectorBox.key != "equipment") {
                    spinners[0].setSelection(choiceLists[0].indexOf(saveMap[selectorBox.key+":0"]))
                }
                else spinners[0].setSelection(saveMap["equipment:0"]!!.toInt())
                if (selectorBox.numberOfChoices >= 2) {
                    spinners[1].visibility = View.VISIBLE
                    if (selectorBox.key != "equipment") {
                        spinners[1].setSelection(choiceLists[1].indexOf(saveMap[selectorBox.key+":1"]))
                    }
                    else spinners[1].setSelection(saveMap["equipment:1"]!!.toInt())
                }
                if (selectorBox.numberOfChoices >= 3) {
                    spinners[2].visibility = View.VISIBLE
                    if (selectorBox.key != "equipment") {
                        spinners[2].setSelection(choiceLists[2].indexOf(saveMap[selectorBox.key+":2"]))
                    }
                    else spinners[2].setSelection(saveMap["equipment:2"]!!.toInt())
                }
                if (selectorBox.numberOfChoices >= 4) {
                    spinners[3].visibility = View.VISIBLE
                    if (selectorBox.key != "equipment") {
                        spinners[3].setSelection(choiceLists[2].indexOf(saveMap[selectorBox.key+":3"]))
                    }
                    else spinners[3].setSelection(saveMap["equipment:3"]!!.toInt())
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