package com.project.light.localstorage;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {


    TextView message;
    FloatingActionButton fab ;
    public static String MY_FILE_NAME="audio";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        message = (TextView)findViewById(R.id.textview);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        setSupportActionBar(toolbar);
        try {
            message.setText(getFromInterne());
        } catch (IOException e) {
            e.printStackTrace();
        }


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    saveInterne();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Snackbar.make(view, "Fichier créé avec succès", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }


        });
    }

    private void saveInterne() throws IOException {

            FileOutputStream outputStream= openFileOutput(MY_FILE_NAME,MODE_PRIVATE);
            String audio_name="Best music for Africa";
            outputStream.write(audio_name.getBytes());
            outputStream.close();

    }
    
    private String getFromInterne() throws IOException {
        String value = null;

        FileInputStream inputStream=openFileInput(MY_FILE_NAME);
        StringBuilder stringb= new StringBuilder();
        int content;
        while ((content=inputStream.read())!=-1){
          value = String.valueOf(stringb.append((char)content));
        }

        return value ;
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
