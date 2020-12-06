package com.devis.foobatllapp.feature.matchdetail.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.devis.foobatllapp.core.base.BaseActivity
import com.devis.foobatllapp.core.model.EventMdl

class MatchDetailActivity : BaseActivity() {

    companion object {
        fun startThisActivity(context: Context, eventMdl: EventMdl) {
            context.startActivity(
                Intent(context, MatchDetailActivity::class.java)
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}