package com.devis.foobatllapp.feature.leaguedetail

import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.marginTop
import com.devis.foobatllapp.R
import com.devis.foobatllapp.core.model.LeagueMdl
import com.devis.foobatllapp.core.util.setImage
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar

/**
 * Created by Devis on 20/09/20
 */

class LeagueDetailActivity : AppCompatActivity() {

    private var mLeagueName: String = ""
    private var mLeagueLogo: Drawable? = null
    private var mLeagueMdl: LeagueMdl? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getExtraData()
        LeagueDetailUI().setContentView(this)
        setSupportActionBar(findOptional(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getExtraData() {
        if (intent.extras != null) {
            mLeagueMdl = Gson().fromJson(intent.extras?.get("league").toString(), LeagueMdl::class.java)
            mLeagueName = mLeagueMdl?.leagueName.orEmpty()
            mLeagueLogo = BitmapDrawable(
                resources,
                BitmapFactory.decodeByteArray(
                    mLeagueMdl?.leagueLogo, 0, mLeagueMdl?.leagueLogo!!.size)
            )
        }
    }

    class LeagueDetailUI : AnkoComponent<LeagueDetailActivity> {

        override fun createView(ui: AnkoContext<LeagueDetailActivity>) = with(ui) {
            verticalLayout {
                toolbar {
                    id = R.id.toolbar
                    title = "League Detail"
                    setTitleTextColor(Color.WHITE)
                    backgroundColor = ContextCompat.getColor(context, R.color.colorPrimary)
                }

                relativeLayout {
                    gravity = Gravity.CENTER
                    backgroundColor = ContextCompat.getColor(context, R.color.colorAccent)

                    linearLayout {
                        gravity = Gravity.CENTER
                        orientation = LinearLayout.VERTICAL

                        imageView {
                            setImage(owner.mLeagueLogo!!)
                        }.lparams(dip(75), dip(75))

                        textView {
                            text = owner.mLeagueName
                            textSize = 18F
                        }.lparams(wrapContent, wrapContent) {
                            topMargin = dip(16)
                        }

                    }.lparams(wrapContent, wrapContent)

                }.lparams(matchParent, 500)

                textView {
                    text = context.getString(R.string.premier_league)
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                    textSize = 16F
                    setLineSpacing(0F, 1.5F)
                }.lparams(matchParent, wrapContent) {
                    topMargin = dip(24)
                    leftMargin = dip(24)
                    rightMargin = dip(24)
                }
            }
        }

    }

}