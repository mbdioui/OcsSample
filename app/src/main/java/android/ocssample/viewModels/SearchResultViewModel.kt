package android.ocssample.viewModels

import android.app.Activity
import android.ocssample.models.Contents
import android.ocssample.remote.OcsApi
import android.ocssample.utils.activityUtilities
import android.util.Log
import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchResultViewModel : ViewModel() {

    private val viewModelTag = "ContentsViewModel"

    val contents: LiveData<Contents> = MutableLiveData()

    /**
     * coroutines fetching contents
     */
    private fun launchingContentsCoroutine(title: String) {
        viewModelScope.launch {
            contents as MutableLiveData
            contents.value = getContents(encodeTitle(title))
        }
    }

    /**
     *handling search click event
     */
    fun onClick(activity: Activity, editText: EditText) {
        launchingContentsCoroutine(editText.text.toString())
        activityUtilities.closeKeyboard(activity, editText)
    }

    /**
     * prefixing encoding @param "search"
     */
    private fun encodeTitle(title: String): String {
        return "title%3D$title"
    }

    /**
     * invoking API interface,@Get fetching contents
     */
    private suspend fun getContents(title: String): Contents? {
        return withContext(Dispatchers.IO) {
            Log.i(viewModelTag, "Getting contents")
            OcsApi().fetchContents(title).body()
        }
    }
}