package com.example.edirprofile;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.MediaStore;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.spinner);
        String[] subject = {"Blank","Mathematics", "Biology"};
        ArrayAdapter<String> subjectList = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                subject);spinner.setAdapter(subjectList);

        Spinner spinner2 = findViewById(R.id.spinner2);
        String[] age = {"blank","under 18", "19-25", "26-30", "31-35", "36-40", "41-45", "46-50", "51-55", "56-60", "over 60"};
        ArrayAdapter<String> ageList = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                age);spinner2.setAdapter(ageList);

        Intent i = new Intent(Intent.ACTION_PICK, null);
        i.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        //startActivityForResult(i, PHOTO_REQUEST_GALLERY);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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

    public void on_button_save_click (View view) {

        Toast toast = Toast.makeText(this, "Saved", Toast.LENGTH_SHORT);
        toast.show();

    }

    public void on_button_image_click (View view) {


    }


}
