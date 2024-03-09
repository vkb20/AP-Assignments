/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2;

import java.util.*;
        
/**
 *
 * @author varun
 */
public class Zotato {
   
    private ArrayList<Restaurant> restaurantList = new ArrayList<>();
    private ArrayList<Customer> customerList = new ArrayList<>();
    private float companyBalance = 0;
    private int deliveryChargeCollected = 0;
    
    public Zotato(){
        
    }
    
    public float getCompanyBalance(){
        return this.companyBalance;
    }
    
    public int getDeliveryChargeCollected(){
        return this.deliveryChargeCollected;
    }
    
    public ArrayList<Restaurant> getRestaurantList(){
        return restaurantList;
    }
    
    public ArrayList<Customer> getCustomerList(){
        return customerList;
    }
    
    public void addRestaurant(Restaurant restaurant){
        restaurantList.add(restaurant);
    }
    
    public void addCustomer(Customer customer){
        customerList.add(customer);
    }
    
    public void setCompanyBalance(float amount){
        this.companyBalance = amount;
    }
    
    public void setDeliveryChargeCollected(int delivery){
        this.deliveryChargeCollected=delivery;
    }
    
    public void checkCustomer(int customerNum){
        Customer _Customer = customerList.get(customerNum);
        System.out.println(_Customer.getCustomerName() + " " + _Customer.getCustomerAddress() + " " +_Customer.getAccountBalance() + "/-");
    }
    
    public void checkRestaurant(int ownerNum){
        Restaurant restaurant = restaurantList.get(ownerNum);
        System.out.println(restaurant.getRestaurantName() + " " + restaurant.getAddress() + " " + restaurant.getNumOfOrders());
    }
    
    
    
    
    
}
