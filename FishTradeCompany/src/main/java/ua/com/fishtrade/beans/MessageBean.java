package ua.com.fishtrade.beans;

import java.io.Serializable;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

@Named("msgs")
@Scope("session")
public class MessageBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	private final String firstNameRequired = "Employee first name field can't be empty";
	private final String lastNameRequired= "Employee last name field can't be empty";
	private final String cityRequired = "Employee city field can't be empty";
	private final String streetRequired = "Employee street field can't be empty";
	private final String buildingNumRequired = "Employee building number can't be empty";
	private final String positionRequired = "Employee position can't be empty";
	private final String phoneNumRequired = "Employee phone num field can't be empty";
	private final String loginReguired = "Employee login can't be empty";
	private final String passwordReguired = "Employee default password can't be empty";
	private final String statusReguired = "Employee status can't be empty";
	
	public MessageBean(){   }

	public String getFirstNameRequired() {
		return firstNameRequired;
	}

	public String getLastNameRequired() {
		return lastNameRequired;
	}

	public String getCityRequired() {
		return cityRequired;
	}

	public String getStreetRequired() {
		return streetRequired;
	}

	public String getBuildingNumRequired() {
		return buildingNumRequired;
	}

	public String getPositionRequired() {
		return positionRequired;
	}

	public String getPhoneNumRequired() {
		return phoneNumRequired;
	}

	public String getLoginReguired() {
		return loginReguired;
	}

	public String getPasswordReguired() {
		return passwordReguired;
	}

	public String getStatusReguired() {
		return statusReguired;
	}

	
}