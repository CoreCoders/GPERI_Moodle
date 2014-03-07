package com.example.gperimoodle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;

public class MyCourses extends Activity implements AdapterView.OnItemClickListener
{
	
	ListView lstCourses;
	String[] courseFullName;
	String[] courseShortName;	
	String[] courseId;
	
	public void setCourses()
	{		
		
		courseFullName=Course.getfName();
		courseShortName=Course.getsName();
		courseId=Course.getId();
		
		//ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,courseFullName);
		CourseAdapter adapter=new CourseAdapter(this, courseFullName,courseShortName);
		lstCourses.setAdapter(adapter);
		
	}
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_courses);
		
		Resources res=getResources();
		
		courseFullName=res.getStringArray(R.array.fName);
		courseShortName=res.getStringArray(R.array.sName);
		
		lstCourses=(ListView) findViewById(R.id.lstCourses);
		
		
		
		try
		{		
			
			
			
			setCourses();
			
		}
		catch (Exception e)
		{
			
		}	
		
	}



	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int index, long arg3)
	{
		
			Intent i=new Intent(this,CourseContents.class);
			i.putExtra("courseId", courseId[index]);
			startActivity(i);
		
		
	}


}


class CourseAdapter extends ArrayAdapter<String>
{
	Context context;
	String[] fullName;
	String[] shortName;
	
	CourseAdapter(Context c,String[] fullName,String[] shortName)
	{
		super(c,R.layout.course_view,R.id.courseFullName,fullName);
		this.context=c;
		this.fullName=fullName;
		this.shortName=shortName;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View courseRow= inflater.inflate(R.layout.course_view,parent,false);
		
		TextView _fullName=(TextView)courseRow.findViewById(R.id.courseFullName);
		TextView _shortName=(TextView)courseRow.findViewById(R.id.courseShortName);
		
		_fullName.setText(fullName[position]);
		_shortName.setText(shortName[position]);
		
		return courseRow;
	}
	
}