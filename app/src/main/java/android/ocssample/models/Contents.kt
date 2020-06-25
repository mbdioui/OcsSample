package android.ocssample.models


import com.google.gson.annotations.SerializedName

data class Contents(
    val contents: List<Content>,
    val count: Int,
    val filter: Any,
    val limit: String,
    val next: Any,
    val offset: Int,
    val parentalrating: Int,
    val previous: Any,
    val sort: Any,
    val template: String,
    val title: String,
    val total: Int
): java.io.Serializable