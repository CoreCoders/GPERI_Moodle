package com.example.gperimoodle;


import com.example.gperimoodle.R.id;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CourseContents extends Activity implements AdapterView.OnItemClickListener
{
	
	ListView lstCnt;
	
	String courseId;
	
	String[] fName;
	String[] fURL;

	
	public void setContents()
	{
		
		new CreateObj().createContentObj(this, courseId);
		
		fName=Content.getFileName();
		fURL=Content.getFileURL();

		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,fName);
		lstCnt.setAdapter(adapter);		
		
	}
	

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_course_contents);
		
		lstCnt=(ListView) findViewById(id.lstCnt);
		lstCnt.setOnItemClickListener(this);
		
		courseId=getIntent().getStringExtra("courseId");		
		
		setContents();		
		
	}


	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int index, long arg3)
	{
		
		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(fURL[index]+"&token="+User.getToken()));
		startActivity(browserIntent);		
		
	}
	
	

}
