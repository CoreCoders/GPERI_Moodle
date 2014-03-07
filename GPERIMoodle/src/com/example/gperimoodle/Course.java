package com.example.gperimoodle;

public class Course
{
	
	static String[] id,sName,fName;
	static int totalCourses;

	public static int getTotalCourses() {
		return totalCourses;
	}

	public static void setTotalCourses(int totalCourses) {
		Course.totalCourses = totalCourses;
	}

	public static String[] getId() {
		return id;
	}

	public static void setId(String[] id) {
		Course.id = id;
	}

	public static String[] getsName() {
		return sName;
	}

	public static void setsName(String[] sName) {
		Course.sName = sName;
	}

	public static String[] getfName() {
		return fName;
	}

	public static void setfName(String[] fName) {
		Course.fName = fName;
	}
	
	

}
