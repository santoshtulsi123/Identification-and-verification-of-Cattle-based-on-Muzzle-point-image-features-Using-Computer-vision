package com.example.atry.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by try on 7/10/2017.
 */

public class login_user extends AppCompatActivity {
    Button login_btn;
    EditText uname, pass;
    login_user usr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        usr = this;
        uname = (EditText) findViewById(R.id.user_name);
        pass = (EditText) findViewById(R.id.user_password);
        login_btn = (Button) findViewById(R.id.btn_login);
        login_btn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                new send_data(getApplicationContext(), usr).execute();
            }
        });
    }

}


class send_data extends AsyncTask<String, Void, String> {

    Context context;
    login_user user;
    String uname, pass;

    public send_data(Context context, login_user user) {
        this.context = context;
        this.user = user;
    }

    @Override
    protected void onPostExecute(String s) {

        try {

            JSONObject object = new JSONObject(s);
            String result = object.getString("result");
            if (result.equals("True")) {
                JSONObject data = object.getJSONObject("data");
                String uid = data.getString("uid");
                SharedPreferences.Editor editor = context.getSharedPreferences("user", MODE_PRIVATE).edit();
                editor.putString("uid", uid);
                editor.apply();
                editor.commit();
                Toast.makeText(context.getApplicationContext(), "Login Successful"+uid, Toast.LENGTH_LONG).show();
                Intent upload = new Intent(context.getApplicationContext(), Upload_photo.class);
                upload.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(upload);
            } else {
                Toast.makeText(context.getApplicationContext(), "Invalid Credentials"+s, Toast.LENGTH_LONG).show();
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        uname = user.uname.getText().toString();
        pass = user.pass.getText().toString();
    }

    @Override

    protected String doInBackground(String... params) {

        try {
            String link = context.getResources().getString(R.string.link)+"login/";

            URL url = new URL(link);
            URLConnection con = url.openConnection();

            con.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());

            JSONObject json = new JSONObject();
            JSONObject add = new JSONObject();
            add.put("uname", uname);
            add.put("password", pass);


            wr.write(add.toString());
            wr.flush();
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null) {

                sb.append(line);
            }
            return sb.toString();


        } catch (Exception e) {
            Log.d("ERROR", e.getMessage());
            return "Exception: " + e.getMessage();
        }
    }
}


