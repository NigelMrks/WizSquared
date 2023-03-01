package com.nigel.marks.wizsquared.ui.ccs1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.R
import androidx.navigation.fragment.findNavController
import com.nigel.marks.wizsquared.MainViewModel
import com.nigel.marks.wizsquared.databinding.FragmentCcs1StartBinding
import com.nigel.marks.wizsquared.databinding.FragmentCharacterListBinding

class Ccs1Start : Fragment() {

    private var _binding: FragmentCcs1StartBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCcs1StartBinding.inflate(inflater, container, false)
        val view = binding.root

        //code goes here

        return view
    }

    //to prevent memory leakage
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}