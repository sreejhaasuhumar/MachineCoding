package driver;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entity.Address;
import entity.Customer;
import entity.FoodItems;
import entity.IDGenerator;
import entity.Orders;
import entity.Restaurant;
import entity.Users;
import enums.OrderStatus;
import enums.PaymentType;
import enums.Rating;
import enums.UserType;
import service.FoodServiceImpl;

public class Driver {

	public static void main(String[] args) {
		
		System.out.println("Welcome to Swiggy");
		FoodServiceImpl service=new FoodServiceImpl();
		Users user=null;
		Customer customer=null;
		Restaurant restaurant=null;
		Orders order=null;
		try {
		Scanner s=new Scanner(System.in);
		String[] input=s.next().split("-");
		s.close();
		String commands =input[0];
		switch(commands)
		{
		case "endProgram":
		{
			System.exit(1);		
		}
		case "onboardUser":
		{
			String email=input[1];
			String password=input[2];
			String type=input[3];
			if(type.equalsIgnoreCase("CUSTOMER"))				
			     service.signUp(email, password, UserType.CUSTOMER);
			else
				 service.signUp(email, password, UserType.SWIGGYDELIVERY);

			break;
		}
		case "login":
		{
			String email=input[1];
			String password=input[2];
			service.signUp(email, password, UserType.CUSTOMER);
			user=service.login(email, password);
			System.out.println("User"+user.getEmail()+"has logged in successfuly");
			break;
		}
		case "registerRestaurant":
		{
			resgisterRestaurant(service);
			break;
		}
		case "showNearbyRestaurants":
		{
			resgisterRestaurant(service);
			String userName=input[1];
			List<Restaurant> list=service.showNearbyRestaurant(userName);
			System.out.println("Below are the restaurants nearby");
			for(Restaurant iterator:list)
			{
				System.out.println(iterator.getRestaurantName()+"\n");
			}
			break;
		}
		case "placeOrder":
		{
			String userName=input[1];
			String restName=input[2];
			String[] items=input[3].split(",");
			resgisterRestaurant(service);
			restaurant=new Restaurant();
			restaurant.setRestaurantName(restName);
			customer=new Customer();
			customer.setUserName(userName);
			List<FoodItems> itemsList=new ArrayList<>();
			for(String i:items)
			{
				FoodItems foodItem=new FoodItems();
				foodItem.setItemName(i);
				itemsList.add(foodItem);
			}
			order=service.placeOrder(customer, restaurant, itemsList);
			System.out.println("Please pay the amount "+order.getBillAmount()
			+" to confirm the order with the restaurant "+ order.getRestaurant().getRestaurantName());
			OrderStatus status = service.makePayment(order,PaymentType.CASH);
			System.out.println("Your order with the restaurant "+order.getRestaurant().getRestaurantName()+" got "+status);
			break;
		}
		case "getOrderList":
		{
			List<Orders> orderList=new ArrayList<>();
			String restName=input[1];
			Restaurant restaurant1=new Restaurant();
			restaurant1.setRestaurantName(restName);
			orderList=service.getListOfOrders(restaurant1);
			for(Orders i:orderList)
			{
				if(i.equals(OrderStatus.CONFIRMED))
				{
					System.out.println("Below are the orders confirmed with the restaurant "+restName);
					System.out.println("Order is "+i.getOrderId()+" time "+i.getDate());
				}
				else
				{
					System.out.println("Below are the orders cancelled with the restaurant "+restName);
					System.out.println("Order is "+i.getOrderId()+" time "+i.getDate());
				}
			}
		}
		}
		
		}
		catch(Exception e)
		{
			System.out.println("Provide proper input commands");
		}

}
	
	public static void resgisterRestaurant(FoodServiceImpl service) {
		List<FoodItems> items=new ArrayList<>();
		List<Restaurant> list=new ArrayList<>();;
		FoodItems item1=service.registerFoodItems("Briyani", 200);	
		FoodItems item2=service.registerFoodItems("Burger", 250);		
		FoodItems item3=service.registerFoodItems("Pizza", 350);		
		FoodItems item4=service.registerFoodItems("Pasta", 100);		
		FoodItems item5=service.registerFoodItems("Coffee", 40);
		items.add(item1);
		items.add(item2);
		items.add(item3);
		items.add(item4);
		items.add(item5);
		Restaurant restaurant1=service.registerRestaurant("Alessandros",items ,Rating.GOOD,new Address("coimbatore",641014L));
		Restaurant restaurant2=service.registerRestaurant("My place",items ,Rating.GOOD,new Address("coimbatore",641014L));
		Restaurant restaurant3=service.registerRestaurant("Burger ka baap",items ,Rating.GOOD,new Address("coimbatore",641014L));
		Restaurant restaurant4=service.registerRestaurant("Annapoorna",items ,Rating.GOOD,new Address("coimbatore",641014L));
		Restaurant restaurant5=service.registerRestaurant("Anandas",items ,Rating.GOOD,new Address("coimbatore",641014L));
		list.add(restaurant1);
		list.add(restaurant2);
		list.add(restaurant3);
		list.add(restaurant4);
		list.add(restaurant5);
		System.out.println("Below are the Restaurants registered");

		for(Restaurant iterator:list)
		{
			System.out.println(iterator.getRestaurantName()+"\n");
		}
	}
}
