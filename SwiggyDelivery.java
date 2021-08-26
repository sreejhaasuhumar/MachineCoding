package entity;

import enums.DeliveryPersonAvailability;

public class SwiggyDelivery extends Users {

	DeliveryPersonAvailability availability;
	public SwiggyDelivery() {
		
	}
	public DeliveryPersonAvailability getAvailability() {
		return availability;
	}
	public void setAvailability(DeliveryPersonAvailability availability) {
		this.availability = availability;
	}
	public SwiggyDelivery(Integer userId, String email, String password) {
		super(userId, email, password);
	}

}
