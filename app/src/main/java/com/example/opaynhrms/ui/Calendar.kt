package com.example.opaynhrms.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.opaynhrms.R
import com.kizitonwose.calendarview.ui.ViewContainer

class Calendar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)



        class DayViewContainer(view: View) : ViewContainer(view) {
            val textView = view.findViewById<TextView>(R.id.calendarDayText)

            // With ViewBinding
            // val textView = CalendarDayLayoutBinding.bind(view).calendarDayText
        }
    }
}