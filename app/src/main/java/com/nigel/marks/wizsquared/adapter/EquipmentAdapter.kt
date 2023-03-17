package com.nigel.marks.wizsquared.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.nigel.marks.wizsquared.data.model.Equipment
import com.nigel.marks.wizsquared.databinding.EquipmentItemBinding

class EquipmentAdapter(var equipmentList:MutableLiveData<MutableList<Equipment>>, var context: Context)
    : RecyclerView.Adapter<EquipmentAdapter.EquipmentHolder>(){

    inner class EquipmentHolder(private val binding: EquipmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(equipment: Equipment) {
            binding.equipmentAmount.text = String.format("%02d", equipment.amount)
            binding.equipmentText.text = equipment.name

            binding.equipmentDeleteButton.setOnClickListener {
                equipmentList.value?.remove(equipment)
                equipmentList.value = equipmentList.value
            }
        }
    }

    fun resubmitList() {
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquipmentHolder {
        val binding = EquipmentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EquipmentHolder(binding)
    }

    override fun getItemCount(): Int {
        return equipmentList.value!!.size
    }

    override fun onBindViewHolder(holder: EquipmentHolder, position: Int) {
        holder.bind(equipmentList.value!![position])
    }
}