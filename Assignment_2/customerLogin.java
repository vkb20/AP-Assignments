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
public class customerLogin implements userLogin{
    private Zotato zotatoApp;
    private Restaurant _restaurant;
    private Customer _customer;
    
    public customerLogin(Zotato zotatoApp){
        this.zotatoApp = zotatoApp;
    }
  
    public Restaurant getRestaurant(){
        return this._restaurant;
    }
    
    public Customer getCustomer(){
        return this._customer;
    }
    
    @Override
    public void chooseRestaurant(int res) {
        _restaurant = zotatoApp.getRestaurantList().get(res);
    }
    @Override
    public void printRewards() {
        System.out.println("Reward Points " + _customer.getCurrentReward());
    }

    public void chooseCustomer(int cust){
        _customer = zotatoApp.getCustomerList().get(cust);
    }
    
    public void addItem(int ID, int quantity) {
        int index = 0;
        for(int i=0;i<_restaurant.getFoodList().size();i++){
            if(_restaurant.getFoodList().get(i).getID()==ID){
                index = i;
            }
        }
        Food foodItem = _restaurant.getFoodList().get(index);
        _customer.addToCart(foodItem);
        _customer.addQuantity(quantity);
        float price = foodItem.getItemPrice()*quantity;
        price = price - (price*(foodItem.getOffer())/100);
        _customer.addPriceToPriceList(price);
    }
    
    public boolean foodCheckout(){
        float totalPrice = _customer.getBillPrice(_restaurant);
        if(_customer.getAccountBalance()+_customer.getRewardPoints()>=totalPrice+_customer.getDeliveryCharges()){
            if(_customer.getRewardPoints()>=totalPrice+_customer.getDeliveryCharges()){
                _customer.setRewardPoints(_customer.getRewardPoints()-totalPrice+_customer.getDeliveryCharges());
            }
            else{
                float newBillPrice = totalPrice+_customer.getDeliveryCharges() - _customer.getRewardPoints();
                _customer.setRewardPoints(0);
                _customer.setAccountBalance(_customer.getAccountBalance()-newBillPrice);
            }
            _customer.setRewardPoints(_customer.getRewardPoints()+_restaurant.reward(totalPrice));
            _restaurant.setRewardPoints(_restaurant.getRewardPoints()+_restaurant.reward(totalPrice));
            zotatoApp.setCompanyBalance(zotatoApp.getCompanyBalance()+(totalPrice/100));
            _restaurant.setNumOfOrders(_restaurant.getNumOfOrders()+1);
            _customer.setCurrentReward(_restaurant.reward(totalPrice));
            float totalAmount = totalPrice+_customer.getDeliveryCharges();
            System.out.println("Delivery charges " + _customer.getDeliveryCharges() + "/-");
            System.out.println("total order amount " + totalAmount + "/-");
            ArrayList<String> itemNames = new ArrayList<>();
            ArrayList<Float> itemPrices = new ArrayList<>();
            ArrayList<Integer> itemQuantity = new ArrayList<>();
            int deliveryCharge = _customer.getDeliveryCharges();
            String resName = _restaurant.getRestaurantName();
            int totalQuantity = 0;
            while(!_customer.getCustomerCart().isEmpty()){
                Food foodItem = _customer.getCustomerCart().get(0);
                itemNames.add(foodItem.getFoodName());
                itemPrices.add(foodItem.getItemPrice());
                itemQuantity.add(_customer.getQuantity().get(0));
                totalQuantity+=_customer.getQuantity().get(0);
                _customer.getCustomerCart().remove(0);
                _customer.getQuantity().remove(0);
                _customer.getCustomerCartPrice().remove(0);
            }
            _customer.setItemName(itemNames);
            _customer.setQuantityList(itemQuantity);
            _customer.setPrice(itemPrices);
            _customer.setResName(resName);
            _customer.setDelivery(deliveryCharge);
            System.out.println(totalQuantity + " items successfully bought for INR "  + totalAmount + "/-");
            return true;
        }
        else{
            System.out.println("Insufficient balance, remove items from cart");
            return false;
        }
        
    }
    
    public void removeItems(int index){
        Food foodItem = _customer.getCustomerCart().get(index);
        System.out.println("Removed item " + foodItem.getID() + " " + _restaurant.getRestaurantName() + " " +  foodItem.getFoodName() +" " + _customer.getQuantity().get(index));
        _customer.getCustomerCart().remove(index);
        _customer.getQuantity().remove(index);
        _customer.getCustomerCartPrice().remove(index);
    }
    
    public void RecentOrders(){
        int start;
        if(_customer.getItemName().size()<=10){
            start = 0;
        }
        else{
            start = Math.abs(_customer.getItemName().size() - 10);
        }
        for(int i=start;i<_customer.getItemName().size();i++){
            int j = i+1;
            int size = _customer.getItemName().get(i).size();
            System.out.println("order " + j);
            System.out.print("Bought Items : ");
            for(int k=0;k<size;k++){
                System.out.print("item name " + _customer.getItemName().get(i).get(k) + ", " + "item quantity " + _customer.getQuantityList().get(i).get(k) + ", " + "item price " +  _customer.getPrice().get(i).get(k) + ", ");
            }
            System.out.print("from " + _customer.getResName().get(i) + " delivery charge " + _customer.getDelivery().get(i));
            System.out.println();
        }
        
    }
  
}
