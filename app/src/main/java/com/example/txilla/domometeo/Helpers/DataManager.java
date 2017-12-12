package com.example.txilla.domometeo.Helpers;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by tXillA on 11/12/2017.
 */

public class DataManager {

    public static void saveSharedPreferences(String serverAdress, String port, Context context) {

        SharedPreferences sharedPref =
                context.getSharedPreferences("configuration",
                        Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        //editor.putInt(getString(R.string.saved_high_score), newHighScore);
        editor.putString("serverAdress",serverAdress);
        editor.putString("port",port);
        //editor.putString("username",username.getText().toString());
        //editor.putString("password",password.getText().toString());
        editor.commit();
    }
}
