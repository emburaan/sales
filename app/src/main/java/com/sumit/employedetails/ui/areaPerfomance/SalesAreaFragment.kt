package com.sumit.employedetails.ui.areaPerfomance

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.sumit.employedetails.R
import com.sumit.employedetails.databinding.FragmentAreaBinding
import com.sumit.employedetails.other.Status
import com.sumit.employedetails.ui.MainViewModel
import com.sumit.employedetails.ui.countryPerfomance.SalesZoneAdapter
import com.sumit.employedetails.utils.hiltNavGraphViewModels
import com.sumit.employedetails.utils.viewBinding

class SalesAreaFragment : Fragment(R.layout.fragment_area),
    SalesZoneAdapter.OnClickListener {
    private val mainViewModel by hiltNavGraphViewModels<MainViewModel>(R.id.nav_graph)
    private val args: SalesAreaFragmentArgs by navArgs()
    private val binding by viewBinding(FragmentAreaBinding::bind)
    private lateinit var adapter: SalesAreaAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        setupObserver()
    }

    private fun setupObserver() {
        mainViewModel.res.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progress.visibility = View.GONE
                    binding.rvEmployees.visibility = View.VISIBLE
                    binding.tvCountry.visibility = View.VISIBLE
                    binding.tvCountry.text = resources.getString(
                        R.string.country_performance, args.performance.substringBefore(':')
                    )
                    it.data.let { res ->
                        res?.let { sales -> adapter.submitList(sales.saleArea) }
                    }
                }
                Status.LOADING -> {
                    binding.progress.visibility = View.VISIBLE
                    binding.rvEmployees.visibility = View.GONE
                }
                Status.ERROR -> {
                    binding.progress.visibility = View.GONE
                    binding.rvEmployees.visibility = View.VISIBLE
                    Snackbar.make(binding.rootView, "Something went wrong", Snackbar.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun setupUi() {
        adapter = SalesAreaAdapter(requireContext())
        binding.rvEmployees.layoutManager = LinearLayoutManager(requireContext())
        binding.rvEmployees.adapter = adapter
    }

    override fun onClick(performance: String) {

    }

}