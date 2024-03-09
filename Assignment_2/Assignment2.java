/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author varun
 */
public class Assignment2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        Reader.init(System.in);
        
        Zotato zotatoApp = new Zotato();
        
        zotatoApp.addRestaurant(new authenticRestaurant("Shah (Authentic)", "New Delhi", 0));
        zotatoApp.addRestaurant(new Restaurant("Ravi's", "New Delhi", 0));
        zotatoApp.addRestaurant(new authenticRestaurant("The Chinese (Authentic)", "New Delhi", 0));
        zotatoApp.addRestaurant(new fastFood("Wang's (Fast Food)", "New Delhi", 0));
        zotatoApp.addRestaurant(new Restaurant("Paradise", "New Delhi", 0));
        
        zotatoApp.addCustomer(new eliteCustomer("Ram (Elite)", "New Delhi", 1000));
        zotatoApp.addCustomer(new eliteCustomer("Sam (Elite)", "New Delhi", 1000));
        zotatoApp.addCustomer(new specialCustomer("Tim (Special)", "New Delhi", 1000));
        zotatoApp.addCustomer(new Customer("Kim", "New Delhi", 1000));
        zotatoApp.addCustomer(new Customer("Jim", "New Delhi", 1000));
        
        boolean flag = true;
        
        while(flag){
            System.out.println("Welcome to Zotato:");
            System.out.println("1) Enter as Restaurant Owner");
            System.out.println("2) Enter as Customer");
            System.out.println("3) Check User Details");
            System.out.println("4) Company Account details");
            System.out.println("5) Exit");
            
            System.out.print("enter query : ");
            
            int query = Reader.nextInt();
            System.out.println();
            
            if(query==1){
                ownerLogin RestaurantOwner = new ownerLogin(zotatoApp);
                System.out.println("Choose restaurant");
                for(int i=0;i<zotatoApp.getRestaurantList().size();i++){
                    int j = i+1;
                    System.out.println(j + ") " + zotatoApp.getRestaurantList().get(i).getRestaurantName());
                }
                System.out.print("enter query : ");
                int query1 = Reader.nextInt();
                System.out.println();
                boolean flag1=true;
                while(flag1){
                    RestaurantOwner.chooseRestaurant(query1);
                    System.out.println("Welcome " + RestaurantOwner.getRestaurant().getRestaurantName());
                    System.out.println("1) Add item");
                    System.out.println("2) Edit item");
                    System.out.println("3) print rewards");
                    System.out.println("4) discount on bill value");
                    System.out.println("5) Exit");
                    
                    System.out.print("enter query : ");
                    int query2 = Reader.nextInt();
                    System.out.println();
                    
                    if(query2==1){
                        System.out.println("Enter food item details");
                        System.out.println("Food name : ");
                        String name = Reader.next();
                        System.out.println("item price : ");
                        float price = Reader.nextFloat();
                        System.out.println("item quantity : ");
                        int quantity = Reader.nextInt();
                        System.out.println("item category : ");
                        String category = Reader.next();
                        System.out.println("offer : ");
                        int offer = Reader.nextInt();
                        RestaurantOwner.addItem(new Food(name, price, quantity, category, offer));
                        System.out.println(RestaurantOwner.getRestaurant().getFoodList().get(RestaurantOwner.getRestaurant().getFoodList().size()-1).getID() + " " + name + " " + price + " " + quantity + " " + offer + " off " + category );
                       
                    }
                    
                    if(query2==2){
                        System.out.println("Choose item by code");
                        for(int i=0;i<RestaurantOwner.getRestaurant().getFoodList().size();i++){
                            Food foodItem = RestaurantOwner.getRestaurant().getFoodList().get(i);
                            System.out.println(foodItem.getID() + " " + RestaurantOwner.getRestaurant().getRestaurantName() + " - " + foodItem.getFoodName() + " " + foodItem.getItemPrice() +  " " + foodItem.getQuantity() + " " + foodItem.getOffer() + " off " + foodItem.getCategory());

                        }
                        System.out.print("enter query : ");
                        int query3 = Reader.nextInt();
                        System.out.println();
                        System.out.println("Choose an attribute to edit: ");
                        System.out.println("1) Name");
                        System.out.println("2) Price");
                        System.out.println("3) Quantity");
                        System.out.println("4) Category");
                        System.out.println("5) Offer");
                        System.out.print("enter query : ");
                        int query4 = Reader.nextInt();
                        System.out.println();
                        if(query4 == 1){
                            System.out.print("Enter the new name - ");
                        }
                        if(query4 == 2){
                            System.out.print("Enter the new price - ");
                        }
                        if(query4 == 3){
                            System.out.print("Enter the new quantity - ");
                        }
                        if(query4 == 4){
                            System.out.print("Enter the new category - ");
                        }
                        if(query4 == 5){
                            System.out.print("Enter the new offer - ");
                        }
                        String modify = Reader.next();
                        RestaurantOwner.editRestaurantFoodItem(query3, query4, modify);
                        Food foodItem = RestaurantOwner.getRestaurant().getFoodList().get(query3-1);
                        System.out.println(foodItem.getID() + " " + RestaurantOwner.getRestaurant().getRestaurantName() + " - " + foodItem.getFoodName() + " " + foodItem.getItemPrice() +  " " + foodItem.getQuantity() + " " + foodItem.getOffer() + " off " + foodItem.getCategory());
                    }
                    if(query2==3){
                        RestaurantOwner.printRewards();
                    }
                    
                    if(query2==4){
                        System.out.print("enter discount on bill value - ");
                        int discount = Reader.nextInt();
                        RestaurantOwner.discountOverall(discount);
                    }
                    
                    if(query2==5){
                        flag1=false;
                    }
                }
            }
            
            if(query==2){
                customerLogin customer = new customerLogin(zotatoApp);
                for(int i=0;i<zotatoApp.getCustomerList().size();i++){
                    int j=i+1;
                    System.out.println(j+ ". " +zotatoApp.getCustomerList().get(i).getCustomerName());
                }
                System.out.print("enter query : ");
                int query2 = Reader.nextInt()-1;
                System.out.println();
                customer.chooseCustomer(query2);
                System.out.println("Welcome " + zotatoApp.getCustomerList().get(query2).getCustomerName());
                boolean flag1 = true;
                boolean query3checked = false;
                while(flag1){
                    System.out.println("Customer Menu");
                    System.out.println("1) Select Restaurant");
                    System.out.println("2) Checkout Cart");
                    System.out.println("3) Reward won");
                    System.out.println("4) print the recent orders");
                    System.out.println("5) Exit");

                    System.out.print("enter query : ");
                    int query3 = Reader.nextInt();
                    System.out.println();
                    if(query3==1){
                        System.out.println("Choose restaurant");
                        for(int i=0;i<zotatoApp.getRestaurantList().size();i++){
                            int j = i+1;
                            System.out.println(j + ") " + zotatoApp.getRestaurantList().get(i).getRestaurantName());
                        }
                        System.out.print("enter query : ");
                        int query4 = Reader.nextInt()-1;
                        System.out.println();
                        customer.chooseRestaurant(query4);
                        for(int i=0;i<customer.getRestaurant().getFoodList().size();i++){
                            Food foodItem = customer.getRestaurant().getFoodList().get(i);
                            System.out.println(foodItem.getID() + " " + customer.getRestaurant().getRestaurantName() + " - " + foodItem.getFoodName() + " " + foodItem.getItemPrice() +  " " + foodItem.getQuantity() + " " + foodItem.getOffer() + " off " + foodItem.getCategory());
                        }
                        
                        boolean flag2 = true;
                        while(flag2){
                            System.out.println("choose item by code or choose 0 for stop adding items to cart");
                            int query5 = Reader.nextInt();
                            //query5 = 0 means stop adding to cart
                            if(query5==0){
                                flag2=false;
                            }
                            else{
                                System.out.println("Enter item quantity -");
                                int quantity = Reader.nextInt();
                                customer.addItem(query5, quantity);
                            }
                        }
                        System.out.println("items added to cart");
                    }
                    
                    if(query3==2){
                        System.out.println("items in cart");
                        for(int i=0;i<customer.getCustomer().getCustomerCart().size();i++){
                            int j = i+1;
                            Food foodItem = customer.getCustomer().getCustomerCart().get(i);
                            int quantity = customer.getCustomer().getQuantity().get(i);
                            System.out.println(foodItem.getID() + " " + customer.getRestaurant().getRestaurantName() + " - " + foodItem.getFoodName() + " " + foodItem.getItemPrice() +  " " + quantity + " " + foodItem.getOffer() + " off " + foodItem.getCategory());
                        }
                        boolean flag2=true;
                        while(flag2){
                            boolean ans = customer.foodCheckout();
                            if(ans==true){
                                flag2=false;
                            }
                            else{
                                System.out.print("choose index of item to be removed ");
                                int remove = Reader.nextInt()-1;
                                customer.removeItems(remove);
                            }
                        }
                        query3checked=true;
                    }
                    
                    if(query3==3){
                        customer.printRewards();
                    }
                    
                    if(query3==4){
                        customer.RecentOrders();
                    }
                    
                    if(query3==5){
                        if(query3checked==false){
                            System.out.println("please checkout items first, press 2 in next query");
                        }
                        else{
                            flag1=false;
                        }
                    }
                }
                
                
            }
            if(query==3){
                System.out.println("1) Customer List");
                System.out.println("2) Restaurant List");
                int choose = Reader.nextInt();
                if(choose==1){
                    for(int i=0;i<zotatoApp.getCustomerList().size();i++){
                        int j = i+1;
                        System.out.println(j + ". " + zotatoApp.getCustomerList().get(i).getCustomerName());
                    }
                    int customerNum = Reader.nextInt()-1;
                    zotatoApp.checkCustomer(customerNum);
                }
                
                else if(choose==2){
                    for(int i=0;i<zotatoApp.getCustomerList().size();i++){
                        int j=i+1;
                        System.out.println(j+". " + zotatoApp.getRestaurantList().get(i).getRestaurantName());
                    }
                    int restaurantNum = Reader.nextInt()-1;
                    zotatoApp.checkRestaurant(restaurantNum);
                }
               
            }
            
            if(query==4){
                System.out.println("Total company balance - " + zotatoApp.getCompanyBalance() + "/-");
                System.out.println("Total delivery charge collected - " + zotatoApp.getDeliveryChargeCollected() + "/-");                
            }
            
            if(query==5){
                flag=false;
            }
            
            
        }
        
        
        
    }
    
}

class Reader {
    static BufferedReader reader;
    static StringTokenizer tokenizer;

    /** call this method to initialize reader for InputStream */
    static void init(InputStream input) {
        reader = new BufferedReader(
                     new InputStreamReader(input) );
        tokenizer = new StringTokenizer("");
    }

    /** get next word */
    static String next() throws IOException {
        while ( ! tokenizer.hasMoreTokens() ) {
            //TODO add check for eof if necessary
            tokenizer = new StringTokenizer(
                   reader.readLine() );
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt( next() );
    }
	
    static double nextDouble() throws IOException {
        return Double.parseDouble( next() );
    }
    static long nextLong() throws IOException {
        return Long.parseLong( next() );
    }
    static float nextFloat() throws IOException {
        return Float.parseFloat( next() );
    }
}











