package com.example.gperimoodle;

public class Friend
{

	static int totalFriends;
	
	static String[] name,id;
	
	
	public static int getTotalFriends() {
		return totalFriends;
	}
	public static void setTotalFriends(int totalFriends) {
		Friend.totalFriends = totalFriends;
	}
	public static String[] getName() {
		return name;
	}
	public static void setName(String[] name) {
		Friend.name = name;
	}
	public static String[] getId() {
		return id;
	}
	public static void setId(String[] id) {
		Friend.id = id;
	}
	
	
}
