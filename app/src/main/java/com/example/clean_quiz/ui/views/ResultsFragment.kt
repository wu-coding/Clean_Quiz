package com.example.clean_quiz.ui.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.clean_quiz.databinding.FragmentResultsBinding
import com.example.clean_quiz.databinding.FragmentScoreBinding
import com.example.clean_quiz.ui.adapter.ScoreViewAdapter
import com.example.clean_quiz.ui.adapter.ViewPagerAdapter
import com.example.clean_quiz.ui.viewmodel.ResultsViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ResultsFragment : Fragment() {
    private lateinit var binding: FragmentResultsBinding
    private val resultsViewModel:ResultsViewModel by viewModels()

    val tabNamesArray = arrayOf("Top 10", "User Records", "Search Records")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ViewPagerAdapter(childFragmentManager,lifecycle)
        binding.tabViewpager.adapter = adapter



        binding.tabBar.addOnTabSelectedListener(  object :TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                resultsViewModel.tabNumber.value = tab?.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                resultsViewModel.tabNumber.value = tab?.position
            }
        })


        TabLayoutMediator(binding.tabBar, binding.tabViewpager) { tab, position ->
            tab.text = tabNamesArray[position]
        }.attach()

    }

}