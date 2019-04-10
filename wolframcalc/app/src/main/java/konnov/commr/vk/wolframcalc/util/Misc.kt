package konnov.commr.vk.wolframcalc.util

import android.content.Context
import android.graphics.Point
import android.net.ConnectivityManager
import android.view.WindowManager


private var screenHeight = 0

// Check remote connectivity
fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connectivityManager.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
}


fun getScreenHeight(c: Context?): Int {
    if (screenHeight == 0) {
        val wm = c?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val size = Point()
        display.getSize(size)
        screenHeight = size.y
    }

    return screenHeight
}
