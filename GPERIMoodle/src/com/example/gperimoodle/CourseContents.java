package com.example.gperimoodle;


import com.example.gperimoodle.R.id;

import android.R.drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class CourseContents extends Activity implements AdapterView.OnItemClickListener
{
	
	ListView lstCnt;
	
	String courseId;
	
	String[] fName;
	String[] fURL;
	String[] fSize;
	String[] fType;
	
	
	
	
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
	
		switch(item.getItemId())
		{
			case R.id.about:
				startActivity(new Intent(this, About.class));
				break;
				
			case R.id.sendFeedback:
				
				Intent emailIntent = new Intent(Intent.ACTION_SEND);
				emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"gpericollege@gmail.com"});
				emailIntent.putExtra(Intent.EXTRA_SUBJECT, "GPERI Moodle App Feedback");
				emailIntent.putExtra(Intent.EXTRA_TEXT, "Write your Likes and Dislikes about the App");				
				emailIntent.setType("message/rfc822");				
				startActivity(Intent.createChooser(emailIntent, "Choose E-mail Clent"));
				
				break;
		}
		
		
		return true;
	}
	
	
	

	
	public void setContents()
	{
		
		new CreateObj().createContentObj(this, courseId);
		
		fName=Content.getFileName();
		fURL=Content.getFileURL();
		fSize=Content.getFileSize();
		fType=Content.getFileType();
		
		

		//ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,fName);
		ContentAdapter adapter=new ContentAdapter(this, fName, fSize, fType);
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



class ContentAdapter extends ArrayAdapter<String>
{
	Context context;
	String[] fName;
	String[] fSize;
	String[] fType;
	ImageView icon;
	
	ContentAdapter(Context c,String[] fName,String[] fSize,String[] fType)
	{
		super(c,R.layout.content_view,R.id.txtContentFileName,fName);
		this.context=c;
		this.fName=fName;
		this.fSize=fSize;
		this.fType=fType;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View contentRow= inflater.inflate(R.layout.content_view,parent,false);
		
		TextView _fName=(TextView)contentRow.findViewById(R.id.txtContentFileName);
		TextView _fSize=(TextView)contentRow.findViewById(R.id.txtContentFileSize);
		TextView _fType=(TextView)contentRow.findViewById(R.id.txtContentType);
		ImageView _icon=(ImageView)contentRow.findViewById(R.id.imgContentIcon);
		
		
		Integer x= Integer.parseInt(fSize[position]);
		x=x/1000;
		
		_fName.setText(fName[position]);
		_fSize.setText(x.toString()+"KB");
		_fType.setText(fType[position]);
		
		
		
		
		
		if(fName[position].contains(".ppt") || fName[position].contains(".pptx"))
		{
			_icon.setImageDrawable(context.getResources().getDrawable(R.drawable.ppt));
		}
		if(fName[position].contains(".doc") || fName[position].contains(".docx"))
		{
			_icon.setImageDrawable(context.getResources().getDrawable(R.drawable.doc));
		}
		if(fName[position].contains(".pdf"))
		{
			_icon.setImageDrawable(context.getResources().getDrawable(R.drawable.pdf));
		}
		if(fName[position].contains(".zip"))
		{
			_icon.setImageDrawable(context.getResources().getDrawable(R.drawable.zip));
		}
		if(fName[position].contains(".rar"))
		{
			_icon.setImageDrawable(context.getResources().getDrawable(R.drawable.rar));
		}
		if(fName[position].contains(".html"))
		{
			_icon.setImageDrawable(context.getResources().getDrawable(R.drawable.url));
		}
		
		
		return contentRow;
		
	}
	
}



