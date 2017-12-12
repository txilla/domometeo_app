package com.example.txilla.domometeo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by tXillA on 26/10/2017.
 */

public class GetJSON extends AsyncTask<Void, Void, JSONObject> {

    Context mContext;
    List<TextView> tvListValues;
    List<TextView> tvListNames;

    /* Constructor */
    public GetJSON(Context aContext, List<TextView> list, List<TextView> namesList) {

        mContext = aContext;
        tvListValues = list;
        tvListNames = namesList;
    }

    @Override
    protected JSONObject doInBackground(Void... voids) {

        JSONObject result = null;



        try {

            SharedPreferences sharedPref = mContext.getSharedPreferences("configuration",Context.MODE_PRIVATE);
            String serverSaved = sharedPref.getString("serverAdress","noAdress");

            String urlString ="http://" + serverSaved + ":8080/json.htm?type=devices&filter=all&used=true&order=Name";

            // Create url
            //
            URL url = new URL(urlString);

            // Create connection to url
            HttpURLConnection urlConnection =  (HttpURLConnection)url.openConnection();

            // Connection status
            int status = urlConnection.getResponseCode();
            Log.d("Connexio", "Estat que retorna la connexió: " + status );

            // Check the connection status
            if (status == 200) // if response code = 200 ok
            {
                // Read the BufferedInputStream
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(in));

                // Se leen los datos
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();
                Log.d("Connexio", sb.toString());

                // Se desconecta del servidor
                urlConnection.disconnect();
                Log.d("Connexio", "Connexió tancada");

                // Se construye el json con el string que se ha recuperado del servidor
                JSONObject json = new JSONObject(sb.toString());
                result = json;
            } else {
                Log.d("Connexio","Estado mal.");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // if result is null: error with server
        //
        return result;
    }

    @Override
    protected void onPostExecute(JSONObject jObject) {
        //super.onPostExecute(jsonObject);

        boolean ok = true;

        if (jObject == null) {
            Toast.makeText(mContext,
                    "Servidor de dades no disponible.",
                    Toast.LENGTH_LONG).show();
            ok = false;

        }



        // Si no hay errores, se leen los datos del JSON
        if (ok) {
            try {




                for (int i = 0; i < jObject.length() - 1; i++) {
                    // Se lee el JSON
                    //String name = jObject.getJSONObject("" + i).getString("Name");
                    String values = jObject.getJSONArray("result").getJSONObject(i).getString("Data");
                    tvListValues.get(i).setText(": " + values);

                    String names = jObject.getJSONArray("result").getJSONObject(i).getString("Name");
                    tvListNames.get(i).setText(names);

                }
            } catch (Exception ex) {
                Log.d("Devolver", "Excepció ");
                ex.printStackTrace();
            }
        }

    }
}
