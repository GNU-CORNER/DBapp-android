package com.example.corner_library.view.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.widget.Toolbar
import com.example.corner_library.R
import com.example.corner_library.view.activity.SearchResultActivity
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

        val toolbar: Toolbar = view.findViewById(R.id.search_toolbar)
        val editText: EditText = view.findViewById(R.id.edit_text)
        val bottomSheetLayout: View = view.findViewById(R.id.bottom_sheet_layout)
        toolbar.setNavigationIcon(R.drawable.back_icon)

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
            // 뒤로가기 버튼 클릭 시 BottomSheet 내리기
            toolbar.setNavigationOnClickListener(View.OnClickListener {
                d.dismiss()
            })

        }

        // 검색 입력창 관련 메소드
        editText.setOnKeyListener { view, keyCode, keyEvent ->
            val handled = false
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

            // 엔터 또는 키보드 내 완료 버튼 눌렀을때 행동
            if (keyEvent.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                imm.hideSoftInputFromWindow(editText.windowToken, 0)
                val intent = Intent(context, SearchResultActivity::class.java)
                startActivity(intent)
                dialog?.dismiss()
//                Toast.makeText(context, "검색 입력 완료", Toast.LENGTH_SHORT).show()
            }

            handled
        }
        // 바탕화면 클릭 시 키보드 내리기
        bottomSheetLayout.setOnClickListener(View.OnClickListener {
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(editText.windowToken, 0)
        })
    }
}