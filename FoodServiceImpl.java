package service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import entity.Address;
import entity.Customer;
import entity.FoodItems;
import entity.IDGenerator;
import entity.Orders;
import entity.Restaurant;
import entity.SwiggyDelivery;
import entity.Users;
import enums.DeliveryPersonAvailability;
import enums.OrderStatus;
import enums.PaymentStatus;
import enums.PaymentType;
import enums.Rating;
import enums.UserType;

public class FoodServiceImpl implements FoodService{
	
	List<Long> userIds;
	Map<String,Users> usersMap;
	Map<String,SwiggyDelivery> deliverPersonMap;
	Long i;
	Map<Integer,String> restaurantMap;
	Map<String,Restaurant> restaurantCollMap;
	Map<Integer,String> itemsMap;
	Map<String,FoodItems> itemsCollMap;
	List<FoodItems> itemsList;
	Set<Orders> orderSet;
	
	public FoodServiceImpl() {
		userIds = new ArrayList<>();
		itemsList = new ArrayList<>();
		usersMap = new HashMap<>();
		deliverPersonMap = new HashMap<>();
		restaurantMap = new HashMap<>();
		restaurantCollMap = new HashMap<>();
		itemsMap = new HashMap<>();
		orderSet=new HashSet<>();
		itemsCollMap= new HashMap<>();
		
		//created one user for testing
		Users user1=new Users();
		user1.setEmail("sreeja@gmail.com");
		user1.setUserName("sreeja");
		user1.setPhoneNumber("123");
		user1.setUserId(1);
		user1.setUserType(UserType.CUSTOMER);
		user1.setAddress(new Address("coimbatore", 641014L));
		usersMap.put("sreeja",user1);
	}

	
	@Override
	public Users login(String email,String password) throws Exception
	{		
		Users user=null;
		if(!usersMap.containsKey(email))
		{
			throw new Exception("User doesn't exists");
		}
		else
		{	
			user=usersMap.get(email);
			if(user.getPassword().equals(password))
			{
				System.out.println("Logged in successfully");
			}
		}
		return user;

	}
	
	@Override
	public void signUp(String email,String password,UserType type) throws Exception
	{		
		if(usersMap.containsKey(email))
		{
			throw new Exception("User already exists");
		}
		
		Integer userId=IDGenerator.getId();
		
		if(type.equals(UserType.CUSTOMER))
		{			
			Customer user=new Customer(userId, email, password);
			usersMap.put(email,user);

		}
		else if(type.equals(UserType.SWIGGYDELIVERY))
		{			
			SwiggyDelivery user=new SwiggyDelivery(userId, email, password);
			deliverPersonMap.put(email,user);

		}
		
				
	}



	@Override
    public Restaurant registerRestaurant(String name,List<FoodItems> items,Rating rating,Address address)
    {
    	if(restaurantMap.containsKey(name))
    	{
    		System.out.println("Restaurant already exist with given name, please give unique name\n");
            return null;
    	}
    	
    	Integer id=IDGenerator.getId();
    	restaurantMap.put(id, name);
    	Restaurant restaurant = new Restaurant();
    	restaurant.setRestaurantId(id);
    	restaurant.setRestaurantName(name);
    	restaurant.setRating(rating);     	
    	restaurant.setFoodItems(items);
    	restaurant.setAddress(address);
    	restaurantCollMap.put(name, restaurant);

    	return restaurant;
    }
	
	public FoodItems registerFoodItems(String name,double price)
	{
		if(itemsMap.containsKey(name))
    	{
    		System.out.println("Item already exist with given name, please give unique name\n");
            return null;
    	}
    	
    	Integer id=IDGenerator.getId();
    	itemsMap.put(id, name);
    	FoodItems items=new FoodItems(id,name,price);
    	itemsCollMap.put(name, items);
    	return items;

	}
	
