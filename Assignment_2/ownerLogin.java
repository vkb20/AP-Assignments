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
public class ownerLogin implements userLogin{
    
    private Zotato zotatoApp;
    private Restaurant _restaurant;
    
    public ownerLogin(Zotato zotatoApp){
        this.zotatoApp=zotatoApp;
    }
    
    public ArrayList<Restaurant> getRestaurantList(){
        return zotatoApp.getRestaurantList();
    }
    
    public Restaurant getRestaurant(){
        return this._restaurant;
    }
    
    @Override
    public void chooseRestaurant(int res) {
        _restaurant = zotatoApp.getRestaurantList().get(res-1);
    }
    
    @Override
    public void printRewards() {
        System.out.println("Restaurant total reward points " + _restaurant.getRewardPoints());
    }
    
    public void discountOverall(int discount){
        _restaurant.setDiscount(discount);
    }
    
    public void addItem(Food foodItem){
        _restaurant.addFood(foodItem);
    }
    
    public void editRestaurantFoodItem(int ID, int attributeNum, String modify){
        Food foodItem = _restaurant.getFoodList().get(ID-1);
        if(attributeNum==1){
            foodItem.setFoodName(modify);
        }
        else if(attributeNum==2){
            foodItem.setItemPrice(Integer.parseInt(modify));
        }
        else if(attributeNum==3){
            foodItem.setQuantity(Integer.parseInt(modify));
        }
        else if(attributeNum==4){
            foodItem.setCategory(modify);
        }
        else if(attributeNum==5){
            foodItem.setOffer(Integer.parseInt(modify));
        }
    }
    
    
    
}
