package com.example.contactmanager;

import java.util.List;

import com.example.questionbank.R;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PhoneBookAdapter extends ArrayAdapter<Contact> {
	Context context;
	
	static class ViewHolder{
		TextView username;
		TextView email;
		TextView phoneno;
		ImageView image;
	}
	public PhoneBookAdapter(Context context, int resource, List<Contact> objects) {
		super(context, resource, objects);
		this.context = context;
	}
	@Override
	
	public View getView(int position, View convertView, ViewGroup parent) {
		
		
		ViewHolder holder;
		if(convertView==null){
		
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		convertView = inflater.inflate(R.layout.list_user_detail,parent,false);
		
		holder = new ViewHolder();
		
		holder.email = (TextView)convertView.findViewById(R.id.emailid_textView);
		holder.phoneno = (TextView)convertView.findViewById(R.id.phoneno_textView);
		holder.username = (TextView)convertView.findViewById(R.id.username_textView);
		holder.image = (ImageView)convertView.findViewById(R.id.image_View);
		convertView.setTag(holder);
		
		
		}else{
			holder =(ViewHolder) convertView.getTag();
		}
		Contact contact = getItem(position);
		holder.email.setText(contact.getEmailid());
		holder.phoneno.setText(contact.getPhoneNumber());
		holder.username.setText(contact.getUsername());
		holder.image.setImageResource(R.drawable.ic_launcher);
		
		return convertView;
	}
	

}
