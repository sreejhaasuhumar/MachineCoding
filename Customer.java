package entity;

import enums.PaymentStatus;
import enums.PaymentType;

public class Customer extends Users{
		
	Double swiggyMoney;
	PaymentStatus status;
	
	
	public PaymentStatus getStatus() {
		return status;
	}
	public void setStatus(PaymentStatus status) {
		this.status = status;
	}

	public Customer(Integer userId, String email, String password) {
		super(userId, email, password);
	}
	public Customer()
	{
		
	}


	public Double getSwiggyMoney() {
		return swiggyMoney;
	}

	public void setSwiggyMoney(Double swiggyMoney) {
		this.swiggyMoney = swiggyMoney;
	}
	
	
	

}
