package com.example.edirprofile;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private final String CHANNEL_ID = "personal notification";
    private final int NOTIFICATION_ID = 001;
    private static final int PICK_IMAGE = 123;
    private static final int REQUEST_CAMERA = 456;
    ImageView imageView;
    TextView textview4, textview6, textview14, textview;
    boolean save = false;
    Button upload;
    Spinner spinner, spinner2;
    Toast toast;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textview = findViewById(R.id.textView);
        textview4 = findViewById(R.id.textView4);
        textview6 = findViewById(R.id.textView6);
        textview14 = findViewById(R.id.textView14);
        imageView = findViewById(R.id.photo);

        upload = findViewById(R.id.upload);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        spinner = findViewById(R.id.spinner);
        String[] subject = {"Blank","Mathematics", "Biology", "Chemistry", "Physics", "Others"};
        ArrayAdapter<String> subjectList = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                subject);spinner.setAdapter(subjectList);

        spinner2 = findViewById(R.id.spinner2);
        String[] age = {"Blank","Under 18", "19-25", "26-30", "31-35", "36-40", "41-45", "46-50", "51-55", "56-60", "Over 60"};
        ArrayAdapter<String> ageList = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                age);spinner2.setAdapter(ageList);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void on_button_save_click (View view) {

        check();
    }

    public void selectImage() {
        final CharSequence[] items = {"Camera", "Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add image");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if(items[i].equals("Camera"))
                {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 0);
                }
                else if(items[i].equals("Gallery"))
                {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Pick an image"), PICK_IMAGE);
                }
                else if(items[i].equals("Cancel"))
                {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE && data != null && data.getData() != null)
        {
            Uri imageUri = data.getData();
            try{
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                imageView.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
               e.printStackTrace();
            }
        }
        else if(requestCode == 0)
        {
            bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
        }
    }

    public void check() {
        String FName = textview4.getText().toString();
        String LName = textview6.getText().toString();
        String MobNum = textview14.getText().toString();
        String Fees = textview.getText().toString();
        String age = spinner2.getSelectedItem().toString();
        String subject = spinner.getSelectedItem().toString();

        if(FName.length() > 30 || FName.length() <= 0)
        {
            textview4.setError("Please enter your first name which at most 30 characters");
        }
        else if(LName.length() > 30 || LName.length() <= 0)
        {
            textview6.setError("Please enter your last name which at most 30 characters");
        }
        else if(age == "Blank")
        {
            ((TextView)spinner2.getSelectedView()).setError("Please choose your range of age");
        }
        else if(subject == "Blank")
        {
            ((TextView)spinner.getSelectedView()).setError("Please choose your teaching subject");
        }
        else if(MobNum.length() > 11 || MobNum.length() <= 0)
        {
            textview14.setError("Please enter valid phone number format");
        }
        else if(Fees.length() <= 0)
        {
            textview.setError("Please enter the fee of a tutorial.");
        }
        else
        {
            savedata();
            toast = Toast.makeText(this, "Saved", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    public void updateData() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        String FName = textview4.getText().toString();
        String LName = textview6.getText().toString();
        String MobNum = textview14.getText().toString();
        String subject = spinner.getSelectedItem().toString();
        String age = spinner2.getSelectedItem().toString();
        String bmp = imageView.getImageMatrix().toString();
        String id = "";

        values.put(Firstname, FName);
        values.put(Surname, LName);
        values.put(PhoneNo, MobNum);
        values.put(TeachSub, subject);
        values.put(Profile_Picture, bmp);
        db.update(TABLE_TUTPROFILE,values,KEY_ID + "=" + id,null);
        //I need to know the id to update the data in correct row.
        //So this code is not test yet
    }
}
