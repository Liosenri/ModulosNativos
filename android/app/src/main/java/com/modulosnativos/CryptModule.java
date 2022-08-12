package com.modulosnativos;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class CryptModule extends ReactContextBaseJavaModule {
    CryptUtil cutil = new CryptUtil();

    @NonNull
    @Override
    public String getName() {
        return "CryptModule";
    }

    public CryptModule(ReactApplicationContext context) {
        super(context);
    }

    @ReactMethod
    public void encryptText(String text, Callback onFailure, Callback onSuccess) {
        try {
            String encryptedString = cutil.encrypt(text);
            onSuccess.invoke(encryptedString);
        } catch (Exception e){
            onFailure.invoke(e);
        }
    }

    @ReactMethod
    public void encryptTextPromise(String text, Promise promise) {
        try {
            String encryptedString = cutil.encrypt(text);
            promise.resolve(encryptedString);
        } catch (Exception e){
            promise.reject(e);
        }
    }

    @ReactMethod
    public void decryptText(String text, Callback onFailure, Callback onSuccess) {
        try {
            String encryptedString = cutil.decrypt(text);
            onSuccess.invoke(text);
        } catch (Exception e){
            onFailure.invoke(e);
        }
    }

    @ReactMethod
    public void decryptTextPromise(String text, Promise promise) {
        try {
            String encryptedString = cutil.decrypt(text);
            promise.resolve(encryptedString);
        } catch (Exception e){
            promise.reject(e);
        }
    }
}
