package android.ocssample.models


import com.google.gson.annotations.SerializedName

data class Content(
    val detaillink: String,
    val duration: String,
    val fullscreenimageurl: String,
    val highlefticon: Any,
    val highrighticon: Any,
    val id: String,
    val imageurl: String,
    val lowrightinfo: Any,
    val playinfoid: Playinfoid,
    val subtitle: String,
    val subtitlefocus: Any,
    val title: List<Title>
)