package io.mallinicouture.network

import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import retrofit2.http.Body
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.Exception

@Singleton
class TokenAuthenticator
@Inject
constructor(private val tokenHolder: Lazy<TokenHolder>, private val tokenInterceptor: TokenInterceptor) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        if (response.request.header("Authorization") != null ||
                !response.request.header("Authorization").equals("Bearer " + tokenInterceptor.token)) {
            // returning null means we have tried and the refresh failed so exit;
            // this will also get you out of infinite loop resulting from retrying
            return null;
        }

        val tokenService = tokenHolder.get().tokenService

        var token = try {
            tokenInterceptor.token
        } catch (e: Exception) {

        }

        var body: ReloginResponse?= null
        try {
            // This api requires old token to be passed for a new refreshed token TODO change

            body = tokenService.relogin("Bearer " + token).execute()?.body()
        }  catch (e: Exception) {

        }

        val newToken = body?.token
        tokenInterceptor.token = newToken!!

        return response.request.newBuilder()
                .header("Authorization", "Bearer " + newToken)
                .build()
    }
} {
}