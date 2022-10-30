package com.example.listviewapp1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDBHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DB_NAME = "Contact.db";
    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "contacts";
    private static final String COLUMN_ID = "phoneNumber";
    private static final String COLUMN_FISRT_NAME = "firstName";
    private static final String COLUMN_LAST_NAME = "lastName";

    public MyDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String querry =
                "CREATE TABLE "+TABLE_NAME+
                        " ("+COLUMN_ID+" TEXT PRIMARY KEY,"+
                        COLUMN_FISRT_NAME+" TEXT,"+
                        COLUMN_LAST_NAME+" TEXT)";
        db.execSQL(querry);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE "+TABLE_NAME);
        onCreate(db);

    }

    public long addContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ID,contact.getTel());
        cv.put(COLUMN_FISRT_NAME,contact.getName());
        cv.put(COLUMN_LAST_NAME,contact.getLastName());

        long result = db.insert(TABLE_NAME,null,cv);
        if(result == -1){
            Toast.makeText(context, "Numéro déja exist", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Ajout avec succée", Toast.LENGTH_SHORT).show();
        }

        return result;

    }

    public Cursor getContacts(){
        String querry = "SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(querry,null);
        }
        return cursor;
    }

    public void updateContact(String phoneNumber,Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ID,contact.getTel());
        cv.put(COLUMN_FISRT_NAME,contact.getName());
        cv.put(COLUMN_LAST_NAME,contact.getLastName());

        long result = db.update(TABLE_NAME,cv,"phoneNumber=?",new String[]{phoneNumber});
        if(result == -1){
            Toast.makeText(context, "Numéro déja exist", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Mise à ajour avec succée", Toast.LENGTH_SHORT).show();
        }


    }

    public void deleteContact(String number){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,"phoneNumber=?",new String[]{number});

    }
}
