package com.example.androidquizapplication;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class ResultActivity extends Activity {
    TextView resultView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		resultView = (TextView)findViewById(R.id.result_textView);
		Intent intent = getIntent();
		int questionNumber = intent.getExtras().getInt("questionNumber");
		int text = intent.getExtras().getInt("score");
		resultView.setText("Your have Scored "+text+" Out of "+questionNumber);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.result, menu);
		return true;
	}

}
