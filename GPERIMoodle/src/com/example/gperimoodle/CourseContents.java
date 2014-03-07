package com.example.gperimoodle;

import com.example.gperimoodle.R.id;

import android.R.string;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.EditText;

public class CourseContents extends Activity {
	
	EditText result;
	

	
	public void setContents()
	{
		
		result.setText(getIntent().getStringExtra("courseId"));
		
	}
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		result=(EditText) findViewById(id.result);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_course_contents);
		
		
		setContents();
		
		
	}
	
	

}
