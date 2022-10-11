package com.example.nasacustomapp.view.maincontent.behavior

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.nasacustomapp.R


class TextBehavior(context: Context, attrs: AttributeSet? = null) :
    CoordinatorLayout.Behavior<TextView>(context, attrs) {

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: TextView,
        dependency: View
    ): Boolean {
        return (dependency.id== R.id.id_bottom_sheet_id)
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: TextView,
        dependency: View
    ): Boolean {
        if (dependency.id== R.id.id_bottom_sheet_id) {
            child.y = dependency.y-child.height
        }
        return super.onDependentViewChanged(parent, child, dependency)
    }
}