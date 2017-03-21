package com.example.contactmanager;

import java.io.InputStream;

import com.example.questionbank.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewContactActivity extends Activity {
	TextView name_tv;
	TextView email_tv;
	TextView phone_tv;
	ImageView image;
	int id;
	String name;
	String email;
	String phoneno;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_contact);
		name_tv = (TextView)findViewById(R.id.view_name_tv);
		email_tv = (TextView)findViewById(R.id.view_email_tv);
		phone_tv = (TextView)findViewById(R.id.view_phone_tv);
		image = (ImageView)findViewById(R.drawable.ic_launcher);
		Intent intent = getIntent();
		id = intent.getExtras().getInt("id");
		name = intent.getExtras().getString("name");
		email = intent.getExtras().getString("email");
		phoneno = intent.getExtras().getString("phoneno");
		name_tv.setText(name);
		phone_tv.setText(phoneno);
		email_tv.setText(email);
		
		InputStream is = getResources().openRawResource(R.drawable.ic_launcher);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_contact, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId()==R.id.edit_item){
			edit();
		}
		if(item.getItemId()==R.id.delete_item){
			delete();
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	public void edit(){
		//Toast.makeText(getApplicationContext(),name+" "+id+" "+email+" "+phoneno,Toast.LENGTH_LONG).show();
		Intent intent = new Intent(getApplicationContext(),EditContactActivity.class);
		intent.putExtra("id",id);
		intent.putExtra("name",name);
		intent.putExtra("phone",phoneno);
		intent.putExtra("email",email);
		startActivity(intent);
	}
	public void delete(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		OnClickListener listener = new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				DbHandler db = new DbHandler(getApplicationContext());
				db.deleteContact(id);
				Toast.makeText(getApplicationContext(), "Contact Deleted",Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(getApplicationContext(),MainActivity.class);
				startActivity(intent);
				
			}
		};
		OnClickListener listener1 = new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		};
		builder.setMessage("Do you want to delete contact?").setCancelable(false).setPositiveButton("Yes", listener).setNegativeButton("No", listener1 );
		AlertDialog alert = builder.create();
		alert.setTitle("Alert");
		alert.show();
		
	}
	/*public void showDialog(View v){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
		OnClickListener listener = new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				
				
			}
		};
		OnClickListener listener1 = new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		};
		builder.setMessage("Select image from :").setCancelable(false).setPositiveButton("From Camera", listener).setNegativeButton("From gallery", listener1 );
		AlertDialog alert = builder.create();
		alert.setTitle("Alert");
		alert.show();
	}*/
	

}
