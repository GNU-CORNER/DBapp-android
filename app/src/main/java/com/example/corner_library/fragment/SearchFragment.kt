package com.example.corner_library.fragment

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.Toolbar
import com.example.corner_library.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SearchFragment(val itemClick: (Int) -> Unit) : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.search_bottom_sheet, container, false)
    }

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        toolbar.setNavigationIcon(R.drawable.back_icon)
        toolbar.navigationIcon?.setColorFilter(resources.getColor(R.color.personal_color), PorterDuff.Mode.SRC_ATOP)

        dialog?.setOnShowListener { dialog ->
            val d = dialog as BottomSheetDialog
            val bottomSheetInternal =
                d.findViewById<View>(R.id.design_bottom_sheet)
            bottomSheetInternal?.minimumHeight=
                Resources.getSystem().displayMetrics.heightPixels
            val behavior = BottomSheetBehavior.from<View>(bottomSheetInternal!!)
            behavior.state = BottomSheetBehavior.STATE_EXPANDED

            // 드래그해도 팝업이 종료되지 않도록
            behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                        behavior.state = BottomSheetBehavior.STATE_EXPANDED
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {}
            })

            toolbar.setNavigationOnClickListener(View.OnClickListener {
                d.dismiss()
            })

        }

        val searchText = view.findViewById<TextView>(R.id.searchText)
        searchText.setOnClickListener(View.OnClickListener {
            Toast.makeText(view.context, "됐냐", Toast.LENGTH_SHORT).show()
        })
    }
}