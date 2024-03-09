/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2;

/**
 *
 * @author varun
 */
public class Food {
    private String foodName;
    private float itemPrice;
    private int quantity;
    private String category;
    private int offer;
    private final int ID;
    private static int uniqueID;
    
    public Food(String foodName, float itemPrice, int quantity, String category, int offer){
        this.foodName = foodName;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
        this.category = category;
        this.offer = offer;
        uniqueID+=1;
        this.ID = uniqueID;
    }
    
    public String getFoodName(){
        return this.foodName;
    }
    
    public float getItemPrice(){
        return this.itemPrice;
    }
    
    public int getQuantity(){
        return this.quantity;
    }
    
    public String getCategory(){
        return this.category;
    }
    
    public int getOffer(){
        return this.offer;
    }
    
    public int getID(){
        return this.ID;
    }
    
    public void setFoodName(String foodName){
        this.foodName=foodName;
    }
    
    public void setItemPrice(float itemPrice){
        this.itemPrice=itemPrice;
    }
    
    public void setQuantity(int quantity){
        this.quantity=quantity;
    }
    
    public void setCategory(String category){
        this.category=category;
    }
    
    public void setOffer(int offer){
        this.offer=offer;
    }
    
           
}
