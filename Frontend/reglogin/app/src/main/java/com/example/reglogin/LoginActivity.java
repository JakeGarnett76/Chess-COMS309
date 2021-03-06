package com.example.reglogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.example.reglogin.app.AppController;

/**
 * The LoginActivity class implements the methods used to login to an Advanced Chess account.
 * This class communicates with the server to check if input passwords and usernames are correct
 *
 * @author Theron Gale
 *
 */
public class LoginActivity extends Activity implements OnClickListener {

    /**
     * String TAG- the name of the page
     * Button btnRegister- The register button
     * EditText userlog, userid, userpass- the fields where the user can input username, user ID, and password respectively
     * TextView msgResponse- where the message is shown
     * String passResponse- the message showing a success or failure
     */
    private String TAG = LoginActivity.class.getSimpleName();
    private Button btnRegister, btnLogin;
    private EditText userlog, userid, userpass;
    private TextView msgResponse, output;
    private static String passResponse;

    /**
<<<<<<< HEAD
    Tag to cancel a login request
=======
     Tag to cancel a login request
>>>>>>> 8165317adda8d03f62217424fd037cb7e42f5289
     */
    private String tag_json_obj = "jobj_req";

    /**
     * This method displays the login page with an option to register instead. There are fields where
     * you can input the username, user ID and, password
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        btnRegister = (Button) findViewById(R.id.btnRegisterReq);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        userlog = (EditText) findViewById(R.id.editUsername);
        userid = (EditText) findViewById(R.id.editUserID);
        userpass = (EditText) findViewById(R.id.editPass);

        msgResponse = (TextView) findViewById(R.id.msgResponse);
        output = (TextView) findViewById(R.id.output);

        btnRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

    }

    /**
     * The getPassword method sends a json request to the server to retrieve the password for verification
     */
    private void getPassword() {

        String url = "http://coms-309-022.cs.iastate.edu:8080/usersName/" + userlog.getText().toString() + "/password";



        StringRequest jsonObjReq = new StringRequest(Method.GET,
                url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                        //responseTransfer(response);
                        output.setText(response);
                    }
                }, new Response.ErrorListener() {

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
        // ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_obj)
    }

    /*public static void responseTransfer(String response){
        passResponse = response;
    }*/

    /**
     * The onClick method takes the user inputted username and password and verifies if they are correct or not.
     * The method then displays the appropriate message showing a success or a failure.
     * @param v- The view for the login page
     */
    @Override
    public void onClick(View v){

        if(v.getId() == R.id.btnRegisterReq){
            startActivity(new Intent(LoginActivity.this,
                    RegisterActivity.class));
        }
        else if(v.getId() == R.id.btnLogin){

            getPassword();

            //System.out.println(output.getText().toString());
            //System.out.println(userpass.getText().toString());

            if(output.getText().toString().equals(userpass.getText().toString()) && output.getText().toString() != null){

                msgResponse.setText("Successfully logged in!");
                startActivity(new Intent(LoginActivity.this,
                        HomeActivity.class));
            }
            else{
                msgResponse.setText("Passwords do not match!");
            }

        }
        else{}
    }

}