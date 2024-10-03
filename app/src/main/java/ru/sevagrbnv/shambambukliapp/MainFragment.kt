package ru.sevagrbnv.shambambukliapp

import CellListAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import ru.sevagrbnv.shambambukliapp.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private val viewModel by viewModels<MainViewModel>()

    private val binding by lazy { FragmentMainBinding.inflate(layoutInflater) }

    private var cellListAdapter: CellListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecView()

        binding.addCell.setOnClickListener {
            viewModel.addCell()
        }
    }

    private fun setRecView() {
        cellListAdapter = CellListAdapter()
        lifecycleScope.launch {
            viewModel.cellList.collect {
                cellListAdapter?.submitList(it)
            }
        }
        binding.recyclerView.adapter = cellListAdapter
    }
}