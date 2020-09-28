package com.devis.foobatllapp.core.base

import android.content.Context
import androidx.fragment.app.Fragment

/**
 * Created by Devis on 27/09/20
 */

open class BaseFragment : Fragment() {

    protected lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

}