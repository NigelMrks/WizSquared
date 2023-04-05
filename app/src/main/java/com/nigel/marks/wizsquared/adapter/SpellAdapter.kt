package com.nigel.marks.wizsquared.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.nigel.marks.wizsquared.R
import com.nigel.marks.wizsquared.data.entities.Spell
import com.nigel.marks.wizsquared.data.model.CharacterCreationTempSave
import com.nigel.marks.wizsquared.databinding.SpellItemBinding
import java.util.*


class SpellAdapter(var spellList: LiveData<List<Spell>>, var context: Context, var tempSave: CharacterCreationTempSave)
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

            for (savedSpell in tempSave.cantrips.value!!) {
                if (savedSpell.name == spell.name) spell.isSelected = true
            }
            for (savedSpell in tempSave.spells.value!!) {
                if (savedSpell.name == spell.name) spell.isSelected = true
            }
            if (spell.isSelected) {
                    binding.spellRecyclerCard.strokeColor = ContextCompat.getColor(context,R.color.primary_inverted)
            }
            else binding.spellRecyclerCard.strokeColor = ContextCompat.getColor(context,R.color.primary_light)

            binding.spellRecyclerCard.setOnClickListener {
                if (spell.isSelected) {
                    spell.isSelected = false
                    binding.spellRecyclerCard.strokeColor = ContextCompat.getColor(context,R.color.primary_light)
                    when (spell.level_int) {
                        0 -> {
                            tempSave.cantrips.value?.remove(spell)
                            tempSave.cantrips.value = tempSave.cantrips.value
                        }
                        1 -> {
                            tempSave.spells.value?.remove(spell)
                            tempSave.spells.value = tempSave.spells.value
                        }
                    }
                }
                else {
                    when (spell.level_int) {
                        0 -> {
                            if (tempSave.cantrips.value?.size!! < 3) {
                                spell.isSelected = true
                                binding.spellRecyclerCard.strokeColor =
                                    ContextCompat.getColor(context,R.color.primary_inverted)
                                tempSave.cantrips.value?.add(spell)
                                tempSave.cantrips.value = tempSave.cantrips.value
                            }
                            else tooManySpellsToast()
                        }
                        1 -> {
                            if (tempSave.spells.value?.size!! < 3) {
                                spell.isSelected = true
                                binding.spellRecyclerCard.strokeColor =
                                    ContextCompat.getColor(context,R.color.primary_inverted)
                                tempSave.spells.value?.add(spell)
                                tempSave.spells.value = tempSave.spells.value
                            }
                            else tooManySpellsToast()
                        }
                    }

                }
            }
        }
        private fun tooManySpellsToast() {
            Toast.makeText(
                context, "Too many spells of that level selected.",
                Toast.LENGTH_SHORT
            ).show()
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