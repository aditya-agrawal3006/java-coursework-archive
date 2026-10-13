package mediaRentalManager;

public class Movie implements Media{
	
	private String title;
	private int copiesAvailable;
	private String rating;
	
	// Constructs a Movie object with the specified title, copies available, and
	// rating
	public Movie(String title, int copiesAvailable, String rating) {
		this.title = title;
		this.copiesAvailable = copiesAvailable;
		this.rating = rating;
	}
	
	// Gets and returns the title of the movie
	public String getTitle() {
		return title;
	}
	
	// Gets and returns the copies available
	public int getCopiesAvailable() {
		return copiesAvailable;
	}
	
	// Get and return the rating of the movie
	public String getRating() {
		return rating;
	}
	
	// Sets the copies available
	public void setCopiesAvailable(int copiesAvailable) {
		this.copiesAvailable = copiesAvailable;
	}
	
	// Rent a copy (decrease available copies by 1)
    public void rentCopy() {
        	copiesAvailable--;
        
    }

    // Return a copy (increase available copies by 1)
    public void returnCopy() {
        copiesAvailable++;
    }
	
    // Returns a string representation of movie object
	public String toString() {
        return "Title: " + title + ", Copies Available: " + copiesAvailable + 
        	   ", Rating: " + rating + "\n";
    }
	

}
