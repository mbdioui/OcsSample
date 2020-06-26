package android.ocssample.viewModels

import android.ocssample.remote.OcsApi
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ContentViewModel : ViewModel() {

    val pitchLiveData: LiveData<String?> = MutableLiveData()
    suspend fun getDetailLinkCoroutine(url: String) {
        viewModelScope.launch {
            pitchLiveData as MutableLiveData
            Log.i("test", "${OcsApi().fetchDetailLink(url).body()}")
            pitchLiveData.value =
                OcsApi().fetchDetailLink(url).body()?.contents?.pitch
        }
    }

}