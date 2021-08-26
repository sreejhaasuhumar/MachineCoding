package entity;

public class Address {
    String doorNo;
    String streetName;
    String city;
	String State;
    Long pinCode;
    
    public Address(String city, Long pinCode) {
		super();
		this.city = city;
		this.pinCode = pinCode;
	}

    
    
	public Address() {
	}
	
	public Long getPinCode() {
		return pinCode;
	}
	public void setPinCode(Long pinCode) {
		this.pinCode = pinCode;
	}
	public String getDoorNo() {
		return doorNo;
	}
	public void setDoorNo(String doorNo) {
		this.doorNo = doorNo;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
    
}
