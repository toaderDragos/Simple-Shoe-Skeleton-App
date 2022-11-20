package com.udacity.shoestore.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.ShoeDetailFragmentBinding
import com.udacity.shoestore.models.ShoeViewModel


class ShoeDetailFragment : Fragment(){

    private val viewModel: ShoeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: ShoeDetailFragmentBinding = DataBindingUtil.inflate(
            inflater,
            com.udacity.shoestore.R.layout.shoe_detail_fragment,
            container,
            false
        )

        binding.buttonSave.setOnClickListener {
            val inputName = binding.editTxtShoeName.text.toString()
            val inputSize = binding.editTextShoeSize.text.toString().toDouble()
            val inputCompany = binding.editTxtCompany.text.toString()
            val inputDescription = binding.editTextTextMultiLine.text.toString()
            viewModel.addNewShoe(inputName, inputSize, inputCompany, inputDescription)
            view?.findNavController()?.navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
        }

        binding.buttonCancel.setOnClickListener {
            view?.findNavController()?.navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
        }

        return binding.root
    }




}