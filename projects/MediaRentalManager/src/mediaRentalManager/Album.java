package mediaRentalManager;

public class Album implements Media{

	private String title;
    private int copiesAvailable;
    private String artist;
    private String songs;
    
    // Constructs an Album object with the parameters of title, copies
    // available, artist, and songs
    public Album(String name, int copiesAvailable, String artist, String songs){
    	title = name;
    	this.copiesAvailable = copiesAvailable;
    	this.artist = artist;
    	this.songs = songs;
    }
    
    // Gets and returns the title of the movie
 	public String getTitle() {
 		return title;
 	}
 	
 	// Gets and returns the copies available
 	public int getCopiesAvailable() {
 		return copiesAvailable;
 	}
 	
 	// Get and return the artist of the album
 	public String getArtist() {
 		return artist;
 	}
 	
 	// Get and return the songs of the album
  	public String getSongs() {
  		return songs;
  	}
 	
 	// Sets the copies available
 	public void setCopiesAvailable(int copiesAvailable) {
 		this.copiesAvailable = copiesAvailable;
 	}
 	
    // Rent a copy (decrease available copies by 1)
    public void rentCopy() {
        if (copiesAvailable > 0) {
        	copiesAvailable--;
        }
    }

    // Return a copy (increase available copies by 1)
    public void returnCopy() {
        copiesAvailable++;
    }
 	
 	// Returns a string representation of albums object
 	public String toString() {
        return "Title: " + title + ", Copies Available: " + copiesAvailable + 
        	   ", Artist: " + artist + ", Songs: " + songs + "\n";
    }
		
	
}
