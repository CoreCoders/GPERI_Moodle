package com.example.gperimoodle;

import java.util.ArrayList;
import java.util.List;

import com.example.gperimoodle.R.id;

import android.R.string;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class CourseContents extends Activity {
	
	//EditText result;
	
	ListView lstCnt;
	
	String courseId;
	
	String[] retResult=new String[2];
	String[] retResult2=new String[2];
	
	List<String> fileName=new ArrayList<String>();
	List<String> fileURL=new ArrayList<String>();
	
	Integer count;
	
	public void setContents()
	{
		
		//result.setText(courseId.toString());
		try
		{
		
			User.setResponseXML(new Moodle(this).execute("6",User.getToken(),courseId).get());
			
			
			
			
			
			retResult=ParseXML.getSingleDetails("<KEY name=\"contents\"><MULTIPLE><SINGLE>", "</SINGLE></MULTIPLE></KEY>", User.getResponseXML());
			
			while(retResult[1].contains("<KEY name=\"contents\"><MULTIPLE><SINGLE>"))
			{
			
			//result.setText(retResult[0]);
				
				retResult=ParseXML.getSingleDetails("<KEY name=\"contents\"><MULTIPLE><SINGLE>", "</SINGLE></MULTIPLE></KEY>", User.getResponseXML());
			
				count=ParseXML.countX("<SINGLE>", retResult[0]);
				
			for(int i=0;i<count;i++)
			{
				retResult2=ParseXML.getSingleDetails("<SINGLE>", "</SINGLE>", retResult[0]);
				fileName.add(ParseXML.getValue("filename", retResult2[0]));
				fileURL.add(ParseXML.getValue("fileurl", retResult2[0]));
				
				retResult[0]=retResult2[1];
				
			}
			
			
			User.setResponseXML(retResult[1]);
			
			}
			
			String[] arr1=fileName.toArray(new String[fileName.size()]);
			String[] arr2=fileURL.toArray(new String[fileURL.size()]);
			
			Contents.setFileName(arr1);
			Contents.setFileURL(arr2);
			
			ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,Contents.getFileName());
			lstCnt.setAdapter(adapter);
			
		
		}
		catch(Exception e)
		{
			Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
		}
		
		
		
	}
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_course_contents);
		
		
		//result=(EditText) findViewById(id.result);
		
		lstCnt=(ListView) findViewById(id.lstCnt);
		
		courseId=getIntent().getStringExtra("courseId");
		
		count=Integer.valueOf(0);
		
		
		setContents();
		
		
	}
	
	

}
