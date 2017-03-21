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

public class EditContactActivity extends Activity {
	EditText name_ed;
	
	EditText phone_ed;
	EditText email_ed;
	Button update_button;
	Button view_button;
	int id;
	@Override
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_contact);
		name_ed = (EditText)findViewById(R.id.edit_name_editText);
		phone_ed = (EditText)findViewById(R.id.edit_phone_editText);
		email_ed = (EditText)findViewById(R.id.edit_email_editText);
		Intent intent = getIntent();
		id = intent.getExtras().getInt("id");
		name_ed.setText(intent.getExtras().getString("name"));
		email_ed.setText(intent.getExtras().getString("email"));
		phone_ed.setText(intent.getExtras().getString("phone"));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		menu.add("delete");
		menu.add("edit");
		return super.onCreateOptionsMenu(menu);
	}
	public void updateContact(View v){
		Contact contact = new Contact();
		contact.setUsername(name_ed.getText().toString());
		contact.setEmailid(email_ed.getText().toString());
		contact.setPhoneNumber(phone_ed.getText().toString());
		contact.setId(id);
		DbHandler db = new DbHandler(getApplicationContext());
		db.updateContactDetails(contact);
		Toast.makeText(getApplicationContext(),"Contact Updated",Toast.LENGTH_LONG).show();
	}

	public void viewUsers(View view){

		Intent intent = new Intent(getApplicationContext(),MainActivity.class);
		startActivity(intent);
	}

}
