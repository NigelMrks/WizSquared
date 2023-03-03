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

                if (selectorBox.hasChoice) {
                    binding.selectorSpinner.visibility = View.VISIBLE
                    val selectorArrayAdapter = ArrayAdapter<Any?>(
                        context,
                        R.layout.spinner_list,
                        selectorBox.choices
                    )
                    binding.selectorSpinner.adapter = selectorArrayAdapter
                }
                else {
                    binding.selectorSpinner.visibility = View.GONE
                }

                if (selectorBox.hasMultipleDesc) {
                    binding.selectorDesc.text =
                        selectorBox.descriptions[selectorBox.choices.indexOf(
                            binding.selectorSpinner.selectedItem.toString())]
                }
                else binding.selectorDesc.text = selectorBox.descriptions[0]
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
    }
}