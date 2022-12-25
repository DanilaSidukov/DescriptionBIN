package com.sidukov.descriptionbin.descriptionbin.ui


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.sidukov.descriptionbin.databinding.ActivityHistoryBinding
import com.sidukov.descriptionbin.descriptionbin.BINApplication
import com.sidukov.descriptionbin.descriptionbin.data.CardBINRepository
import com.sidukov.descriptionbin.descriptionbin.data.remote.APIClient
import kotlinx.coroutines.launch

class HistoryActivity: AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding

    private lateinit var historyBINViewModel: HistoryViewModel

    private lateinit var historyAdapter: HistoryItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        historyBINViewModel = HistoryViewModel(
            CardBINRepository(
                APIClient.binApiClient,
                BINApplication.database.daoHistoryBIN()
            )
        )

        historyAdapter = HistoryItemAdapter(emptyList())
        binding.requestHistoryRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.requestHistoryRecyclerView.adapter = historyAdapter
        binding.requestHistoryRecyclerView.addItemDecoration(EmptyDividerItemDecoration())

        binding.imageBack.setOnClickListener{
            finish()
        }

        binding.buttonClearHistory.setOnClickListener {
            historyBINViewModel.deleteHistory()
            finish()
        }

        lifecycleScope.launch {
            historyBINViewModel.shouldShowNoHistoryText.collect {
                binding.historyEmptyOrNot.isVisible = it
                println("Visibility gone $it")
            }
        }
        lifecycleScope.launch{
            historyBINViewModel.historyData.collect { listHistory ->
                if (listHistory.isEmpty()) return@collect
                historyAdapter.updateList(listHistory)
                println("List History -  $listHistory")
            }
        }
    }
}