package com.junelabs.june.utashuttles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class maxSchedule extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_max_schedule);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_max_schedule, menu);
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

    public void onScheduleClick(View view) {
        View view1;
        View Button1 = findViewById(R.id.AFButton);
        View Button2 = findViewById(R.id.FAButton);
        View Button3 = findViewById(R.id.ADButton);
        View Button4 = findViewById(R.id.DAButton);
        switch (view.getId()){
            case R.id.AFButton:
                view1 = findViewById(R.id.AtoFSchedule);
                if (view1.getVisibility() == View.GONE) {
                    view1.setVisibility(View.VISIBLE);
                    Button2.setVisibility(View.GONE);
                    Button3.setVisibility(View.GONE);
                    Button4.setVisibility(View.GONE);
                }
                else {
                    view1.setVisibility(View.GONE);
                    Button2.setVisibility(View.VISIBLE);
                    Button3.setVisibility(View.VISIBLE);
                    Button4.setVisibility(View.VISIBLE);
                }
                break;

            case R.id.FAButton:
                view1 = findViewById(R.id.FtoASchedule);
                if (view1.getVisibility() == View.GONE) {
                    view1.setVisibility(View.VISIBLE);
                    Button1.setVisibility(View.GONE);
                    Button3.setVisibility(View.GONE);
                    Button4.setVisibility(View.GONE);
                }
                else {
                    view1.setVisibility(View.GONE);
                    Button1.setVisibility(View.VISIBLE);
                    Button3.setVisibility(View.VISIBLE);
                    Button4.setVisibility(View.VISIBLE);
                }
                break;

            case R.id.ADButton:
                view1 = findViewById(R.id.AtoDSchedule);
                if (view1.getVisibility() == View.GONE) {
                    view1.setVisibility(View.VISIBLE);
                    Button2.setVisibility(View.GONE);
                    Button1.setVisibility(View.GONE);
                    Button4.setVisibility(View.GONE);
                }
                else {
                    view1.setVisibility(View.GONE);
                    Button2.setVisibility(View.VISIBLE);
                    Button1.setVisibility(View.VISIBLE);
                    Button4.setVisibility(View.VISIBLE);
                }
                break;

            case R.id.DAButton:
                view1 = findViewById(R.id.DtoASchedule);
                if (view1.getVisibility() == View.GONE) {
                    view1.setVisibility(View.VISIBLE);
                    Button2.setVisibility(View.GONE);
                    Button3.setVisibility(View.GONE);
                    Button1.setVisibility(View.GONE);
                }
                else {
                    view1.setVisibility(View.GONE);
                    Button2.setVisibility(View.VISIBLE);
                    Button3.setVisibility(View.VISIBLE);
                    Button1.setVisibility(View.VISIBLE);
                }
                break;
        }
    }
}
