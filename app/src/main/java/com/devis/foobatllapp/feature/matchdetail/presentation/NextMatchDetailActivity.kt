package com.devis.foobatllapp.feature.matchdetail.presentation

import android.content.Context
import android.os.Bundle
import com.devis.foobatllapp.R
import com.devis.foobatllapp.core.base.BaseActivity
import com.devis.foobatllapp.core.model.EventMdl
import com.devis.foobatllapp.feature.matchdetail.ui.NextMatchDetailUI
import org.jetbrains.anko.findOptional
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.startActivity

class NextMatchDetailActivity : BaseActivity() {

    companion object {
        private const val EXTRA_EVENT_DATA = "eventData"

        fun startThisActivity(context: Context, eventMdl: EventMdl) {
            context.startActivity<NextMatchDetailActivity>(
                EXTRA_EVENT_DATA to eventMdl
            )
        }
    }

    private var mEventMdl: EventMdl? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getExtraData()
        NextMatchDetailUI(mEventMdl).setContentView(this)
        initToolbar()
        setStatusBarColor()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun initToolbar() {
        setSupportActionBar(findOptional(R.id.toolbar))
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun getExtraData() {
        mEventMdl = intent.getSerializableExtra(EXTRA_EVENT_DATA) as EventMdl
    }

}