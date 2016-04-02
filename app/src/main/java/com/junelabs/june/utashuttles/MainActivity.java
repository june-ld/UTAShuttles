package com.junelabs.june.utashuttles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClick(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.mainButton:
                Toast.makeText(MainActivity.this, "going to main map", Toast.LENGTH_SHORT).show();
                break;

            case R.id.blueButton:
                intent = new Intent(MainActivity.this, blueRoute.class);
                startActivity(intent);
                break;

            case R.id.greenButton:
                intent = new Intent(MainActivity.this, greenRoute.class);
                startActivity(intent);
                break;

            case R.id.redButton:
                intent = new Intent(MainActivity.this, redRoute.class);
                startActivity(intent);
                break;

            case R.id.maxButton:
                intent = new Intent(MainActivity.this, maxSchedule.class);
                startActivity(intent);
                break;

            case R.id.infoButton:
                intent = new Intent(MainActivity.this, useful_info.class);
                startActivity(intent);
                break;

            default:
                Toast.makeText(MainActivity.this, "Another Button", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
