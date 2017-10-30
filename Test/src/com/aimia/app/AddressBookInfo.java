package com.aimia.app;

public class AddressBookInfo {
	private String name;
	private String dob;
	private String gender;
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDob() {
		return dob;
	}

	public int getAge() {
		return age;
	}


	public void setDob(String dob) {
		this.dob = dob;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Override
	public String toString() {
		return "AddressBookInfo [name=" + name + ", dob=" + dob + ", gender=" + gender + ", age=" + age + "]";
	}


}