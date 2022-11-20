package com.udacity.shoestore.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoeViewModel : ViewModel() {

    private val _shoesList = MutableLiveData<MutableList<Shoe>>()
    val shoesList: LiveData<MutableList<Shoe>>
        get() = _shoesList

    init {
        _shoesList.value = mutableListOf()
    }
    fun addNewShoe(
        inputName: String,
        inputSize: Double,
        inputCompany: String,
        inputDescription: String
    ) {
        val newShoe = Shoe(
            name = inputName,
            size = inputSize,
            company = inputCompany,
            description = inputDescription
        )
        _shoesList.value?.add(newShoe)
    }


}