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
public class Restaurant implements Reward{
    
    private ArrayList<Food> foodList = new ArrayList<>();
    private final String restaurantName;
    private String address;
    private int numOfOrders;
    private int discount;
    private float rewardPoints=0;
    
    public Restaurant(String restaurantName, String address, int numOfOrders){
        this.restaurantName = restaurantName;
        this.address = address;
        this.numOfOrders = numOfOrders;
    }
    
    public String getRestaurantName(){
        return this.restaurantName;
    }
    
    public String getAddress(){
        return this.address;
    }
    
    public int getNumOfOrders(){
        return this.numOfOrders;
    }
    
    public ArrayList<Food> getFoodList(){
        return this.foodList;
    }
    
    public void setNumOfOrders(int newOrders){
        this.numOfOrders = newOrders;
    }
    
    public void setAddress(String address){
        this.address = address;
    }
    
    public void addFood(Food food){
        this.foodList.add(food);
    }
    
    public int getDiscount(){
        return this.discount;
    }
    
    public void setDiscount(int discount){
        this.discount = 0;
    }
    
    public float getRewardPoints(){
        return this.rewardPoints;
    }
    
    public void setRewardPoints(float reward){
        this.rewardPoints = reward;
    }

    @Override
    public float reward(float amount) {
        float rewardPoint = (int)amount/100;
        return 5*rewardPoint;
    }
    
    public int extraDiscount(float amount){
        return 0;
    }
    
}
