package com.example.txilla.domometeo.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.txilla.domometeo.Activities.MainActivity;
import com.example.txilla.domometeo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConfigurationActivity extends AppCompatActivity {

    @BindView(R.id.serverAdressEditText) EditText serverAdress;
    @BindView(R.id.portEditText) EditText port;
    @BindView(R.id.usernameEditText) EditText username;
    @BindView(R.id.passwordEditText) EditText password;
    @BindView(R.id.pbConfig) ProgressBar pb;
    @BindView(R.id.saveButton) Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);
        ButterKnife.bind(this);

        SharedPreferences sharedPref = this.getSharedPreferences("configuration",Context.MODE_PRIVATE);
        String serverSaved = sharedPref.getString("serverAdress","noAdress");
        String portSaved = sharedPref.getString("port","noPort");
        String usernameSaved = sharedPref.getString("username","noUsername");
        String passwordSaved = sharedPref.getString("password","noPassword");

        if ( !serverSaved.equals("noAdress") && !portSaved.equals("noPort")
                && !usernameSaved.equals("noUsername") && !passwordSaved.equals("noPassword")) {
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    @OnClick(R.id.saveButton)
    public void save() {
        pb.setVisibility(View.VISIBLE);
        buttonSave.setEnabled(false);
        clickSave();
    }

    private void clickSave() {

        String textToast = null;

        String serverAdressText = serverAdress.getText().toString().trim();
        String portText = port.getText().toString().trim();
        String usernameText = username.getText().toString().trim();
        String passwordText = password.getText().toString().trim();

        if (TextUtils.isEmpty(serverAdressText) || TextUtils.isEmpty(portText)
                || TextUtils.isEmpty(usernameText) || TextUtils.isEmpty(passwordText)) {
            textToast = getString(R.string.emptyFields);
        }
        /*else if (password.length() < 8) {
            //textToast = getString(R.string.passwordLength);
        }*/


        if (textToast != null) {
            Toast.makeText(getApplicationContext(), textToast, Toast.LENGTH_LONG).show();
            pb.setVisibility(View.INVISIBLE);
            //menuItemSave.setEnabled(true);
        } else {

            saveData();

        }
    }

    private void saveData() {

        String serverAdressString = serverAdress.getText().toString();

        SharedPreferences sharedPref =
                this.getSharedPreferences("configuration",
                        Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        //editor.putInt(getString(R.string.saved_high_score), newHighScore);
        editor.putString("serverAdress",serverAdress.getText().toString());
        editor.putString("port",port.getText().toString());
        editor.putString("username",username.getText().toString());
        editor.putString("password",password.getText().toString());
        editor.commit();

        pb.setVisibility(View.INVISIBLE);

        startActivity(new Intent(this, MainActivity.class));

    }
}
