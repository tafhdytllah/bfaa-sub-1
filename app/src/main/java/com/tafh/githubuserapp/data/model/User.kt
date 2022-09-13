import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var username: String? = "",
    var name: String? = "",
    var avatar: Int? = 0
) : Parcelable