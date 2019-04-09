package konnov.commr.vk.wolframcalc.data.network

import konnov.commr.vk.wolframcalc.data.ResultPod
import okhttp3.*
import java.io.IOException
import java.io.UnsupportedEncodingException
import java.net.URLEncoder

object RemoteDataSource : DataSource{
    override fun onPodsLoaded(pods: ArrayList<ResultPod>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDataNotAvailable() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun getQueryResult(query: String, dataSource : DataSource)  {
        makeRestCall(getFormattedUrl(query), dataSource)
    }


    private fun getFormattedUrl(query: String): String? {
        return WOLFRAM_BASE_URL + "input=" + URLEncoder.encode(
            query,
            "utf-8"
        ) + "&appid=" + WOLFRAM_APP_ID
    }


    private fun makeRestCall(url: String?, dataSource : DataSource): String? {

        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url!!)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                dataSource.onDataNotAvailable()
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                val result = ResultPodXmlParser.parseResultXml(response.body()!!.string(), url)
                dataSource.onPodsLoaded(result!!)
            }
        })
        return null
    }
}