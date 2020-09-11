package io.mallinicouture.network

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenInterceptor @Inject constructor(): Interceptor {
    var token: String = "";

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request();

        if (original.url.encodedPath.contains("/login") && original.method.equals("post") ||
                original.url.encodedPath.contains("/sign-up") && original.method.equals("post")) {
            val originalHttpUrl = original.url;
            val requestBuilder = original
                    .newBuilder()
                    .addHeader("Authorization", token)
                    .url(originalHttpUrl)

            val request = requestBuilder.build()

            return chain.proceed(request);
        }

        return chain.proceed(original);
    }

}