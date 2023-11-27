// Created by: Ethan Tang


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
 * This class takes a String input of a password and salt and generates a SHA-1 hash of
 * the password.
 */

public class Encrypter
{
	/* 
	 * Generates a SHA-1 hash of password and salt and returns it as hex-encoded string.
	 */
	
    public static String encryptPassword(String password, String salt) 
    {
        final String algorithm = "SHA-1"; // type of encryption algorithm
        try 
        {
        	// Encrypting the password + salt.
            MessageDigest digester = MessageDigest.getInstance(algorithm);
            byte[] saltedPassword = (password + salt).getBytes();
            byte[] hash = digester.digest(saltedPassword);
            return String.format("%040x", new BigInteger(1, hash));
            
        } 
        catch (NoSuchAlgorithmException e) 
        {
        	// Printing out the exception if password + salt encryption is unsuccessful.
        	e.printStackTrace();
            return null;
        }
    }
} //end of class Encrypter.