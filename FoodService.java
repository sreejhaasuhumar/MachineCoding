package service;

import java.util.List;

import entity.Address;
import entity.Customer;
import entity.FoodItems;
import entity.Orders;
import entity.Restaurant;
import entity.Users;
import enums.OrderStatus;
import enums.PaymentType;
import enums.Rating;
import enums.UserType;

public interface FoodService {
	public Users login(String email,String password) throws Exception;
	public List<Restaurant> showNearbyRestaurant(String userName);
	public void signUp(String email, String password, UserType type) throws Exception;
	public Restaurant registerRestaurant(String name, List<FoodItems> items, Rating rating, Address address);
    public void registerDeliveryPerson(String email,String password) throws Exception;
	public Orders placeOrder(Customer customer, Restaurant restaurant, List<FoodItems> items);
    public List<Orders> getListOfOrders(Restaurant restaurant);
    public OrderStatus makePayment(Orders order,PaymentType type);
}
