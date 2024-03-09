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
public class foodCart {
    
    private ArrayList<Food> cart;
    private ArrayList<Integer> quantity;
    private ArrayList<Food> permanentCart;
    private ArrayList<Integer> permanentQuantity;
    private ArrayList<Float> itemPriceList; 
    

    
    foodCart(){
        this.cart = new ArrayList<>();
        this.quantity = new ArrayList<>();
        this.permanentCart = new ArrayList<>();
        this.permanentQuantity = new ArrayList<>();
        this.itemPriceList = new ArrayList<>();
        
    }
    
    public ArrayList<Food> getCart(){
        return cart;
    }
    
    public ArrayList<Integer> getQuantity(){
        return this.quantity;
    }
    
    public ArrayList<Food> getPermanentCart(){
        return permanentCart;
    }
    
    public ArrayList<Integer> getPermanentQuantity(){
        return this.permanentQuantity;
    }
    
    public ArrayList<Float> getItemPriceList(){
        return this.itemPriceList;
    }
    
    public void addToCart(Food food){
        this.cart.add(food);
    }
    
    public void addQuantity(int quantity){
        this.quantity.add(quantity);
    }
    
    
    public void addToPermanentCart(Food food){
        this.permanentCart.add(food);
    }
    
    public void addPermanentQuantity(int quantity){
        this.permanentQuantity.add(quantity);
    }
    
    public void addItemPriceList(float price){
        this.itemPriceList.add(price);
    }
    
    public float TotalCartPrice(){
        float TotalPrice = 0;
        for(int i=0;i<itemPriceList.size();i++){
            TotalPrice+=itemPriceList.get(i);
        }
        return TotalPrice;
    }
    
    public float BillPrice(Restaurant _restaurant, Customer _customer){
       float totalPrice = TotalCartPrice();
       totalPrice = totalPrice - (((float)_restaurant.getDiscount()*(float)totalPrice)/(float)100);
       totalPrice = totalPrice + _restaurant.extraDiscount(totalPrice);
       totalPrice = totalPrice + _customer.customerDiscount(totalPrice);
       return totalPrice;
    }
    
    
    
}
