package com.example.gperimoodle;

public class User
{
	
	private static String responseXML,token,fullName,profilePic,id,eMail,city,address,contact,siteName,skypeId,department,enrollNo,institute;

	public static String getToken() {
		return token;
	}

	public static void setToken(String token) {
		User.token = token;
	}

	public static String getResponseXML() {
		return responseXML;
	}

	public static void setResponseXML(String responseXML) {
		User.responseXML = responseXML;
	}

	public static String getProfilePic() {
		return profilePic;
	}

	public static void setProfilePic(String profilePic) {
		User.profilePic = profilePic;
	}

	public static String getFullName() {
		return fullName;
	}

	public static void setFullName(String fullName) {
		User.fullName = fullName.substring(fullName.indexOf('_')+1);
	}

	public static String getId() {
		return id;
	}

	public static void setId(String id) {
		User.id = id;
	}

	public static String geteMail() {
		return eMail;
	}

	public static void seteMail(String eMail) {
		User.eMail = eMail;
	}

	public static String getCity() {
		return city;
	}

	public static void setCity(String city) {
		User.city = city;
	}

	public static String getAddress() {
		return address;
	}

	public static void setAddress(String address) {
		User.address = address;
	}

	public static String getContact() {
		return contact;
	}

	public static void setContact(String contact) {
		User.contact = contact;
	}

	public static String getSiteName() {
		return siteName;
	}

	public static void setSiteName(String siteName) {
		User.siteName = siteName;
	}

	public static String getSkypeId() {
		return skypeId;
	}

	public static void setSkypeId(String skypeId) {
		User.skypeId = skypeId;
	}

	public static String getDepartment() {
		return department;
	}

	public static void setDepartment(String department) {
		User.department = department;
	}

	public static String getEnrollNo() {
		return enrollNo;
	}

	public static void setEnrollNo(String enrollNo) {
		User.enrollNo = enrollNo;
	}

	public static String getInstitute() {
		return institute;
	}

	public static void setInstitute(String institute) {
		User.institute = institute;
	}
	
	

}
