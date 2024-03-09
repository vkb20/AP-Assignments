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
public class eliteCustomer extends Customer {
    
    private final int deliveryCharge=0;
    private int customerDiscount=0;
    
    public eliteCustomer(String customerName, String address, float accountBalance) {
        super(customerName, address, accountBalance);
    }
    
    @Override
    public int getDeliveryCharges(){
        return this.deliveryCharge;
    }
    
    @Override
    public int customerDiscount(float amount){
        if(amount>200){
            return -50;
        }
        else{
            return 0;
        }
    }
    
}
