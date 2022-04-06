package com.sumit.employedetails.ui.countryPerfomance

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.sumit.employedetails.R
import com.sumit.employedetails.databinding.FragmentCountryPerFomanceBinding
import com.sumit.employedetails.other.Status
import com.sumit.employedetails.ui.perfomancebyzone.SalesregionAdapter
import com.sumit.employedetails.ui.MainViewModel
import com.sumit.employedetails.ui.perfomancebyzone.PerformanceByZoneFragmentDirections
import com.sumit.employedetails.utils.hiltNavGraphViewModels
import com.sumit.employedetails.utils.viewBinding

class CountryPerRomanceFragment : Fragment(R.layout.fragment_country_per_fomance),
    SalesZoneAdapter.OnClickListener {
    private val mainViewModel by hiltNavGraphViewModels<MainViewModel>(R.id.nav_graph)
    private val args: CountryPerRomanceFragmentArgs by navArgs()
    private val binding by viewBinding(FragmentCountryPerFomanceBinding::bind)
    private lateinit var adapter: SalesZoneAdapter

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
                        res?.let { sales -> adapter.submitList(sales.salesZone) }
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
        adapter = SalesZoneAdapter(requireContext(), this)
        binding.rvEmployees.layoutManager = LinearLayoutManager(requireContext())
        binding.rvEmployees.adapter = adapter
    }

    override fun onClick(performance: String) {
        val action =
            CountryPerRomanceFragmentDirections.actionCountryPerRomanceFragmentToAreaFragment(
                performance
            )
        findNavController().navigate(action)
    }

}