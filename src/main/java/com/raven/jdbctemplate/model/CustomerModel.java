package com.raven.jdbctemplate.model;

public class CustomerModel {

	private int customerNumber = 0;
	private String customerName = "";
	private String phone = "";
	private String address1 = "";
	private String city = "";
	private String country = "";

	public CustomerModel() {
		super();
	}

	public CustomerModel(int customerNumber, String customerName, String phone, String address1, String city,
			String country) {
		super();
		this.customerNumber = customerNumber;
		this.customerName = customerName;
		this.phone = phone;
		this.address1 = address1;
		this.city = city;
		this.country = country;
	}

	public int getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(int customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "CustomerModel [customerNumber=" + customerNumber + ", customerName=" + customerName + ", phone=" + phone
				+ ", address1=" + address1 + ", city=" + city + ", country=" + country + "]";
	}

}
