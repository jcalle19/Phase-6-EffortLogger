// Created by: Ethan Tang

import java.security.SecureRandom;

/* 
 * Represents a single user account in EffortLogger. Contains strings which
 * represent a user's username, encrypted password, salt used to encrypt the
 * password, type of account, and recovery key.
 */

public class UserAccount 
{
    private String username;
    private String salt;
    private String encryptedPassword;
    private String type;
    private String recoveryKey;
    
    /*
     * Constructor for a UserAccount object.
     */
    public UserAccount(String username, String password, String type) 
    {
    	// If the provided username and password are valid...
        if (isValidUsername(username) && isValidPassword(password)) 
        {
        	// Create the account.
            this.username = username;
            this.salt = generateSalt();
            this.encryptedPassword = encryptPassword(password, salt);
            this.type = type;
            if (type == "admin")
            {
                this.recoveryKey = generateRecoveryKey();
            }
            // Print out a confirmation method.
            System.out.println("Account with username " + "\"" + username + "\"" + " successfully created!");
        } 
        // Otherwise...
        else 
        {
        	// Print out an error message.
            System.out.println("Account creation unsuccessful, invalid username or password.");
        }
    }
    
    /*
     * This method checks if a username is valid.
     * A username is valid only if it has a length of 7 or more characters
     * along with including at least one number.
     */
    private boolean isValidUsername(String username) 
    {
        return username.length() >= 7 && username.matches(".*\\d.*");
    }

    /*
     * This method checks if a password is valid.
     * A password is valid only if it has a length of 13 or more characters and
     * at least one number, upper-case letter, lower-case letter, and special character.
     */
    private boolean isValidPassword(String password) 
    {
        return password.length() >= 13 &&
               password.matches(".*\\d.*") &&
               password.matches(".*[a-z].*") &&
               password.matches(".*[A-Z].*") &&
               password.matches(".*[@*%^&].*");
    }

    /* 
     * This method generates a salt value for encrypting a UserAccount's password.
     */
    private String generateSalt() 
    {
        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[16];
        random.nextBytes(saltBytes);
        return new String(saltBytes);
    }
    
    /* 
     * This method encrypts a UserAccount's password and salt by calling the Encrypter class.
     */
    private String encryptPassword(String password, String salt) 
    {
        return Encrypter.encryptPassword(password, salt);
    }
    
    /* 
     * This method generates a 20 digit recovery key for admin accounts.
     */
    private String generateRecoveryKey() 
    {
        SecureRandom random = new SecureRandom();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder recoveryKey = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            int index = random.nextInt(characters.length());
            recoveryKey.append(characters.charAt(index));
        }
        return recoveryKey.toString();
    }

    /*
     * Getter methods for the various UserAccount fields.
     */
    public String getUsername()
    {
    	return username;
    }
    public String getTypeAccount()
    {
    	return type;
    }
    public String getEncPassword()
    {
    	return encryptedPassword;
    }
    public String getSalt()
    {
    	return salt;
    }
    public String getRecoveryKey()
    {
    	return recoveryKey;
    }
    
    /*
     *  This method prints out summary of user information, used for testing 
     *  account creation in main function.
     */
    public void printUserInfo() 
    {
        System.out.println("Username: " + username);
        System.out.println("Type: " + type);
        System.out.println("Encrypted Password: " + encryptedPassword);
        System.out.println("Salt: " + salt);
        if (recoveryKey != null) {
            System.out.println("Recovery Key: " + recoveryKey);
        }
        else
        {
        	System.out.println("No recovery key, user account is employee type.");
        }
    }
} //end of class UserAccount.

