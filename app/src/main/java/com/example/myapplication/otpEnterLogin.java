package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class otpEnterLogin extends AppCompatActivity {

    EditText ed;
    TextView tv;
    Button verfybutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_enter_login);
        ed = (EditText)findViewById(R.id.otped);
        tv=(TextView)findViewById(R.id.textView4);

//        String text = ed.getText().toString();
//        tv.setText(text);

        Intent intent = getIntent();
        String phone_otp = intent.getStringExtra("phone_number_Login");
//        Toast.makeText(otpEnter.this, phone_otp, Toast.LENGTH_LONG).show();
//        Toast.makeText(otpEnter.this, edd, Toast.LENGTH_LONG).show();

        verfybutton = (Button) findViewById(R.id.button_verifyotp);
        verfybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = ed.getText().toString(); // retrieve the text from the EditText
                tv.setText(text);
                Toast.makeText(otpEnterLogin.this, text, Toast.LENGTH_LONG).show();
              //  Toast.makeText(otpEnterLogin.this, text, Toast.LENGTH_LONG).show();
              //  Log.d("API Response", ed);
               otpVerifyLogin(phone_otp, text);
            }
        });
    }
        private void otpVerifyLogin(String pnumber, String otp) {

        String url = " ";
        //RequestQueue initialized


        Map<String, String> params = new HashMap<>();
        params.put("mobile", pnumber);
        params.put("otp", otp);

        // create the request object
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("API Response", response.toString());
                        Toast.makeText(otpEnterLogin.this, url.toString(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(otpEnterLogin.this, pnumber, Toast.LENGTH_SHORT).show();
                        Toast.makeText(otpEnterLogin.this, otp, Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(otpEnterLogin.this, HomeActivity.class);
                          startActivity(intent);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // handle the error here
                        Toast.makeText(otpEnterLogin.this, error.toString(), Toast.LENGTH_LONG).show();

                    }
                });

        // add the request to the Volley request queue
        Volley.newRequestQueue(this).add(request);
    }
}