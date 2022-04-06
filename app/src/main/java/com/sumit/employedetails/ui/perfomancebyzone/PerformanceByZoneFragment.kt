package com.sumit.employedetails.ui.perfomancebyzone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.sumit.employedetails.R
import com.sumit.employedetails.databinding.FragmentPerfomanceByZoneBinding
import com.sumit.employedetails.other.Status
import com.sumit.employedetails.ui.MainViewModel
import com.sumit.employedetails.utils.hiltNavGraphViewModels
import com.sumit.employedetails.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PerformanceByZoneFragment : Fragment(R.layout.fragment_perfomance_by_zone),
    SalesregionAdapter.OnClickListener {
    private val mainViewModel by hiltNavGraphViewModels<MainViewModel>(R.id.nav_graph)
    private val binding by viewBinding(FragmentPerfomanceByZoneBinding::bind)
    private lateinit var adapter: SalesregionAdapter

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
                        R.string.country_performance, it.data?.salesCountry?.get(0)?.country
                    )
                    it.data.let { res ->
                        res?.let { sales -> adapter.submitList(sales.salesRegion) }
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
        adapter = SalesregionAdapter(requireContext(), this)
        binding.rvEmployees.layoutManager = LinearLayoutManager(requireContext())
        binding.rvEmployees.adapter = adapter
    }

    override fun onClick(performance: String) {
        val action = PerformanceByZoneFragmentDirections.actionPerformanceByZoneFragmentToCountryPerRomanceFragment(performance)
        findNavController().navigate(action)
    }
}