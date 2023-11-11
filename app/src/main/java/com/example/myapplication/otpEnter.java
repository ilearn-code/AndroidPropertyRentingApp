package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class otpEnter extends AppCompatActivity {
    EditText ed;
Button verfybutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_enter);
        ed = (EditText) findViewById(R.id.otped);
//        String edd = ed.getText().toString();
        Intent intent = getIntent();
        String phone_otp = intent.getStringExtra("phone_otp");
        String tokken = intent.getStringExtra("tokken");

        verfybutton=(Button)findViewById(R.id.button_verifyotp);
        verfybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = ed.getText().toString();
               Toast.makeText(otpEnter.this, phone_otp, Toast.LENGTH_LONG).show();
                Toast.makeText(otpEnter.this, text, Toast.LENGTH_LONG).show();
              otpverify(phone_otp, text,tokken);
            }
        });


    }

    private void otpverify(String phonenumber, String otp,String tokken) {

        String url = " link"; // replace with your actual API endpoint

        // create the request parameters
        Map<String, String> params = new HashMap<>();
        params.put("mobile", phonenumber);
        params.put("otp", otp);

        // create the request object
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // handle the response JSON object here

                        Log.d("API Response", response.toString());
                                                    SharedPreferences.Editor editor = getSharedPreferences("mySharedPreferences", MODE_PRIVATE).edit();
                            editor.putString("token", tokken);

                            editor.apply();
                        Intent intent=new Intent(otpEnter.this, HomeActivity.class);
                        startActivity(intent);


                        Toast.makeText(otpEnter.this, response.toString(), Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // handle the error here
                        Toast.makeText(otpEnter.this, error.toString(), Toast.LENGTH_LONG).show();

                    }
                });

        // add the request to the Volley request queue
        Volley.newRequestQueue(this).add(request);
    }
}
