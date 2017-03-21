package com.example.contactmanager;

import java.util.ArrayList;

import com.example.questionbank.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import android.widget.Button;
import android.widget.ListView;



public class MainActivity extends Activity {
	ListView list;
	Button addUser;
	ArrayList<Contact> contacts_List;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		list = (ListView)findViewById(R.id.listView1);
		
		/*
		
		contacts_List = new ArryaList<Contact>();
		contact_List.add(new Contact("hari","hari@codeinstruct.com","765757566"));
		contact_List.add(new Contact("hari","hari@codeinstruct.com","765757566"));
		contact_List.add(new Contact("hari","hari@codeinstruct.com","765757566"));
		contact_List.add(new Contact("hari","hari@codeinstruct.com","765757566"));
		contact_List.add(new Contact("hari","hari@codeinstruct.com","765757566"));
		PhoneBookdAdapter adapter = new PhoneBookAdapter(getApplicationContect(),R.layout.list_user_detail,contacts_list);
		list.setAdapter(adapter);
		
		
		
		
		
		*/
		
		addUser = (Button)findViewById(R.id.addUser_Button);
		contacts_List = getUsers();
		
		
		
		PhoneBookAdapter adapter = new PhoneBookAdapter(getApplicationContext(),R.layout.list_user_detail,contacts_List);
		list.setAdapter(adapter);
		
		
		
		
		OnItemClickListener listener = new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				Contact contact = contacts_List.get(position);
				Intent intent = new Intent(getApplicationContext(),ViewContactActivity.class);
				intent.putExtra("id",contact.getId());
				intent.putExtra("name",contact.getUsername());
				intent.putExtra("email",contact.getEmailid());
				intent.putExtra("phoneno",contact.getPhoneNumber());
				startActivity(intent);
			}
		};
		list.setOnItemClickListener(listener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void addUser(View v){
		Intent intent = new Intent(getApplicationContext(),AddUserActivity.class);
		startActivity(intent);
		
	}
	public ArrayList<Contact> getUsers(){
		DbHandler db = new DbHandler(getApplicationContext());
		ArrayList<Contact> list= db.getContacts();
		return list;
	}

}
