package com.example.contactmanager;

import com.example.questionbank.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddUserActivity extends Activity {
	EditText name;
	EditText phoneno;
	EditText email;
	Button addButton;
	Button viewListButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_user);
		name = (EditText)findViewById(R.id.name_editText1);
		phoneno = (EditText)findViewById(R.id.phone_editText2);
		email = (EditText)findViewById(R.id.email_editText3);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_user, menu);
		return true;
	}
	
	public void insertUser(View view){
		String username = name.getText().toString();
		String emailid = email.getText().toString();
		String phoneNumber = phoneno.getText().toString();
		Contact contact = new Contact(username, emailid, phoneNumber);
		DbHandler db = new DbHandler(getApplicationContext());
		db.addContact(contact);
		name.setText("");
		phoneno.setText("");
		email.setText("");
		Toast.makeText(getApplicationContext(), "Contact Added Successfully",Toast.LENGTH_LONG).show();
	}
	public void viewUsers(View view){

		Intent intent = new Intent(getApplicationContext(),MainActivity.class);
		startActivity(intent);
	}

}
