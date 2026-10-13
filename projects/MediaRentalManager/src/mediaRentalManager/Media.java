package mediaRentalManager;

public interface Media {
	
	// Gets and returns title of media
	String getTitle();
	
	// Gets and returns the number of copies available
	int getCopiesAvailable();
	
	// Sets the number of copies available
	void setCopiesAvailable(int copiesAvailable);
	
    // Rent a copy (decrease available copies by 1)
	void rentCopy();

    // Return a copy (increase available copies by 1)
    void returnCopy();
}
