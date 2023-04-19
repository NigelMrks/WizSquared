package com.nigel.marks.wizsquared.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nigel.marks.wizsquared.MainViewModel
import com.nigel.marks.wizsquared.R
import com.nigel.marks.wizsquared.adapter.EquipmentAdapter
import com.nigel.marks.wizsquared.adapter.FeatureAdapter
import com.nigel.marks.wizsquared.adapter.SkillProfAdapter
import com.nigel.marks.wizsquared.data.model.Equipment
import com.nigel.marks.wizsquared.databinding.FragmentDetailViewEquipmentBinding
import com.nigel.marks.wizsquared.databinding.FragmentDetailViewFeaturesBinding

class DetailViewFeatures : Fragment() {

    private var _binding: FragmentDetailViewFeaturesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailViewFeaturesBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.dvFeatureRecycler.layoutManager = LinearLayoutManager(requireContext())
        val adapter = FeatureAdapter(viewModel.viewedPlayerCharacter.featureList)
        binding.dvFeatureRecycler.adapter = adapter

        binding.dvBack0.setOnClickListener {
            findNavController().navigate(R.id.dv1dv0)
        }
        binding.dvForward1.setOnClickListener {
            findNavController().navigate(R.id.dv1dv2)
        }

        return view
    }

    //to prevent memory leakage
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}