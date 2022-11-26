package com.evanemran.kickoff.sharedprefs;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.core.content.SharedPreferencesKt;

import com.evanemran.kickoff.models.User;
import com.google.gson.Gson;

public class SharedPrefs {
    public static final String SHARED_PREFS = "sharedPrefs";

    Context context;

    public SharedPrefs(Context context) {
        this.context = context;
    }

    public void saveUser(User user) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);

        User userObj = new User();

        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(userObj);
        prefsEditor.putString("user", json);
        prefsEditor.apply();
    }

    public User getUser() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("user", "");

        return gson.fromJson(json, User.class);
    }

    public void deleteInfo() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.clear().apply();
    }

}
