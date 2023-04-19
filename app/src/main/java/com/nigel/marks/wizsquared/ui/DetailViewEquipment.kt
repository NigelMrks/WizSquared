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
import com.nigel.marks.wizsquared.data.model.Equipment
import com.nigel.marks.wizsquared.databinding.FragmentDetailViewEquipmentBinding

class DetailViewEquipment : Fragment() {

    private var _binding: FragmentDetailViewEquipmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailViewEquipmentBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.dvEqRecycler.layoutManager = LinearLayoutManager(requireContext())
        val equipmentList: MutableLiveData<MutableList<Equipment>> = MutableLiveData(viewModel.viewedPlayerCharacter.equipment)
        val adapter = EquipmentAdapter(equipmentList,requireContext(),false)
        binding.dvEqRecycler.adapter = adapter

        binding.dvBack1.setOnClickListener {
            findNavController().navigate(R.id.dv2dv1)
        }
        binding.dvForward2.setOnClickListener {
            findNavController().navigate(R.id.dv2dv3)
        }

        return view
    }

    //to prevent memory leakage
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}