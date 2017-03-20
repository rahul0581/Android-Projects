package com.example.androidquizapplication;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;

import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends Activity {
	TextView question_text;
	RadioButton option1;
	RadioButton option2;
	RadioButton option3;
	RadioButton option4;
	Button nextButton;
	Button answerButton;
	Button previousButton;
	JSONArray array;
	int questionNumber = 0;
	int result = 0;
	HashMap<Integer,Integer> resultMap = new HashMap<Integer,Integer>();
	
	public void initializeJSONArray(){
		JSONParser parser = new JSONParser();
		Resources res = getResources();
		InputStream inputstream = res.openRawResource(R.raw.quiz);
		InputStreamReader isr = new InputStreamReader(inputstream);
	    BufferedReader br = new BufferedReader(isr);
	    Object obj = null;
	    try{
	     obj = parser.parse(br);
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    array = (JSONArray)obj;
	    
	    
		
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz);
		initializeJSONArray();
		question_text = (TextView)findViewById(R.id.question_text);
		option1 = (RadioButton)findViewById(R.id.radio_Button1);
		option2 = (RadioButton)findViewById(R.id.radio_Button2);
		option3 = (RadioButton)findViewById(R.id.radio_Button3);
		option4 = (RadioButton)findViewById(R.id.radio_Button4);
		nextButton = (Button)findViewById(R.id.next_button);
		answerButton = (Button)findViewById(R.id.answer_button);
		previousButton = (Button)findViewById(R.id.previous_button);
		final JSONObject object = (JSONObject)array.get(0);
		question_text.setText((String)object.get("question"));
		String[] options1 = ((String)object.get("options")).split(","); 
		
		option1.setText(options1[0]);
		option2.setText(options1[1]);
		option3.setText(options1[2]);
		option4.setText(options1[3]);
		
		OnClickListener l = new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				questionNumber = questionNumber+1;
				if(questionNumber<=(array.size()-1)){
				RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup1);
				String selectedAnswerValue = null;
				JSONObject object1 = (JSONObject)array.get(questionNumber);
			    if(rg.getCheckedRadioButtonId()!=-1){
				selectedAnswerValue = ((RadioButton)findViewById(rg.getCheckedRadioButtonId())).getText().toString();
				JSONObject object2 = (JSONObject)array.get(questionNumber-1);
				if(selectedAnswerValue.equals(object2.get("answer"))){
					++result;
					resultMap.put(questionNumber-1,1);
				}
			    }
				question_text.setText((String)object1.get("question"));
				if(rg.getCheckedRadioButtonId()!=-1){
				rg.clearCheck();
				}
				String[] options1 = ((String)object1.get("options")).split(","); 
				
				option1.setText(options1[0]);
				option2.setText(options1[1]);
				option3.setText(options1[2]);
				option4.setText(options1[3]);
				
				
				}else{
					RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup1);
					String selectedAnswerValue = null;
					//Toast.makeText(getApplicationContext(),"Quiz completed And you Score is"+result,Toast.LENGTH_LONG).show();
					if(questionNumber==(array.size())&&(rg.getCheckedRadioButtonId()!=-1)){
						selectedAnswerValue = ((RadioButton)findViewById(rg.getCheckedRadioButtonId())).getText().toString();
						JSONObject object3 = (JSONObject)array.get(questionNumber-1);
						if(selectedAnswerValue.equals(object3.get("answer"))){
							result = result+1;
						}
						
					}
					//Toast.makeText(getApplicationContext(),"Your score is "+result,Toast.LENGTH_LONG).show();
					Intent intent = new Intent(getApplicationContext(),ResultActivity.class);
					intent.putExtra("score",result);
					intent.putExtra("questionNumber",questionNumber);
			    	startActivity(intent);
				}
				//questionNumber++;
			}
		};
		nextButton.setOnClickListener(l);
		OnClickListener l1 = new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				JSONObject ob  = (JSONObject)array.get(questionNumber);
				String answer = (String)ob.get("answer");
				Toast.makeText(getApplicationContext(),"Answer for this question is : "+answer,Toast.LENGTH_LONG).show();
			}
		};
		answerButton.setOnClickListener(l1);
		OnClickListener l2 = new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(questionNumber==0){
					Toast.makeText(getApplicationContext(), "This is First Question and there is no previous question", Toast.LENGTH_LONG).show();
				}else{
				questionNumber = questionNumber-1;
				if(questionNumber==1){
					result = 0;
				}
				if((resultMap.get(questionNumber)!=null)&&(result>0)){
					result = result-1;
				}
				RadioGroup rg = (RadioGroup)findViewById(R.id.radioGroup1);
				rg.clearCheck();
				JSONObject object3 = (JSONObject)array.get(questionNumber);
				String question = (String)object3.get("question");
				String options = (String)object3.get("options");
				question_text.setText(question);
				String[] optionValues = options.split(",");
				option1.setText(optionValues[0]);
				option2.setText(optionValues[1]);
				option3.setText(optionValues[2]);
				option4.setText(optionValues[3]);
				}
				
			}
		};
		previousButton.setOnClickListener(l2 );
		
		
		
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.quiz, menu);
		return true;
	}

}
