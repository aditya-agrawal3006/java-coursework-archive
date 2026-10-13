package mediaRentalManager;

import java.util.ArrayList;
import java.util.Collections;

public class MediaRentalManager implements MediaRentalManagerInt{

	private ArrayList<Customer> customers;
    private ArrayList<Media> media;
    private int limitedPlanLimit = 2;
    
    // Constructs object and initializes the media lists
    public MediaRentalManager() {
        customers = new ArrayList<>();
        media = new ArrayList<>();
    }
    
    // Add a customer to the system
	@Override
	public void addCustomer(String name, String address, String plan) {
		customers.add(new Customer(name, address, plan));
	}

	// Add a movie to the system
	@Override
	public void addMovie(String title, int copiesAvailable, String rating) {
		media.add(new Movie(title, copiesAvailable, rating));		
	}

	// Add a album to the system
	@Override
	public void addAlbum(String title, int copiesAvailable, String artist,
			String songs) {
        media.add(new Album(title, copiesAvailable, artist, songs));		
	}

	// Set the limit for LIMITED plans
	@Override
	public void setLimitedPlanLimit(int value) {
        this.limitedPlanLimit = value;		
	}

	// Get all customer info (sorted by name)
	@Override
	public String getAllCustomersInfo() {
		Collections.sort(customers, (a, b)->a.getName().compareTo(b.getName()));
        StringBuilder sb = new StringBuilder("***** Customers' Information *****\n");
        for (Customer customer : customers) {
            sb.append(customer).append("\n");
        }
        return sb.toString();
	}

	// Get all media information (sorted by title)
    @Override
    public String getAllMediaInfo() {
        Collections.sort(media, (a, b) -> a.getTitle().compareTo(b.getTitle()));
        StringBuilder sb = new StringBuilder("***** Media Information *****\n");
        for (Media m : media) {
            sb.append(m);
        }
        return sb.toString();
    }

    // Add a media title to a customer's request queue
    @Override
    public boolean addToQueue(String customerName, String mediaTitle) {
    	
        for (Customer customer : customers) {
            if (customer.getName().equals(customerName)) {
            	if (customer.getRequested().contains(mediaTitle)) {
                    return false; // Media already in queue
                }
                customer.addToQueue(mediaTitle);
                return true;
            }
        }
        return false;
    }

    // Remove a media title from a customer's request queue
    @Override
    public boolean removeFromQueue(String customerName, String mediaTitle) {
        for (Customer customer : customers) {
            if (customer.getName().equals(customerName)) {
                customer.removeFromQueue(mediaTitle);
                return true;
            }
        }
        return false;
    }

    // Process customer requests to rent media
    @Override
    public String processRequests() {
        StringBuilder result = new StringBuilder();
        
        // Sort the list of customers by their names in alphabetical order
        Collections.sort(customers,(a, b)-> a.getName().compareTo(b.getName()));
        
        for (Customer customer : customers) {
        	// Determine the rental limit based on the customer's plan
            int limit = customer.getPlan().equals("LIMITED") ? 
            		    limitedPlanLimit : Integer.MAX_VALUE;
            int index = 0;
            
            // Continue processing while the customer can rent more media and 
            // there are requests left
            while (customer.getRented().size() < limit && 
            		index < customer.getRequested().size()) {
            	
                String mediaTitle = customer.getRequested().get(index);
                boolean rentedMedia = false;
                
                // Go through the list of all media items to find the requested one
                for (Media m : media) {
                	
                    if (m.getTitle().equals(mediaTitle) && 
                    							m.getCopiesAvailable() > 0) {
                    	
                    	m.rentCopy();
                    	
                        customer.getRented().add(mediaTitle);
                        
                        result.append("Sending ").append(mediaTitle)
                        .append(" to ").append(customer.getName()).append("\n");
                        
                        customer.getRequested().remove(index);
                        rentedMedia = true;
                        break;
                    }
                    
                    
                }
                
                if(!rentedMedia) {
                	index++;
                }
            }
        }
        return result.toString();
    }

    // Handle media return
    @Override
    public boolean returnMedia(String customerName, String mediaTitle) {
        for (Customer customer : customers) {
        	// Check if the current customer's name matches the provided name
            if (customer.getName().equals(customerName)) {
            	
                if (customer.getRented().remove(mediaTitle)) {
                	
                    for (Media m : media) {
                    	
                        if (m.getTitle().equals(mediaTitle)) {
                        	// Increment the number of copies available for the 
                        	// media and return true
                        	m.returnCopy();
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    // Search media based on criteria
    @Override
    public ArrayList<String> searchMedia(String title, String rating, 
    											String artist, String songs) {
    	ArrayList<String> result = new ArrayList<>();
        for (Media m : media) {
            boolean matches = true;
            // Check title if specified
            if (title != null && !m.getTitle().equals(title)) {
                matches = false;
            }
            
            if (m instanceof Movie) {
                Movie movie = (Movie) m;
                // If rating is specified, check rating
                if (rating != null && !movie.getRating().equals(rating)) {
                    matches = false;
                }
                // Exclude Movies because they don't have artist or songs
                if (artist != null || songs != null) {
                    matches = false;
                }
            } else if (m instanceof Album) {
                Album album = (Album) m;
                // Exclude Albums because they don't have ratings 
                if (rating != null) {
                    matches = false;
                }
                // Check artist if specified
                if (artist != null && !album.getArtist().equals(artist)) {
                    matches = false;
                }
                // Check songs if specified
                if (songs != null && !album.getSongs().contains(songs)) {
                    matches = false;
                }
            }
            
            if (matches) {
                result.add(m.getTitle());
            }
        }
        Collections.sort(result);
        return result;
    }
	
}
