package com.example.atry.myapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

//class to register user by filling form
public class register extends AppCompatActivity {
    EditText fname, mname, lname, address, mobile, aadhar, password;
    Button register;
    register reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fname = (EditText) findViewById(R.id.first_name_register);
        mname = (EditText) findViewById(R.id.middle_name_register);
        lname = (EditText) findViewById(R.id.last_name_register);
        address = (EditText) findViewById(R.id.address_register);
        mobile = (EditText) findViewById(R.id.mobile_register);
        password = (EditText) findViewById(R.id.user_password_register);
        register = (Button) findViewById(R.id.btn_register);
        aadhar = (EditText) findViewById(R.id.aadhar_register_user);
        reg = this;
        register.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                new register_user(getApplicationContext(), reg).execute();
            }
        });
    }

}

//uploading the filed form to server for further processing
class register_user extends AsyncTask<String, Void, String> {

    Context context;
    register user;
    String fname, lname, mname, addr, mobile, aadhar, password;

    public register_user(Context context, register user) {
        this.context = context;
        this.user = user;
    }

    @Override
    protected void onPostExecute(String s) {
        Toast.makeText(context.getApplicationContext(), s, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        fname = user.fname.getText().toString();
        mname = user.mname.getText().toString();
        lname = user.lname.getText().toString();
        addr = user.address.getText().toString();
        mobile = user.mobile.getText().toString();
        aadhar = user.aadhar.getText().toString();
        password = user.password.getText().toString();
    }

    @Override

    protected String doInBackground(String... params) {

        try {
            String link = context.getResources().getString(R.string.link) + "register/";

            URL url = new URL(link);
            URLConnection con = url.openConnection();
            con.setConnectTimeout(15000);
            con.setReadTimeout(15000);
            con.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());

            //JSONObject json = new JSONObject();
            JSONObject add = new JSONObject();
            add.put("fname", fname);
            add.put("mname", mname);
            add.put("lname", lname);
            add.put("address", addr);
            add.put("mobile", mobile);
            add.put("aadhar", aadhar);
            add.put("password",password);

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


