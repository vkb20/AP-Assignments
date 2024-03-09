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
public class Customer{
    
    private final String customerName;
    private String address;
    private float accountBalance;
    private float rewardPoints=0;
    private final int deliveryCharge=40;
    private foodCart customerCart;
    private boolean isRestaurant;
    private int customerDiscount=0;
    private float currentReward;
    private ArrayList<ArrayList<String>> itemName;
    private ArrayList<ArrayList<Integer>> QuantityList;
    private ArrayList<ArrayList<Float>> itemPrice;
    private ArrayList<String> restaurantName;
    private ArrayList<Integer> deliveryCharges;
    
    public Customer(String customerName, String address, float accountBalance){
        this.customerName = customerName;
        this.address = address;
        this.accountBalance = accountBalance;
        this.isRestaurant = false;
        customerCart = new foodCart();
        this.itemName= new ArrayList<ArrayList<String>>();
        this.QuantityList = new ArrayList<ArrayList<Integer>>();
        this.itemPrice = new ArrayList<ArrayList<Float>>();
        this.restaurantName = new ArrayList<String>();
        this.deliveryCharges= new ArrayList<Integer>();
    }
    
    public String getCustomerName(){
        return this.customerName;
    }
    
    public String getCustomerAddress(){
        return this.address;
    }
    
    public float getAccountBalance(){
        return this.accountBalance;
    }
    
    public float getRewardPoints(){
        return this.rewardPoints;
    }
    
    public int getDeliveryCharges(){
        return this.deliveryCharge;
    }
    
   
    public ArrayList<Food> getCustomerCart(){
        return customerCart.getCart();
    }
    
    public ArrayList<Integer> getQuantity(){
        return customerCart.getQuantity();
    }
    
    public ArrayList<Food> getPermanentCart(){
        return customerCart.getPermanentCart();
    }
    
    public ArrayList<Integer> getPermanentQuantity(){
        return customerCart.getPermanentQuantity();
    }
    
    public boolean isRestaurantAdded(){
        return this.isRestaurant;
    }
    
    public ArrayList<Float> getCustomerCartPrice(){
        return this.customerCart.getItemPriceList();
    }
  
    public void setCustomerAddress(String address){
        this.address = address;
    }
    
    public void setAccountBalance(float accountBalance){
        this.accountBalance = accountBalance;
    }
    
    public void addToCart(Food food){
        customerCart.addToCart(food);
    }
    
    public void addQuantity(int quantity){
        customerCart.addQuantity(quantity);
    }
    
    public void addToPermanentCart(Food food){
        customerCart.addToPermanentCart(food);
    }
    
    public void addPermanentQuantity(int quantity){
        customerCart.addPermanentQuantity(quantity);
    }
    
    public void addPriceToPriceList(float amount){
        customerCart.addItemPriceList(amount);
    }
    
    public void setRes(boolean flag){
        this.isRestaurant = flag;
    }
    
    public void setRewardPoints(float reward){
        this.rewardPoints = reward;
    }
    
    public float getTotalCartPrice(){
        return customerCart.TotalCartPrice();
    }
    
    public int customerDiscount(float amount){
        return 0;
    }
    
    public float getBillPrice(Restaurant _restaurant){
        return customerCart.BillPrice(_restaurant, this);
    }
    
    public float getCurrentReward(){
        return this.currentReward;
    }
    
    public void setCurrentReward(float reward){
        this.currentReward=reward;
    }
    
    public ArrayList<ArrayList<String>> getItemName(){
        return this.itemName;
    }
    public ArrayList<ArrayList<Integer>> getQuantityList(){
        return this.QuantityList;
    }
    public ArrayList<ArrayList<Float>> getPrice(){
        return this.itemPrice;
    }
    public ArrayList<String> getResName(){
        return this.restaurantName;
    }
    public ArrayList<Integer> getDelivery(){
        return this.deliveryCharges;
    }
    
    public void setItemName(ArrayList<String> list){
         this.itemName.add(list);
    }
    public void setQuantityList(ArrayList<Integer> list){
        this.QuantityList.add(list);
    }
    public void setPrice(ArrayList<Float> list){
        this.itemPrice.add(list);
    }
    public void setResName(String resName){
        this.restaurantName.add(resName);
    }
    public void setDelivery(int delivery){
        this.deliveryCharges.add(delivery);
    }
    
}
