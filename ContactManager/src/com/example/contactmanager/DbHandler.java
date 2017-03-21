package com.example.contactmanager;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;

public class DbHandler extends SQLiteOpenHelper {

	private static int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "contactManager";
	private static final String TABLE_NAME = "contacts";
	private static final String KEY_ID = "id";
	private static final String KEY_NAME = "name";
	private static final String KEY_PHONE = "phone_number";
	private static final String KEY_EMAIL = "email";
	private static final String DATABASE_CREATE = "create table "+TABLE_NAME+
												  "("+KEY_ID+" INTEGER PRIMARY KEY,"
												  	+KEY_NAME+" TEXT,"
												     +KEY_PHONE+" TEXT,"
												    +KEY_EMAIL+" TEXT)";
	
	
	ArrayList<Contact> contacts_List = new ArrayList<Contact>();
	
	
	
	public DbHandler(Context context) {
		super(context, DATABASE_NAME,null,DATABASE_VERSION);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table "+TABLE_NAME);
		onCreate(db);
		
	}
	public void addContact(Contact contact){
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_NAME,contact.getUsername());
		values.put(KEY_EMAIL,contact.getEmailid());
		values.put(KEY_PHONE,contact.getPhoneNumber());
		db.insert(TABLE_NAME, null, values);
		db.close();
	}
	
	public ArrayList<Contact> getContacts(){
		
		contacts_List.clear();
		String select_Query = "select * from "+TABLE_NAME;
		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = db.rawQuery(select_Query, null);
		if(cursor.moveToFirst()){
			do{
				Contact contact = new Contact();
				contact.setId(cursor.getInt(0));
				contact.setUsername(cursor.getString(1));
				contact.setPhoneNumber(cursor.getString(2));
				contact.setEmailid(cursor.getString(3));
				contacts_List.add(contact);
			}while(cursor.moveToNext());
		}
		
		
		cursor.close();
		db.close();
		return contacts_List;
	}
	public void deleteContact(int id){
		
		SQLiteDatabase db = getWritableDatabase();
		db.delete(TABLE_NAME,KEY_ID+"="+id,null);
	}
	public void updateContactDetails(Contact contact){
		SQLiteDatabase db = getWritableDatabase();
		
		ContentValues args = new ContentValues();
	    args.put(KEY_NAME,contact.getUsername());
	    args.put(KEY_EMAIL, contact.getEmailid());
	    args.put(KEY_PHONE,contact.getPhoneNumber());
	    db.update(TABLE_NAME, args, KEY_ID + "=" + contact.getId(), null);
	    
	}
	

}
