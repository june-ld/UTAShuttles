package com.junelabs.june.utashuttles;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class useful_info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_useful_info);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_useful_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onButtonClick(View view) {
        new myTask2().execute();
    }

    private class myTask2 extends AsyncTask<String, Integer, String> {
        Socket s;

        @Override
        protected String doInBackground(String... params) {
            try{
                s = new Socket("192.168.1.70", 9090); //update with IP address if needed
                //BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
                //String response = input.readLine();

                /* // running addUser
                s.getOutputStream().write("addUser".getBytes());
                s.getOutputStream().write("guest, guestp4ss,guest@test.com,guestAns,0".getBytes());
                */

                // running getUser
                s.getOutputStream().write("getUser".getBytes());
                s.getOutputStream().write("guest".getBytes());
                BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
                String response = input.readLine();

                return response;

            } catch(IOException e){
                return "Error";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if(result.matches("Error")) {
                ifNoConnection();
                return;
            }

            connected(result);
        }
    }

    private void ifNoConnection(){
        Toast.makeText(useful_info.this, "Server is down", Toast.LENGTH_SHORT).show();
    }

    private void connected(String result){
        Toast.makeText(useful_info.this, "Result: " + result, Toast.LENGTH_SHORT).show();
    }
}
