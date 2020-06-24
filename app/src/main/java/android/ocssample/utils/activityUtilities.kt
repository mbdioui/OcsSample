package android.ocssample.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

class activityUtilities {
    companion object {
        fun closeKeyboard(activity: Activity, editText: EditText) {
            val imm: InputMethodManager =
                activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0)
        }
    }
}