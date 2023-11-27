// Created by: Ethan Tang


// This class contains code that is part of the main function called by our group prototype test.
public class LoginPortalMain {
	public static void main(String[] args)
    {
		// Testing password encryption to ensure different salt values produce
		// a different hash value.
    	String password = "Password123456789@";
    	String salt1 = "hash398tbridf";
    	String salt2 = "hash39843247df";
    	System.out.println("------------------------------" + "\nPassword encryption test" + "\n------------------------------");
    	System.out.println("Password: " + password);
    	System.out.println("Salt1: " + salt1);
    	System.out.println("Salt2: " + salt2);
    	
    	// These printed encrypted passwords should be different because different salt values were used before encryption.
    	System.out.println("Encrypted Password #1: " + Encrypter.encryptPassword(password, salt1));
    	System.out.println("Encrypted Password #2: " + Encrypter.encryptPassword(password, salt2));
    	
    	// Creating different user accounts to test if account username and password
    	// requirements are being met.
    	
    	// user1 and user2 should successfully be created while user3 and user4 should fail.
    	System.out.println("------------------------------" + "\nUser account creation test" + "\n------------------------------");
    	UserAccount user1 = new UserAccount("user1asdkasd", "Password32841111!@", "admin");
    	UserAccount user2 = new UserAccount("user2daniosd", "Password32841111!@", "employee");
    	UserAccount user3 = new UserAccount("user3", "Password3284!@", "employee");
    	UserAccount user4 = new UserAccount("user4fhuaisf", "password", "employee");
    	
    	// Printing out information for each UserAccount object.
    	System.out.println("------------------------------" + "\nUser 1 Information" + "\n------------------------------");
    	user1.printUserInfo();
    	System.out.println("------------------------------" + "\nUser 2 Information" + "\n------------------------------");
    	user2.printUserInfo();
    	System.out.println("------------------------------" + "\nUser 3 Information" + "\n------------------------------");
    	user3.printUserInfo();
    	System.out.println("------------------------------" + "\nUser 4 Information" + "\n------------------------------");
    	user4.printUserInfo();
    }
} //end of class LoginPortalMain.
