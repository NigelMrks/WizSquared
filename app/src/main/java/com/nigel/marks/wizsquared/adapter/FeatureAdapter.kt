package com.nigel.marks.wizsquared.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.nigel.marks.wizsquared.R
import com.nigel.marks.wizsquared.data.model.Feature
import com.nigel.marks.wizsquared.databinding.FeatureItemBinding
import com.nigel.marks.wizsquared.databinding.SkillItemBinding

class FeatureAdapter(private var features: MutableList<Feature>)
    : RecyclerView.Adapter<FeatureAdapter.FeatureHolder>(){

    inner class FeatureHolder(private val binding: FeatureItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(feature: Feature) {

            binding.dvFeatureName.text = feature.name
            binding.dvFeatureSource.text = feature.source
            binding.dvFeatureDesc.text = feature.desc

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeatureHolder {
        val binding = FeatureItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FeatureHolder(binding)
    }

    override fun getItemCount(): Int {
        return features.size
    }

    override fun onBindViewHolder(holder: FeatureHolder, position: Int) {
        holder.bind(features[position])
    }
}