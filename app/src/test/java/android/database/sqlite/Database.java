
package android.database.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    private static final String DATABASE_NAME = "TutorAppDB";
    private static final String TABLE_STULOGIN = "StudentAccountTable";
    private static final String TABLE_TUTLOGIN = "TutorAccountTable";
    private static final String TABLE_TUTPROFILE = "TutorProfileTable";
    private static final String TABLE_STUPROFILE = "StudentProfileTable";
    private static final String KEY_ID = "ID";
    private static final String EmailAddress = "EmailAddress";
    private static final String Password = "Password";
    private static final String Firstname = "FirstName";
    private static final String Surname = "Surname";
    private static final String PhoneNo = "PhoneNumber";
    private static final String Country = "Country";
    private static final String County = "County";
    private static final String Town = "Town";
    private static final String Postcode = "Post Code";
    private static final String Subject = "Subject";


    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createStuLoginTable = "CREATE TABLE " + TABLE_STULOGIN + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + EmailAddress + " TEXT, " + Password + " TEXT)";
        db.execSQL(createStuLoginTable);

        String createTutLoginTable = "CREATE TABLE " + TABLE_TUTLOGIN + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + EmailAddress + " TEXT, " + Password + " TEXT)";
        db.execSQL(createTutLoginTable);

        String createTutProfileTable = "CREATE TABLE " + TABLE_TUTPROFILE + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Firstname + " TEXT, " + Surname + " TEXT, " + PhoneNo + " TEXT, " +
                Country + "TEXT, " +
                County + "TEXT, " +
                Town + "TEXT, " +
                Postcode + "TEXT, " +
                Subject + "TEXT)";
        db.execSQL(createTutLoginTable);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STULOGIN);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TUTLOGIN);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TUTPROFILE);
        onCreate(db);
    }

    public boolean addDataStuRegister(String item1, String item2) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EmailAddress, item1);
        contentValues.put(Password, item2);

        Log.d(TAG, "addData: Adding " + item1 + " and " + item2 + " to " + TABLE_STULOGIN);

        long result = db.insert(TABLE_STULOGIN, null, contentValues);

        if (result == 1) {
            return false;
        } else {
            return true;

        }

    }

    public boolean addDataTutProfile(String item1, String item2, String item3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Firstname, item1);
        contentValues.put(Surname, item2);
        contentValues.put(PhoneNo, item3);

        Log.d(TAG, "addData: Adding " + item1 + ", " + item2 + " and " + item3 + " to " + TABLE_TUTPROFILE);

        long result = db.insert(TABLE_TUTPROFILE, null, contentValues);

        if (result == 1) {
            return false;
        } else {
            return true;

        }
    }

}