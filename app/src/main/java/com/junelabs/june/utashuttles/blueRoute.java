package com.junelabs.june.utashuttles;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class blueRoute extends AppCompatActivity {

    int nextItem = 0; //0-9

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blue_route);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_blue_route, menu);
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

    public void onReceiveData(View view) {
        new myTask().execute();

    }

    private class myTask extends AsyncTask<String, Integer, String>{
        @Override
        protected String doInBackground(String... params) {
            try{
                Socket s = new Socket("192.168.1.106", 9090); //update with IP address if needed
                BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
                String answer = input.readLine();
                return answer;
            } catch(IOException e){
                return "Error";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if(result == "Error") {
                ifNoConnection();
                return;
            }


            LinearLayout capsule = (LinearLayout)findViewById(R.id.stopCapsule);
            for(int i = 0; i < 10; i++) {
                LinearLayout stop = (LinearLayout)capsule.getChildAt(i);
                stop.setBackgroundColor(Color.TRANSPARENT);
                TextView tx = (TextView)stop.getChildAt(0);
                tx.setTypeface(null,Typeface.NORMAL);
                tx = (TextView)stop.getChildAt(1);
                tx.setVisibility(View.GONE);
            }

            String splitUp[] = result.split(" ");
            Calendar cal;
            Date date;
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            try{
                date = sdf.parse(splitUp[1]);
            } catch (ParseException e){
                date = new Date();
            }
            cal = GregorianCalendar.getInstance();
            cal.setTime(date);



            int stopNum = 0;
            LinearLayout layout;
            switch (splitUp[0]){
                case "Stop0":
                    layout = (LinearLayout)findViewById(R.id.BS0);
                    stopNum = 0;
                    break;
                case "Stop1":
                    layout = (LinearLayout)findViewById(R.id.BS1);
                    stopNum = 1;
                    break;
                case "Stop2":
                    layout = (LinearLayout)findViewById(R.id.BS2);
                    stopNum = 2;
                    break;
                case "Stop3":
                    layout = (LinearLayout)findViewById(R.id.BS3);
                    stopNum = 3;
                    break;
                case "Stop4":
                    layout = (LinearLayout)findViewById(R.id.BS4);
                    stopNum = 4;
                    break;
                case "Stop5":
                    layout = (LinearLayout)findViewById(R.id.BS5);
                    stopNum = 5;
                    break;
                case "Stop6":
                    layout = (LinearLayout)findViewById(R.id.BS6);
                    stopNum = 6;
                    break;
                case "Stop7":
                    layout = (LinearLayout)findViewById(R.id.BS7);
                    stopNum = 7;
                    break;
                case "Stop8":
                    layout = (LinearLayout)findViewById(R.id.BS8);
                    stopNum = 8;
                    break;
                default:
                    layout = (LinearLayout)findViewById(R.id.BS9);
                    stopNum = 9;
                    break;
            }


            int i = stopNum;
            Calendar antiTime = GregorianCalendar.getInstance();;
            antiTime.setTime(date);
            do {
                i++;
                if(i > 9)
                    i = 0;
                LinearLayout stop = (LinearLayout)capsule.getChildAt(i);
                TextView tx = (TextView)stop.getChildAt(2);

                if(i == 0){
                    antiTime.add(Calendar.MINUTE, 3);
                    tx.setText("Expected to arrive at: " + sdf.format(antiTime.getTime()));
                }
                if(i == 1){
                    antiTime.add(Calendar.MINUTE, 2);
                    tx.setText("Expected to arrive at: " + sdf.format(antiTime.getTime()));
                }
                if(i == 2){
                    antiTime.add(Calendar.MINUTE, 2);
                    tx.setText("Expected to arrive at: " + sdf.format(antiTime.getTime()));
                }
                if(i == 3){
                    antiTime.add(Calendar.MINUTE, 2);
                    tx.setText("Expected to arrive at: " + sdf.format(antiTime.getTime()));
                }
                if(i == 4){
                    antiTime.add(Calendar.MINUTE, 1);
                    tx.setText("Expected to arrive at: " + sdf.format(antiTime.getTime()));
                }
                if(i == 5){
                    antiTime.add(Calendar.MINUTE, 4);
                    tx.setText("Expected to arrive at: " + sdf.format(antiTime.getTime()));
                }
                if(i == 6){
                    antiTime.add(Calendar.MINUTE, 3);
                    tx.setText("Expected to arrive at: " + sdf.format(antiTime.getTime()));
                }
                if(i == 7){
                    antiTime.add(Calendar.MINUTE, 1);
                    tx.setText("Expected to arrive at: " + sdf.format(antiTime.getTime()));
                }
                if(i == 8){
                    antiTime.add(Calendar.MINUTE, 1);
                    tx.setText("Expected to arrive at: " + sdf.format(antiTime.getTime()));
                }
                if(i == 9){
                    antiTime.add(Calendar.MINUTE, 1);
                    tx.setText("Expected to arrive at: " + sdf.format(antiTime.getTime()));
                }
            } while(i != stopNum);




            layout.setBackgroundColor(Color.parseColor("#81DAF5"));
            TextView tx = (TextView)layout.getChildAt(0);
            tx.setTypeface(null, Typeface.BOLD);
            tx = (TextView)layout.getChildAt(1);
            tx.setText("Arrived at: " + sdf.format(cal.getTime()));
            tx.setVisibility(View.VISIBLE);
        }
    }

    private void ifNoConnection(){
        Toast.makeText(blueRoute.this, "No Connection from server", Toast.LENGTH_SHORT).show();
        Calendar check = GregorianCalendar.getInstance();
        Calendar run = GregorianCalendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date date;
        LinearLayout capsule = (LinearLayout)findViewById(R.id.stopCapsule);

        for(int i = 0; i < 10; i++) {
            LinearLayout stop = (LinearLayout)capsule.getChildAt(i);
            stop.setBackgroundColor(Color.TRANSPARENT);
            TextView tx = (TextView)stop.getChildAt(0);
            tx.setTypeface(null,Typeface.NORMAL);
            tx = (TextView)stop.getChildAt(1);
            tx.setVisibility(View.GONE);
        }



        LinearLayout layout;
        int minute = run.get(Calendar.MINUTE);
        int stopNum = 0;

        switch (minute){
            //times for stop 0
            case 0:
            case 1:
                check.set(Calendar.MINUTE, 0);
                stopNum = 0;
                break;
            case 20:
            case 21:
                check.set(Calendar.MINUTE, 20);
                stopNum = 0;
                break;
            case 40:
            case 41:
                check.set(Calendar.MINUTE, 40);
                stopNum = 0;
                break;

            //times for stop 1
            case 2:
            case 3:
                check.set(Calendar.MINUTE, 2);
                stopNum = 1;
                break;
            case 22:
            case 23:
                check.set(Calendar.MINUTE, 2);
                stopNum = 1;
                break;
            case 42:
            case 43:
                check.set(Calendar.MINUTE, 2);
                stopNum = 1;
                break;

            //times for stop 2
            case 4:
            case 5:
                check.set(Calendar.MINUTE, 4);
                stopNum = 2;
                break;
            case 24:
            case 25:
                check.set(Calendar.MINUTE, 22);
                stopNum = 2;
                break;
            case 44:
            case 45:
                check.set(Calendar.MINUTE, 44);
                stopNum = 2;
                break;

            //times for stop 3
            case 6:
                check.set(Calendar.MINUTE, 6);
                stopNum = 3;
                break;
            case 26:
                check.set(Calendar.MINUTE, 26);
                stopNum = 3;
                break;
            case 46:
                check.set(Calendar.MINUTE, 46);
                stopNum = 3;
                break;

            //times for stop 4
            case 7:
            case 8:
            case 9:
            case 10:
                check.set(Calendar.MINUTE, 6);
                stopNum = 4;
                break;
            case 27:
            case 28:
            case 29:
            case 30:
                check.set(Calendar.MINUTE, 27);
                stopNum = 4;
                break;
            case 47:
            case 48:
            case 49:
            case 50:
                check.set(Calendar.MINUTE, 47);
                stopNum = 4;
                break;

            //times for stop 5
            case 11:
            case 12:
            case 13:
                check.set(Calendar.MINUTE, 11);
                stopNum = 5;
                break;
            case 31:
            case 32:
            case 33:
                check.set(Calendar.MINUTE, 31);
                stopNum = 5;
                break;
            case 51:
            case 52:
            case 53:
                check.set(Calendar.MINUTE, 51);
                stopNum = 5;
                break;

            //times for stop 6
            case 14:
                check.set(Calendar.MINUTE, 14);
                stopNum = 6;
                break;
            case 34:
                check.set(Calendar.MINUTE, 34);
                stopNum = 6;
                break;
            case 54:
                check.set(Calendar.MINUTE, 54);
                stopNum = 6;
                break;

            //times for stop 7
            case 15:
                check.set(Calendar.MINUTE, 15);
                stopNum = 15;
                break;
            case 35:
                check.set(Calendar.MINUTE, 35);
                stopNum = 35;
                break;
            case 55:
                check.set(Calendar.MINUTE, 55);
                stopNum = 55;
                break;

            //times for stop 8
            case 16:
                check.set(Calendar.MINUTE, 16);
                stopNum = 16;
                break;
            case 36:
                check.set(Calendar.MINUTE, 36);
                stopNum = 36;
                break;
            case 56:
                check.set(Calendar.MINUTE, 56);
                stopNum = 56;
                break;

            //times for stop 9
            case 17:
            case 18:
            case 19:
                check.set(Calendar.MINUTE, 17);
                stopNum = 9;
                break;
            case 37:
            case 38:
            case 39:
                check.set(Calendar.MINUTE, 37);
                stopNum = 9;
                break;
            case 57:
            case 58:
            default:
                check.set(Calendar.MINUTE, 57);
                stopNum = 9;
                break;

        }

        int i = stopNum;
        do {
            i++;
            if(i > 9)
                i = 0;
            LinearLayout stop = (LinearLayout)capsule.getChildAt(i);
            TextView tx = (TextView)stop.getChildAt(2);

            if(i == stopNum) {
                layout = (LinearLayout)capsule.getChildAt(i);
                layout.setBackgroundColor(Color.parseColor("#81DAF5"));
                TextView tx1 = (TextView)layout.getChildAt(0);
                tx1.setTypeface(null, Typeface.BOLD);
            }

            if(i == 0){
                check.add(Calendar.MINUTE, 3);
                tx.setText("Expected to arrive at: " + sdf.format(check.getTime()));
            }
            if(i == 1){
                check.add(Calendar.MINUTE, 2);
                tx.setText("Expected to arrive at: " + sdf.format(check.getTime()));
            }
            if(i == 2){
                check.add(Calendar.MINUTE, 2);
                tx.setText("Expected to arrive at: " + sdf.format(check.getTime()));
            }
            if(i == 3){
                check.add(Calendar.MINUTE, 2);
                tx.setText("Expected to arrive at: " + sdf.format(check.getTime()));
            }
            if(i == 4){
                check.add(Calendar.MINUTE, 1);
                tx.setText("Expected to arrive at: " + sdf.format(check.getTime()));
            }
            if(i == 5){
                check.add(Calendar.MINUTE, 4);
                tx.setText("Expected to arrive at: " + sdf.format(check.getTime()));
            }
            if(i == 6){
                check.add(Calendar.MINUTE, 3);
                tx.setText("Expected to arrive at: " + sdf.format(check.getTime()));
            }
            if(i == 7){
                check.add(Calendar.MINUTE, 1);
                tx.setText("Expected to arrive at: " + sdf.format(check.getTime()));
            }
            if(i == 8){
                check.add(Calendar.MINUTE, 1);
                tx.setText("Expected to arrive at: " + sdf.format(check.getTime()));
            }
            if(i == 9){
                check.add(Calendar.MINUTE, 1);
                tx.setText("Expected to arrive at: " + sdf.format(check.getTime()));
            }
        } while(i != stopNum);




    }
}