	@Override
    public List<Restaurant> showNearbyRestaurant(String userName)
    {
		List<Restaurant> nearbyRestaurants=new ArrayList<>();
		try {
		if(usersMap.containsKey(userName))
		{
			Users user=usersMap.get(userName);
			Long pincode=user.getAddress().getPinCode();
			for(Map.Entry<String,Restaurant> iterator : restaurantCollMap.entrySet())
			{
				Long restPincode=iterator.getValue().getAddress().getPinCode();
				if(restPincode.equals(pincode))
				{
					nearbyRestaurants.add(iterator.getValue());
				}
				
			}
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		if(nearbyRestaurants==null)
    		System.out.println("There are no restaurants nearby\n");
		
		nearbyRestaurants.sort(new Comparator<Restaurant>() {

			@Override
			public int compare(Restaurant o1, Restaurant o2) {
				return o1.getRestaurantName().compareTo(o2.getRestaurantName());
			}
			
		});
		
		return nearbyRestaurants;
    }
	
	@Override
    public void registerDeliveryPerson(String email,String password) throws Exception
    {
		 signUp(email, password, UserType.SWIGGYDELIVERY);
    }

	@Override
    public Orders placeOrder(Customer customer,Restaurant restaurant,List<FoodItems> items)
    {
		Double billAmount=0.0;
		Orders order=null;
		String userName = customer.getUserName();
		String restaurantName = restaurant.getRestaurantName();
		if(usersMap.containsKey(userName) && restaurantMap.containsValue(restaurantName))
		{
			for(FoodItems i:items)
			{
				if(itemsCollMap.containsKey(i.getItemName()))
				{
					Double price=itemsCollMap.get(i.getItemName()).getItemPrice();
					billAmount=billAmount+price;
				}
				else
				{
					System.out.println("Item "+i.getItemName()+" is not available. Please choose a different item");
				}
			}
			
			order=new Orders();
			order.setOrderId(IDGenerator.getId());
			order.setDate(java.time.LocalDateTime.now());
			order.setBillAmount(billAmount);
			order.setCustomer(customer);
			order.setRestaurant(restaurant);
			order.setDeliveryPerson(getAvailableDeliveryExecutives());
			orderSet.add(order);

		}
		return order;

    }
	
	public SwiggyDelivery getAvailableDeliveryExecutives()
	{
		List<SwiggyDelivery> list=new ArrayList<>();
		if(deliverPersonMap.size()!=0)
		{
			for(Entry<String, SwiggyDelivery> entry:deliverPersonMap.entrySet())
			{
				if(entry.getValue().getAvailability().equals(DeliveryPersonAvailability.Aavailable))
				{
					list.add(entry.getValue());
				}
			}
		}
		else
		{
			SwiggyDelivery newDelivery=new SwiggyDelivery();
			newDelivery.setUserId(IDGenerator.getId());
			list.add(newDelivery);
		}		
		
		return list.get(0);
	}
	
	@Override
    public OrderStatus makePayment(Orders order,PaymentType type)
    {
		if(type.equals(PaymentType.CARD) || type.equals(PaymentType.CASH))
		{
			if(order.getBillAmount()!=0)
			{
				order.setStatus(OrderStatus.CONFIRMED);
				order.getCustomer().setStatus(PaymentStatus.Paid);
			}
			else
			{
				order.setStatus(OrderStatus.CANCELLED);
				order.getCustomer().setStatus(PaymentStatus.Cancelled);
			}
		}
		
		return order.getStatus();
    }


	@Override
    public List<Orders> getListOfOrders(Restaurant restaurant)
    {
		List<Orders> orderList=new ArrayList<>();
		try {
		if(restaurantMap.containsValue(restaurant.getRestaurantName()))
		{
			orderList.addAll(restaurant.getOrders());
		}
		
		orderList.sort(new Comparator<Orders>() {

			@Override
			public int compare(Orders o1, Orders o2) {
				// TODO Auto-generated method stub
				return o1.getDate().compareTo(o2.getDate());
				
			}
			
		});
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		return orderList;
    }


}



