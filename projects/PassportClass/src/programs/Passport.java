package programs;

/**
 * This class represents a person's passport. It has three instance variables
 * representing the first, last and middle name (all are String variables). A
 * character instance variable representing a separator (to be used for
 * formatting purposes) is also part of the class. In addition, the class has a
 * StringBuffer instance variable that represents the passport stamps the person
 * has received.
 * 
 * For this class you need to define and use a private method called
 * validateAndFormat that takes a character as a parameter.
 * 
 * The class will keep track of the number of instances created by using a
 * private static field called objectCount.
 * 
 * @author CS
 *
 */
public class Passport {

	
	private String first, middle, last;
	private char separator;
	private StringBuilder stamps; 
	private static int count = 0;

	
	
	
	public Passport(String firstname, String middlename, String lastname) {
		separator = ',';
		stamps = new StringBuilder();

	    // Validate and format names
	    first = validateAndFormat(firstname);
	    if (middlename.isBlank()) {
	        middle = "";
	    } else {
	        middle = validateAndFormat(middlename);
	    }
	    last = validateAndFormat(lastname);

	    // Check if any name is invalid
	    if (first == null || last == null) {
	        return;
	    }

	    // Add to the count
	    count++;
	}

	public String toString() {
		// Create a StringBuilder for the fullName and append it
		StringBuilder fullName = new StringBuilder();
	    fullName.append(last);
	    fullName.append(separator);
	    fullName.append(first);
	    if (!this.middle.isEmpty()) {
	        fullName.append(separator);
	        fullName.append(middle);
	    }
	    return fullName.toString();
	}

	public Passport(String firstname, String lastname) {
		this(firstname, "", lastname);
	}

	public Passport() {
		this("SAMPLEFIRSTNAME", "SAMPLEMIDDLENAME", "SAMPLELASTNAME");
	}

	public Passport(Passport passport) {
		// Check if the object is null
		if (passport == null) {
	        return;
	    }
	    first = passport.first;
	    middle = passport.middle;
	    last = passport.last;
	    separator = passport.separator;
	    stamps = new StringBuilder(passport.stamps);
	    count++;
	}

	public Passport addStamp(String stamp) {
		 if (stamp != null && !stamp.isBlank()) {
			 stamps.append(stamp);
		 }
		 return this;
	}

	public StringBuffer getStamps() {
		return new StringBuffer(stamps);
	}

	public char getSeparator() {
		return separator;
	}

	public boolean setSeparator(char separator) {
		// Make sure that the separator is not an ineligible character
		if (separator == '@' || Character.isSpaceChar(separator) || 
			Character.isLetter(separator)) {
	        return false;
	    }
	    this.separator = separator;
	    return true;
	}

	public boolean equals(Object obj) {
		// Check if the instance of passport is equal to object
		if (this == obj) {
			return true;
		}
	    if (obj == null || getClass() != obj.getClass()) {
	    	return false;
	    }
	    Passport other = (Passport) obj;
	    // Return if they have the same first, last, and middle name
	    return first.equals(other.first) && middle.equals(other.middle) &&
	           last.equals(other.last);
	}

	public int compareTo(Passport passport) {
		 int lastNameComparison = this.last.compareTo(passport.last);
		 if (lastNameComparison != 0) {
	         return lastNameComparison;
		 }
		 int firstNameComparison = this.first.compareTo(passport.first);
		 if (firstNameComparison != 0) {
		     return firstNameComparison;
		 }
		 return this.middle.compareTo(passport.middle);
	}

	public static int getNumberOfPassportObjects() {
		return count;
	}

	public static void resetNumberOfPassportObjects() {
		count = 0;
	}

	public static Passport normalize(Passport passport, boolean uppercase) {
	    // If the passport object is null, return null (no processing)
	    if (passport == null) {
	        return null;
	    }

	    // Retrieve the original names
	    String firstName = passport.first;
	    String middleName = passport.middle;
	    String lastName = passport.last;

	    // If uppercase is true, convert all to uppercase
	    // If uppercase is false, apply validateAndFormat
	    if (uppercase) {
	        firstName = firstName.toUpperCase();
	        middleName = middleName.toUpperCase();
	        lastName = lastName.toUpperCase();
	    } else {
	        firstName = firstName.toLowerCase();
	        middleName = middleName.toLowerCase();
	        lastName = lastName.toLowerCase();
	    }

	    // Create a new Passport object with the normalized names
	    Passport normalPassport = new Passport(firstName, middleName, lastName);
	    
	    // Set the same separator as the original Passport
	    normalPassport.first = firstName;
	    normalPassport.middle = middleName;
	    normalPassport.last = lastName;
	    normalPassport.setSeparator(passport.getSeparator());
	    
	    // Return the new normalized Passport object
	    return normalPassport;
	}

	public boolean changeLastname(String lastname) {
		String formattedName = validateAndFormat(lastname);
	    if (formattedName != null) {
	        this.last = formattedName;
	        return true;
	    }
	    return false; // Change did not occur
	}

	/*
	 * This method will generate and return a formatted string in lowercase with the
	 * first character in uppercase. The parameter is valid if it is not null and it
	 * is not blank according to the string method isBlank(). If the parameter is
	 * invalid, the method will return null and perform no further processing. If
	 * the parameter is valid, spaces surrounding the parameter will be removed, the
	 * string will be converted to lowercase, and the first character of the string
	 * (after spaces have been removed) will be in upper case. The following methods
	 * can be helpful during the implementation of this method:
	 * Character.toUpperCase, and the string methods charAt and substring.
	 * 
	 * You can test this method by initially defining it public; once you have
	 * tested it, make it private.
	 * 
	 */
	private static String validateAndFormat(String name) {
	    if (name == null || name.isBlank()) {
	        return null;
	    }
	    name = name.trim().toLowerCase();
	    char firstChar = Character.toUpperCase(name.charAt(0));
	    return firstChar + name.substring(1);
	}
}