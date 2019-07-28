package com.android.test.miaguila.ui.customViews

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.android.test.miaguila.R



class RouteSummaryView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var summaryText : TextView
    private var summaryImage : ImageView
    init {
        inflate(context, R.layout.route_summary_item, this)
        summaryImage = findViewById(R.id.summaryImage)
        summaryText = findViewById(R.id.summaryLabel)
        val summaryLine = findViewById<View>(R.id.summaryLine)
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.RouteSummaryView, defStyleAttr, 0)
        summaryText.text = attributes.getString(R.styleable.RouteSummaryView_text)
        val resource = attributes.getResourceId(R.styleable.RouteSummaryView_image,0)
        val end = attributes.getBoolean(R.styleable.RouteSummaryView_end, false)
        summaryImage.setBackgroundResource(resource)
        if(end){
            summaryLine.visibility = View.GONE
        } else {
            summaryLine.visibility = View.VISIBLE
        }
        attributes.recycle()
    }

    fun setText(text : String){
        summaryText.text = text
    }
}