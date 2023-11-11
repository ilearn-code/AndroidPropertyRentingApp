package com.example.myapplication;

import static com.android.volley.VolleyLog.TAG;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class register_user extends AppCompatActivity {
EditText user,email,mobileno,password,cpassword;
RadioGroup rg;
Button rg_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        user= findViewById(R.id.rg_username);
        email= findViewById(R.id.rg_email);
        mobileno= findViewById(R.id.rg_mobno);
        password= findViewById(R.id.rg_password);
        cpassword= findViewById(R.id.rg_confirm_password);
         rg = findViewById(R.id.rg_rg);
        rg_button=(Button) findViewById(R.id.rg_btn);

        rg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user_s = user.getText().toString();
                String email_s = email.getText().toString();
                String mobileno_s = mobileno.getText().toString();
                String password_s = password.getText().toString().trim();

                String cpassword_s = cpassword.getText().toString().trim();

                String value = "";
                int checkedId = rg.getCheckedRadioButtonId();

                switch (checkedId) {
                    case R.id.rg_rd_br_2:
                        value = "2";
                        break;
                    case R.id.rg_rd_ow_3:
                        value = "3";
                        break;
                    case R.id.rg_rd_us_4:
                        value = "4";
                        break;
                }


                makeVolleyRequest(user_s, email_s, mobileno_s, password_s, cpassword_s, value);

            }
        });

    }





    private void makeVolleyRequest(String user_v, String email_v , String mobileno_v, String password_v , String pconfirm_v , String val ) {

        String url = "link"; // replace with your actual API endpoint

        // create the request parameters
        Map<String, String> params = new HashMap<>();
        params.put("username", user_v);
        params.put("email", email_v);
        params.put("mobile", mobileno_v);
        params.put("password", password_v);
        params.put("confirm_password", pconfirm_v);
        params.put("type", val);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // handle the response JSON object here

                        Log.d("API Response", response.toString());


                        try {
                            String token = response.getString("token");
//                            SharedPreferences.Editor editor = getSharedPreferences("mySharedPreferences", MODE_PRIVATE).edit();
//                            editor.putString("token", token);
//
//                            editor.apply();
                            Intent intent=new Intent(register_user.this, otpEnter.class);
                            intent.putExtra("phone_otp", mobileno_v);
                            intent.putExtra("tokken", token);
                            startActivity(intent);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }



                        Toast.makeText(register_user.this, response.toString(), Toast.LENGTH_LONG).show();

//                        sendAndRequestResponse(mobileno_v);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // handle the error here
                        Toast.makeText(register_user.this, error.toString(), Toast.LENGTH_LONG).show();
//                        Intent intent=new Intent(register_user.this, HomeActivity.class);
                    }
                });

        // add the request to the Volley request queue
        Volley.newRequestQueue(this).add(request);
    }
//    private void sendAndRequestResponse(String phone) {
//
//        String url = " " + phone;
//        //RequestQueue initialized
//
//
//        // Instantiate the RequestQueue
//        RequestQueue queue = Volley.newRequestQueue(this);
//
//// Specify the API endpoint URL with the phone number as a parameter
//
//
//// Create a new GET request
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
//                response -> {
//                    Log.d("API Response", response.toString());
//                    Intent intent=new Intent(register_user.this, otpEnter.class);
//                    intent.putExtra("phone_otp", phone);
//                    startActivity(intent);
//                },
//                error -> {
//                    // Handle the error here
//                    Toast.makeText(register_user.this, error.toString(), Toast.LENGTH_LONG).show();
//                });
//
//// Add the request to the RequestQueue
//        queue.add(jsonObjectRequest);
//    }
    }
