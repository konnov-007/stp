package apps.stp.converter.util

import android.app.Activity
import android.widget.Toast

fun Activity?.toast(text: String) {
    if (this != null) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}