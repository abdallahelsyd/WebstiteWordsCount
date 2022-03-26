package com.instabug.instabugwordscount.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.instabug.instabugwordscount.R
import com.instabug.instabugwordscount.databinding.ActivityMainBinding
import com.instabug.core.domain.models.Sorting
import com.instabug.core.domain.models.ViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binder: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()
    lateinit var wordsListAdapter: WordsListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder = DataBindingUtil.setContentView(this, R.layout.activity_main)
        wordsListAdapter= WordsListAdapter()
        binder.rvWords.adapter=wordsListAdapter
        observeLiveData()

        setOnClickListener()

    }


    private fun setOnClickListener() {
        binder.pullToRefresh.setOnRefreshListener {
            viewModel.getData(
                if (binder.cbSort.isChecked)
                    Sorting.Descending
                else
                    Sorting.Ascending,
                binder.etSearch.text.toString()
            )
        }
        binder.ivSearch.setOnClickListener {
            viewModel.getData(
                if (binder.cbSort.isChecked)
                    Sorting.Descending
                else
                    Sorting.Ascending,
                binder.etSearch.text.toString()
            )
        }
        binder.cbSort.setOnCheckedChangeListener { compoundButton, b ->
            when (b) {
                true -> viewModel.getData(
                    Sorting.Descending, binder.etSearch.text.toString()
                )
                false -> viewModel.getData(
                    Sorting.Ascending, binder.etSearch.text.toString()
                )
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeLiveData() {
        lifecycleScope.launchWhenStarted {
            viewModel.dataSaved.observe(this@MainActivity) {
                when (it){
                    is ViewState.Loading->{
                        binder.pullToRefresh.isRefreshing=true
                    }
                    is WordsListViewState.OnSuccess->{
                        binder.pullToRefresh.isRefreshing=false
                        wordsListAdapter.submitList(it.data)
                        wordsListAdapter.notifyDataSetChanged()
                        binder.rvWords.scrollToPosition(0)
                    }
                    is ViewState.NetworkError->{
                        binder.pullToRefresh.isRefreshing=false
                        Toast.makeText(this@MainActivity,"Network Error",Toast.LENGTH_LONG).show()
                    }
                    is ViewState.UnknownError->{
                        Toast.makeText(this@MainActivity,"Unknown Error",Toast.LENGTH_LONG).show()
                    }
                    is ViewState.Empty->{
                        AlertDialog.Builder(this@MainActivity)
                            .setMessage("can't find this word")
                            .setCancelable(true)
                            .setPositiveButton("Retry") { dialog, id ->
                                viewModel.getData(
                                    if (binder.cbSort.isChecked)
                                        Sorting.Descending
                                    else
                                        Sorting.Ascending,
                                    binder.etSearch.text.toString()
                                )
                                finish()
                            }.create().show()
                    }
                }
            }
        }
    }
}