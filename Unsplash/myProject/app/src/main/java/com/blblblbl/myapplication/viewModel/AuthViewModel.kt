package com.blblblbl.myapplication.viewModel

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import com.blblblbl.myapplication.AuthActivity
import com.blblblbl.myapplication.MainActivity
import com.blblblbl.myapplication.data.persistant_sorage.PersistantStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import net.openid.appauth.*
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val persistantStorage: PersistantStorage
):ViewModel() {
    val serviceConfig = AuthorizationServiceConfiguration(
        Uri.parse("https://unsplash.com/oauth/authorize"),  // authorization endpoint
        Uri.parse("https://unsplash.com/oauth/token")) // token endpoint
    var authState = AuthState(serviceConfig)
    var authRequestBuilder = AuthorizationRequest.Builder(
        serviceConfig,  // the authorization service configuration
        MY_CLIENT_ID,  // the client ID, typically pre-registered and static
        ResponseTypeValues.CODE,  // the response_type value: we want a code
        MY_REDIRECT_URI.toUri()
    ) // the redirect URI to which the auth response is sent
    var authRequest = authRequestBuilder
        .setScope("public read_user write_user read_photos write_photos write_likes write_followers write_collections read_collections")
        .build()
    var context = context
    var authService = AuthorizationService(context)
    fun rememberedAuth(){
        val token =persistantStorage.getProperty(PersistantStorage.AUTH_TOKEN)
        if (token!=null){
            Log.d("MyLog","token exist: $token")
            val intent = Intent(context, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent);
        }
    }
    fun auth(){
        authService.performAuthorizationRequest(
            authRequest,
            //PendingIntent.getActivity(this, 0, Intent(this, MyAuthCompleteActivity::class.java), 0),
            PendingIntent.getActivity(context, 0, Intent(context, MainActivity::class.java), PendingIntent.FLAG_IMMUTABLE),
            PendingIntent.getActivity(context, 0, Intent(context, AuthActivity::class.java), PendingIntent.FLAG_IMMUTABLE))
        /*val token =persistantStorage.getProperty(PersistantStorage.AUTH_TOKEN)
        if (token!=null){
            Log.d("MyLog","token exist: $token")
            val intent = Intent(context, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent);
        }
        else{
            authService.performAuthorizationRequest(
                authRequest,
                //PendingIntent.getActivity(this, 0, Intent(this, MyAuthCompleteActivity::class.java), 0),
                PendingIntent.getActivity(context, 0, Intent(context, MainActivity::class.java), 0),
                PendingIntent.getActivity(context, 0, Intent(context, AuthActivity::class.java), 0))
        }*/
        Log.d("MyLog","auth_button_click")
       /* val intent = Intent(context, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent);*/
        /*authService.performAuthorizationRequest(
            authRequest,
            //PendingIntent.getActivity(this, 0, Intent(this, MyAuthCompleteActivity::class.java), 0),
            PendingIntent.getActivity(context, 0, Intent(context, MainActivity::class.java), 0),
            PendingIntent.getActivity(context, 0, Intent(context, MainActivity::class.java), 0))*/

    }

    companion object{
        //1
        const val MY_CLIENT_ID:String ="RoIF7WHVqFj86IPcmNSz_dKxmUaDRGANTaSk9aqnyac"
        //2
        //const val MY_CLIENT_ID:String ="Yv9weUh_uPLLfEBUI_zNzh5yJoMuRpXSYyjfAZV66WY"
        const val MY_REDIRECT_URI:String ="myproject://www.exagfdasrvxcmple.com/gizmos"
        //const val MY_REDIRECT_URI:String ="myproject2://www.exagfdasrvxcmple.com/gizmos"
    }
}