package mediaRentalManager;

import java.util.ArrayList;

public class Customer implements Comparable<Customer> {
	private String name;
    private String address;
    private String plan;
    private ArrayList<String> requested;
    private ArrayList<String> rented;
    
    // Constructs a customer object name, address, and plan
    public Customer(String name, String address, String plan) {
        this.name = name;
        this.address = address;
        this.plan = plan;
        this.requested = new ArrayList<String>();
        this.rented = new ArrayList<String>();
    }
    
    // Gets and returns the customer's name
    public String getName() {
    	return name;
    }
    
    // Gets and returns the customer's address
    public String getAddress() {
    	return address;
    }
    
    // Gets and returns the customer's rental plan
    public String getPlan() {
    	return plan;
    }
    
    // Sets the customer's rental plan
    public void setPlan(String plan) {
    	this.plan = plan;
    }
    
    // Gets and returns the customer's requested media queue
    public ArrayList<String> getRequested() {
        return requested;
    }
    
    // Gets and returns the list of media titles rented by customer
    public ArrayList<String> getRented() {
        return rented;
    }
    
    // Compares customers by their names for sorting purposes
    public int compareTo(Customer other) {
        return this.name.compareTo(other.name);
    }
    
    // Add media title to queue of the customer
    public void addToQueue(String mediaTitle) {
        if (!requested.contains(mediaTitle)) {
            requested.add(mediaTitle);
        }
    }

    // remove media title from queue of the customer
    public void removeFromQueue(String mediaTitle) {
        requested.remove(mediaTitle);
    }
    
    // Returns a string representation of customer
    public String toString() {
    	return  "Name: " + name + ", Address: " + address + ", Plan: " + plan + 
                "\nRented: " + rented + "\nQueue: " + requested;

    }
    
    
}
