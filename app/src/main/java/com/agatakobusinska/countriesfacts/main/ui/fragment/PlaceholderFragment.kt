package com.agatakobusinska.countriesfacts.main.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.agatakobusinska.countriesfacts.databinding.FragmentMainBinding
import com.agatakobusinska.countriesfacts.main.model.entities.Country
import com.agatakobusinska.countriesfacts.main.ui.adapter.Continent.*
import com.agatakobusinska.countriesfacts.main.ui.adapter.CountriesListAdapter
import com.agatakobusinska.countriesfacts.main.viewmodel.PageViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PlaceholderFragment : Fragment() {

    @Inject lateinit var pageViewModel: PageViewModel
    private lateinit var progressBar: ProgressBar
    private lateinit var countriesList: RecyclerView
    private lateinit var adapter: CountriesListAdapter
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMainBinding.inflate(inflater, container, false)
        progressBar = binding.progressCircular
        progressBar.visibility = View.VISIBLE

        countriesList = binding.countriesList

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CountriesListAdapter(requireContext())
        countriesList.adapter = adapter

        pageViewModel.europe.observe(viewLifecycleOwner, { data ->
            if (data.isNullOrEmpty().not())
                reloadBySectionNumber(EUROPE.index, data)
        })
        pageViewModel.americas.observe(viewLifecycleOwner, { data ->
            if (data.isNullOrEmpty().not())
                reloadBySectionNumber(AMERICAS.index, data)
        })
        pageViewModel.asia.observe(viewLifecycleOwner, { data ->
            if (data.isNullOrEmpty().not())
                reloadBySectionNumber(ASIA.index, data)
        })
        pageViewModel.africa.observe(viewLifecycleOwner, { data ->
            if (data.isNullOrEmpty().not())
                reloadBySectionNumber(AFRICA.index, data)
        })
        pageViewModel.oceania.observe(viewLifecycleOwner, { data ->
            if (data.isNullOrEmpty().not())
                reloadBySectionNumber(OCEANIA.index, data)
        })
    }

    private fun reloadBySectionNumber(sectionNumber: Int, data: List<Country>) {
        arguments?.getInt(ARG_SECTION_NUMBER).apply {
            if (this == sectionNumber) {
                adapter.setData(data)
                progressBar.visibility = View.GONE
            }
        }
    }

    companion object {

        private const val ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}