package com.example.gperimoodle;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.app.Activity;
import android.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;

public class UserFrame extends Activity implements AdapterView.OnItemClickListener
{
	
	ImageView profilePic;
	TextView fullname,city,branch,institute;
	ListView lstFunctions;
	
	
	
	
	
	@Override
	public void onBackPressed()
	{
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
	    
		alertDialogBuilder.setTitle("Logout");
 
		alertDialogBuilder
				.setMessage("Are you sure you want to Logout?")
				.setCancelable(false)
				.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {						
					
						
					UserFrame.this.finish();
						
					}
					
				
				  })
				  .setNegativeButton("No", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							
							dialog.cancel();
								
							}
							
						
						  });
 
				AlertDialog alertDialog = alertDialogBuilder.create();
 
				alertDialog.show();   
	}
	
	
	public void setUserDetails()
	{
		try
		{
			
			User.setResponseXML(new Moodle(UserFrame.this).execute("1",User.getToken()).get());
			
			User.setFullName(ParseXML.getValue("fullname", User.getResponseXML()));
			User.setProfilePic(ParseXML.getValue("userpictureurl", User.getResponseXML()));
			User.setId(ParseXML.getValue("userid", User.getResponseXML()));
			User.setSiteName(ParseXML.getValue("sitename", User.getResponseXML()));
			
			setTitle(User.getSiteName());
			
			profilePic.setImageBitmap(new GetImage().execute(User.getProfilePic()).get());
			
			fullname.setText(User.getFullName());
			
			
			User.setResponseXML(new Moodle(UserFrame.this).execute("2",User.getToken(),User.getId()).get());			
			
			User.seteMail(ParseXML.getValue("email", User.getResponseXML()));
			User.setAddress(ParseXML.getValue("address", User.getResponseXML()));
			User.setContact(ParseXML.getValue("phone1", User.getResponseXML()));
			User.setSkypeId(ParseXML.getValue("skype", User.getResponseXML()));
			User.setCity(ParseXML.getValue("city", User.getResponseXML()));	
			User.setEnrollNo(ParseXML.getValue("idnumber", User.getResponseXML()));
			User.setDepartment(ParseXML.getValue("department", User.getResponseXML()));
			User.setInstitute(ParseXML.getValue("institution", User.getResponseXML()));
			
			branch.append(User.getDepartment());
			city.append(User.getCity());
			institute.append(User.getInstitute());
			
			//pd.dismiss();
			
		}
		catch(Exception e)
		{
			
		}
	}
	
	
	String[] functions={"My Info","My Courses","Instant Messaging","Send Feedback","About"};
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_frame);
		
		//pd=ProgressDialog.show(this, "Loading...", "Please wait, Getting your data...");
		
		
		profilePic=(ImageView) findViewById(R.id.imgPic);
		fullname=(TextView) findViewById(R.id.lblFName);
		city=(TextView) findViewById(R.id.lblCity);
		branch=(TextView) findViewById(R.id.lblDep);
		institute=(TextView) findViewById(R.id.lblInst);
		
		lstFunctions=(ListView) findViewById(R.id.lstFunctions);
		
		
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,functions);
		lstFunctions.setAdapter(adapter);
		lstFunctions.setOnItemClickListener(this);
		
		
		
		setUserDetails();
		
		
		
		//setup other objects
		
		new CreateObj().createCourseObj(UserFrame.this);
		new CreateObj().createFriendObj(UserFrame.this);
		
		//
		
		
	}


	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int index, long arg3)
	{
	
		if(index==0)
		{
			
			Intent i=new Intent(this,MyInfo.class);
			startActivity(i);
			
		}
		if(index==1)
		{
			Intent i=new Intent(this,MyCourses.class);
			startActivity(i);
		}
		
		if(index==2)
		{
			Intent i=new Intent(this,InstantMessaging.class);
			startActivity(i);
		}
		if(index==3)
		{
			Intent emailIntent = new Intent(Intent.ACTION_SEND);
			emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"gpericollege@gmail.com"});
			emailIntent.putExtra(Intent.EXTRA_SUBJECT, "GPERI Moodle App Feedback");
			emailIntent.putExtra(Intent.EXTRA_TEXT, "Write your Likes and Dislikes about the App");				
			emailIntent.setType("message/rfc822");				
			startActivity(Intent.createChooser(emailIntent, "Choose E-mail Clent"));
		}
		if(index==4)
		{
			startActivity(new Intent(this, About.class));
		}
		
		
	}

}
