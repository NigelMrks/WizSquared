package com.nigel.marks.wizsquared.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.nigel.marks.wizsquared.R
import com.nigel.marks.wizsquared.data.entities.PlayerCharacter
import com.nigel.marks.wizsquared.data.entities.Spell
import com.nigel.marks.wizsquared.data.model.Equipment
import com.nigel.marks.wizsquared.databinding.CharacterListItemBinding
import com.nigel.marks.wizsquared.databinding.EquipmentItemBinding

class CharacterAdapter(private var dataset: MutableList<PlayerCharacter>)
    : RecyclerView.Adapter<CharacterAdapter.CharacterHolder>(){

    inner class CharacterHolder(private val binding: CharacterListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(character: PlayerCharacter) {

            if (character.characterName != "") {
                binding.characterName.text = character.characterName
            }
            else binding.characterName.text = "(No Name)"

            if (character.selClass != "") {
                if (character.subclass != "") {
                    binding.characterClassAndLevel.text = character.subclass + " " + character.selClass
                }
                else binding.characterClassAndLevel.text = character.selClass
            }
            else binding.characterClassAndLevel.text = "(No Class)"

            var race = "(No Race)"
            if (character.race != "") {
                race = if (character.subrace != "") {
                    character.subrace + " " + character.race
                } else character.race
            }

            var background = ""
            if (character.background != "") {
                background = character.background
            }

            binding.characterRaceAndBackground.text = "$race $background"

            binding.pcCard.setOnClickListener {
                itemView.findNavController().navigate(R.id.home_to_detail_view)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterHolder {
        val binding = CharacterListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
        holder.bind(dataset[position])
    }
}