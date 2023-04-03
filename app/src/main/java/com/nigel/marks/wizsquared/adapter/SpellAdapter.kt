package com.nigel.marks.wizsquared.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.nigel.marks.wizsquared.data.entities.Spell
import com.nigel.marks.wizsquared.data.model.Equipment
import com.nigel.marks.wizsquared.databinding.EquipmentItemBinding
import com.nigel.marks.wizsquared.databinding.SpellItemBinding
import java.util.*

class SpellAdapter(var spellList: LiveData<List<Spell>>, var context: Context)
    : RecyclerView.Adapter<SpellAdapter.SpellHolder>(){

    inner class SpellHolder(private val binding: SpellItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(spell: Spell) {
            binding.spellNameText.text = spell.name
            binding.spellSchoolText.text = spell.school.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            }
            binding.spellLevelText.text = spell.level_int.toString()
        }
    }

    fun resubmitList() {
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpellHolder {
        val binding = SpellItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SpellHolder(binding)
    }

    override fun getItemCount(): Int {
        return spellList.value!!.size
    }

    override fun onBindViewHolder(holder: SpellHolder, position: Int) {
        holder.bind(spellList.value!![position])
    }
}