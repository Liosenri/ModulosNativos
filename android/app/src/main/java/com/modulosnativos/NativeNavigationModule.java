package com.modulosnativos;

import android.app.Activity;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableNativeMap;

public class NativeNavigationModule extends ReactContextBaseJavaModule implements ActivityEventListener {

    private static final String ACTIVITY_DOEST_NOT_EXISTS = "ACTIVITY_DOEST_NOT_EXISTS";
    private static final int LOGIN_ACTIVITY_PRESENT = 2;

    NativeNavigationModule(ReactApplicationContext context){
        super(context);
        context.addActivityEventListener(this);
    }

    private Promise loginPromise;

    @NonNull
    @Override
    public String getName() {
        return "NativeNavigationModule";
    }

    @Override
    public void onActivityResult(Activity activity, int i, int i1, @Nullable Intent intent) {
        if(intent != null) {
            String email = intent.getStringExtra("email");
            String password = intent.getStringExtra("password");

            WritableNativeMap nativeMap =  new WritableNativeMap();
            nativeMap.putString("email", email);
            nativeMap.putString("password",password);

            if(loginPromise != null) {
                loginPromise.resolve(nativeMap);
            }
        }

        loginPromise = null;
    }

    @Override
    public void onNewIntent(Intent intent) {}

    @ReactMethod
    public void showLoginActivity(Promise loginPromise) {
        Activity currentActivity = getCurrentActivity();
        if(currentActivity == null) {
            loginPromise.reject(ACTIVITY_DOEST_NOT_EXISTS,"La pantalla no existe");
            return;
        }

        try {
            this.loginPromise = loginPromise;
            Intent intent = new Intent(getReactApplicationContext(),LoginActivity.class);
            currentActivity.startActivityForResult(intent,LOGIN_ACTIVITY_PRESENT);
        } catch (Exception e){
            loginPromise.reject(e);
        }
    }
}
