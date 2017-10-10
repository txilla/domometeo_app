package com.example.txilla.domometeo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConfigurationActivity extends AppCompatActivity {

    @BindView(R.id.serverAdressEditText) EditText serverAdress;
    @BindView(R.id.portEditText) EditText port;
    @BindView(R.id.usernameEditText) EditText username;
    @BindView(R.id.passwordEditText) EditText password;
    @BindView(R.id.pbConfig) ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);
        ButterKnife.bind(this);
    }

    private void saveData() {

        String textToast = null;

        String serverAdressText = serverAdress.getText().toString().trim();
        String portText = port.getText().toString().trim();
        String usernameText = username.getText().toString().trim();
        String passwordText = password.getText().toString().trim();

        if (TextUtils.isEmpty(serverAdressText) || TextUtils.isEmpty(portText) || TextUtils.isEmpty(usernameText) || TextUtils.isEmpty(passwordText)) {
            textToast = getString(R.string.emptyFields);
        } else if (!Patterns.IP_ADDRESS.matcher(serverAdressText).matches()) {
            //textToast = getString(R.string.emailNoExist);
        }
        /*else if (password.length() < 8) {
            //textToast = getString(R.string.passwordLength);
        }*/


        if (textToast != null) {
            Toast.makeText(getApplicationContext(), textToast, Toast.LENGTH_LONG).show();
            pb.setVisibility(View.INVISIBLE);
            //menuItemSave.setEnabled(true);
        } else {

            //register(username, password, mail);

        }
    }
}
