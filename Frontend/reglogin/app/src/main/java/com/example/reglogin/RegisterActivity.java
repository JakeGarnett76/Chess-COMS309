package com.example.reglogin;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.RequestQueue;

import com.example.reglogin.app.AppController;
import com.example.reglogin.net_utils.Const;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * This class will allow the user to register a new account within the AdvancedChess app database.
 * It will ask the user for a username, a password, and a confirmation of that password.
 * If the passwords match, the user is sent back to the login screen where they can log in.
 * If the passwords do not match, the user is notified of the mismatched passwords and is asked to reenter them again.
 *
 * @author Theron Gale
 */
public class RegisterActivity extends Activity implements OnClickListener {

    private String TAG = RegisterActivity.class.getSimpleName();
    private Button btnCreateAccount, btnCancel;
    private EditText newUser, newPass, newPassConfirm;
    private TextView regResponse;
    private ProgressDialog pDialog;
    RequestQueue Queue;

    //Tag to cancel a register request
    private String tag_json_obj = "jobj_req";

    /**
     * Creates and initializes the Register Screen; username, password and confirm password text boxes; and the create account and cancel buttons.
     * @param savedInstanceState The state from which the Register screen will be made.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        btnCreateAccount = (Button) findViewById(R.id.btnCreateAccount);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        newUser = (EditText) findViewById(R.id.newUser);
        newPass = (EditText) findViewById(R.id.newPass);
        newPassConfirm = (EditText) findViewById(R.id.editPass);

        regResponse = (TextView) findViewById(R.id.regResponse);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);

        btnCancel.setOnClickListener(this);
        btnCreateAccount.setOnClickListener(this);

        Queue = Volley.newRequestQueue(this);

    }

    /**
     * Shows a loading icon when the app is comparing the passwords and
     * either adds the user to the app database or notifies the user of a mismatched password.
     */
    private void showProgressDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    /**
     * Hides the loading icon when the register processes (comparing passwords, mismatched
     * passwords, add user to database) are complete.
     */
    private void hideProgressDialog() {
        if (pDialog.isShowing())
            pDialog.hide();
    }

    /**
     * Makes a JSON post request to add a new valid user
     */
    private void makeJsonObjReq() {

        JSONObject userData = new JSONObject();
        try{
            userData.put("username", newUser.getText().toString());
            userData.put("password", newPass.getText().toString());
        } catch(JSONException e){
            e.printStackTrace();
        }


        final String requestBody = userData.toString();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.POST,
                Const.URL_POSTMAN, userData,
                new Response.Listener<JSONObject>() {

                    /**
                     * Register post response from the database showcasing if the post was successful
                     * @param response The response generated from the database.
                     */
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);
                    }
                }, new Response.ErrorListener() {

            /**
             * Register post request error catcher. This responds with an error message if the post request fails.
             * @param error The error that is thrown
             */
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                error.printStackTrace();
            }
                });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq,
                tag_json_obj);

        // Cancelling request
        // ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_obj);

    }

    /**
     * A click listener that when either the Cancel or CreateAccount buttons are pressed, they carry
     * out their respective functions.
     * @param v The spot where the user has clicked/tapped
     */
    @Override
    public void onClick(View v){
        if(v.getId() == R.id.btnCancel){
            startActivity(new Intent(RegisterActivity.this,
                    LoginActivity.class));
        }
        else if(v.getId() == R.id.btnCreateAccount){

            /*String Pass = newPass.getText().toString();
            String Confirm = newPassConfirm.getText().toString();
            String Username = newUser.getText().toString();

            if(Username == null || Pass == null || Confirm == null){
                regResponse.setText("Fill out all fields!");
            }
            if(Pass != Confirm){
                regResponse.setText("Passwords do not match!");
            }*/
            //else {
                makeJsonObjReq();

                startActivity(new Intent(RegisterActivity.this,
                        LoginActivity.class));
            //}

        }
        else{}
    }

}
