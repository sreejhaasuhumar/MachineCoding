package entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import enums.OrderStatus;

public class Orders {

	Integer orderId;
	LocalDateTime date;
	Double billAmount;
	Customer customer;
	List<FoodItems> foodItems;
	Restaurant restaurant;
	SwiggyDelivery deliveryPerson;
	OrderStatus status;
	
	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Orders(Customer customer, List<FoodItems> foodItems, Restaurant restaurant) {
		super();
		this.customer = customer;
		this.foodItems = foodItems;
		this.restaurant = restaurant;
	}
	
	public Orders()
	{
		
		
	}
	
	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime localDateTime) {
		this.date = localDateTime;
	}

	public Double getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(Double billAmount) {
		this.billAmount = billAmount;
	}

	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public List<FoodItems> getFoodItems() {
		return foodItems;
	}
	public void setFoodItems(List<FoodItems> foodItems) {
		this.foodItems = foodItems;
	}
	public Restaurant getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	public SwiggyDelivery getDeliveryPerson() {
		return deliveryPerson;
	}
	public void setDeliveryPerson(SwiggyDelivery deliveryPerson) {
		this.deliveryPerson = deliveryPerson;
	}
}
