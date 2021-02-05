package com.devis.foobatllapp.feature.search.presentation

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devis.foobatllapp.R
import com.devis.foobatllapp.core.base.BaseActivity
import com.devis.foobatllapp.core.base.BaseViewState
import com.devis.foobatllapp.core.model.EventMdl
import com.devis.foobatllapp.feature.matchdetail.presentation.MatchDetailActivity
import com.devis.foobatllapp.feature.matchdetail.presentation.NextMatchDetailActivity
import com.devis.foobatllapp.feature.search.adapter.SearchMatchAdapter
import com.devis.foobatllapp.feature.search.ui.SearchMatchUI
import org.jetbrains.anko.findOptional
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.startActivity

class SearchMatchActivity : BaseActivity() {

    companion object {
        val TAG: String = SearchMatchActivity::class.java.simpleName

        fun startThisActivity(context: Context) {
            context.startActivity<SearchMatchActivity>()
        }
    }

    private lateinit var mRvEventList: RecyclerView
    private lateinit var mEtSearch: EditText
    private lateinit var mAdapter: SearchMatchAdapter

    private val handler = Handler(Looper.getMainLooper())
    private var runnable: Runnable? = null

    private val mListEvent = ArrayList<EventMdl>()
    private val mViewModel: SearchMatchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SearchMatchUI().setContentView(this)
        initObserve()
        initToolbar()
        initView()
        initViewAction()
        initRecyclerView()
        setStatusBarColor()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun initToolbar() {
        setSupportActionBar(findOptional(R.id.toolbar))
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun initView() {
        mRvEventList = findOptional(R.id.rv_match_list)!!
        mEtSearch = findOptional(R.id.et_search)!!
    }

    private fun initViewAction() {
        mEtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s.isNullOrEmpty()) {
                    mListEvent.clear()
                    mAdapter.notifyDataSetChanged()
                } else {
                    if (runnable != null) {
                        handler.removeCallbacks(runnable!!)
                    }
                    runnable = Runnable {
                        mViewModel.searchEventByClubName(s.toString())
                    }
                    handler.postDelayed(runnable!!, 500)
                }
            }
        })
    }

    private fun initRecyclerView() {
        mListEvent.clear()
        mAdapter = SearchMatchAdapter(mListEvent) {
            if (it.home_score == null || it.away_score == null) {
                NextMatchDetailActivity.startThisActivity(this, it)
            } else {
                MatchDetailActivity.startThisActivity(this, it)
            }
        }
        mRvEventList.apply {
            layoutManager = LinearLayoutManager(
                this@SearchMatchActivity,
                RecyclerView.VERTICAL,
                false
            )
            adapter = mAdapter
        }
    }

    private fun initObserve() {
        mViewModel.run {
            listEvent.observe(this@SearchMatchActivity, Observer {
                when(it) {
                    is BaseViewState.Loading -> {
                        Log.d(TAG, "LOADING")
                    }
                    is BaseViewState.Success -> {
                        Log.d(TAG, "SUCCESS")
                        if (!it.data?.event.isNullOrEmpty()) {
                            mListEvent.clear()
                            mListEvent.addAll(it.data?.event!!.filter { eventMdl ->
                                eventMdl.str_sport.equals("soccer", ignoreCase = true)
                            })
                            mAdapter.notifyDataSetChanged()
                        }
                    }
                    is BaseViewState.Error -> {
                        Log.d(TAG, "ERROR")
                        Log.e(TAG, it.errorMessage.orEmpty())
                    }
                }
            })
        }
    }

}