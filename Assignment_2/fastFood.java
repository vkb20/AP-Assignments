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
public class fastFood extends Restaurant{
    private float rewardPoints=0;
    private int discount;
    
    public fastFood(String restaurantName, String address, int numOfOrders) {
        super(restaurantName, address, numOfOrders);
    }
    
    @Override
    public void setDiscount(int discount){
        this.discount = discount;
    }
    
    @Override
    public int getDiscount(){
        return this.discount;
    }
    
    @Override
    public float getRewardPoints(){
        return this.rewardPoints;
    }
    
    @Override
    public void setRewardPoints(float reward){
        this.rewardPoints = reward;
    }

    @Override
    public float reward(float amount){
        float rewardPoint = (int)(amount/150);
        return 10*rewardPoint;
    }
    
    
}
