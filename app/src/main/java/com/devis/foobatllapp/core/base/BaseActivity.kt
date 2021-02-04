package com.devis.foobatllapp.core.base

import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.devis.foobatllapp.R

/**
 * Created by Devis on 27/09/20
 */

open class BaseActivity : AppCompatActivity() {

    @Suppress("DEPRECATION")
    protected fun setStatusBarColor(color: Int = R.color.colorPrimary) {
        val window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = ContextCompat.getColor(this, color)
    }

}