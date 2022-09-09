package com.example.week2assessment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.week2assessment.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {
    private val model: SharedViewModel by activityViewModels()
    private val binding by lazy {
        FragmentDetailsBinding.inflate(layoutInflater)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.DetailsTitle.text = model.importantTitle
        binding.DetailsCategory.text = model.importantCategory
        binding.DetailsDescription.text = model.importantDescription
        binding.DetailsDate.text = model.importantDate
        binding.detailsBackBtn.setOnClickListener {
            findNavController().navigate(R.id.action_DetailsFragment_to_MainFragment)
        }
        binding.detailsEditBtn.setOnClickListener {
            findNavController().navigate(R.id.action_DetailsFragment_to_ModifyEventFragment)
        }
        return binding.root
    }

    companion object {

    }
}