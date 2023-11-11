package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {


    Button LoginButton;
    TextView dispay;
    EditText emailed, passworded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (isUserLoggedIn()) {
            // User is already logged in, start LogoutActivity
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);

        }





            LoginButton = (Button) findViewById(R.id.button);
            dispay = (TextView) findViewById(R.id.textView4);
            emailed = (EditText) findViewById(R.id.loginusername);
            passworded = (EditText) findViewById(R.id.loginPassword);


            LoginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dispay = (TextView)findViewById(R.id.textView4);

                    String email = emailed.getText().toString(); // read the value of email from the EditText view
                    String password = passworded.getText().toString().trim();
//                dispay.setText(password);
                    makeVolleyRequest(email, password);


                }
            });
        }


    private boolean isUserLoggedIn() {
        // Check if user's token is stored in SharedPreferences or any other storage mechanism
        // Return true if token is found, false otherwise
        SharedPreferences prefs = getSharedPreferences("mySharedPreferences", MODE_PRIVATE);
        String token = prefs.getString("token", null);
        return (token!= null);
    }
    public void tv_reset(View view) {

        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);

    }


    private void makeVolleyRequest(String username, String password) {

        String url = "link"; // replace with your actual API endpoint

        // create the request parameters
        Map<String, String> params = new HashMap<>();
        params.put("email", username);
        params.put("password", password);

        // create the request object
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // handle the response JSON object here

                        Log.d("API Response", response.toString());


                        try {

                            boolean status = response.getBoolean("status");
                            String userToken = response.getString("MessageFragment");
                            Toast.makeText(LoginActivity.this, response.toString(), Toast.LENGTH_SHORT).show();

                            if (status) {
                                // User logged in successfully
                                //JSONObject user = response.getJSONObject("user");
                                String token = response.getString("token");
                                SharedPreferences.Editor editor = getSharedPreferences("mySharedPreferences", MODE_PRIVATE).edit();
                                editor.putString("token", token);

                                editor.apply();
                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(intent);

                            } else {
                                // User not verified, request OTP
                                String resource = response.getString("resource");
                                // Display resource MessageFragment or redirect to resource URL

                                ResquestOtp_login(resource);
                                Intent intent = new Intent(LoginActivity.this, otpEnterLogin.class);
                                startActivity(intent);

                            }

//                            SharedPreferences.Editor token_shared = getSharedPreferences("mySharedPreferences", MODE_PRIVATE).edit();
//
//                            token_shared.putString("token", userToken);
//                            token_shared.apply();


                        } catch (JSONException e) {
                            e.printStackTrace();
//                            Toast.makeText(LoginActivity.this, response.toString(), Toast.LENGTH_LONG).show();

                        }

//
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // handle the error here
                        Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_LONG).show();

                    }
                });

        // add the request to the Volley request queue
        Volley.newRequestQueue(this).add(request);
    }

    private void ResquestOtp_login(String res) {

        String url = res;

        String phoneNumber = Uri.parse(url).getLastPathSegment();




        // Instantiate the RequestQueue
        RequestQueue queue = Volley.newRequestQueue(this);

// Specify the API endpoint URL with the phone number as a parameter


// Create a new GET request
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    Log.d("API Response", response.toString());
                   Intent intent=new Intent(LoginActivity.this, otpEnterLogin.class);
                   intent.putExtra("phone_number_Login", phoneNumber);
                      startActivity(intent);
                    //otpVerify(phoneNumber,);
                    Toast.makeText(LoginActivity.this, phoneNumber, Toast.LENGTH_LONG).show();
                },
                error -> {
                    // Handle the error here
                    Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                });

// Add the request to the RequestQueue
        queue.add(jsonObjectRequest);
    }

//

}


