package com.udacity.shoestore.fragments

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeListFragmentBinding
import com.udacity.shoestore.models.ShoeViewModel


class ShoeListFragment : Fragment() {

    private val viewModel: ShoeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: ShoeListFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.shoe_list_fragment,
            container,
            false
        )

        binding.floatingActionButton.setOnClickListener {
            view?.findNavController()
                ?.navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        }
        // checking if we have a list and then if the list has over 1 elements
        val shoeListSize: Int? = viewModel.shoesList.value?.size
        viewModel.shoesList.observe(viewLifecycleOwner, Observer {
            if (shoeListSize != null && shoeListSize > 0) {

                for (shoe in viewModel.shoesList.value!!) {
                    // a new layout
                    val myLayout: LinearLayout = LinearLayout(activity)
                    myLayout.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.MATCH_PARENT
                    )

                    // a new textview for the layout
                    val myTextView: TextView = TextView(activity)
                    myTextView.text = shoe.name
                    binding.nameInLayout.text = myTextView.text

                    myLayout.addView(myTextView)
                }
            }
        })

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_only_for_list_fragment, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // without this tweak it doesn't work
        findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment())
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }


}