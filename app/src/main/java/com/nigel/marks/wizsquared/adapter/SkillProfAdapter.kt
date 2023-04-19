package com.nigel.marks.wizsquared.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.nigel.marks.wizsquared.R
import com.nigel.marks.wizsquared.databinding.SkillItemBinding

class SkillProfAdapter(private var skillProfs: List<String>, var proficiencies: List<Boolean>, var context: Context)
    : RecyclerView.Adapter<SkillProfAdapter.SkillHolder>(){

    inner class SkillHolder(private val binding: SkillItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(skillProf: String, index: Int) {
            var skillMod = when (skillProf) {
                "Athletics" -> "Strength"
                in "Acrobatics","Sleight of Hand","Stealth" -> "Dexterity"
                in "Arcana","Investigation","History","Nature","Religion" -> "Intelligence"
                in "Animal Handling","Insight","Medicine","Perception","Survival" -> "Wisdom"
                else -> "Charisma"
            }
            binding.dvSkillName.text = "$skillProf ($skillMod)"

            if (proficiencies[index]) {
                binding.dvSkillName.setTextColor(ContextCompat.getColor(context, R.color.primary_inverted))
            }
            else binding.dvSkillName.setTextColor(ContextCompat.getColor(context, R.color.primary_light))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillHolder {
        val binding = SkillItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SkillHolder(binding)
    }

    override fun getItemCount(): Int {
        return skillProfs.size
    }

    override fun onBindViewHolder(holder: SkillHolder, position: Int) {
        holder.bind(skillProfs[position],position)
    }
}