package com.emreerkahraman.movieappcompose.api

import android.util.Log
import com.emreerkahraman.movieappcompose.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        val url = request.url.newBuilder()
            .addQueryParameter("api_key", BuildConfig.API_KEY).build()

        request = request.newBuilder().url(url).build()

        Log.i("reqUrl", request.toString())

        return chain.proceed(request)
    }
}
