package com.example.alan.talogintest;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by alan on 11/2/17.
 */

public class FireApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Firebase.setAndroidContext(this);
    }
}
