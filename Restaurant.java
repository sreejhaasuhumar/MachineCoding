package entity;

import java.util.List;
import enums.Rating;

public class Restaurant {

	Integer restaurantId;
	String restaurantName;
	Address address;
	List<FoodItems> foodItems;
	Rating rating;
	List<Orders> orders;
	
	public Restaurant() {
		
	}
	public Integer getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public List<FoodItems> getFoodItems() {
		return foodItems;
	}
	public void setFoodItems(List<FoodItems> foodItems) {
		this.foodItems = foodItems;
	}
	public Rating getRating() {
		return rating;
	}
	public void setRating(Rating rating) {
		this.rating = rating;
	}
	public List<Orders> getOrders() {
		return orders;
	}
	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}
}
