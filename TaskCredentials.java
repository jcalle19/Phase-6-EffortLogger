
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskCredentials {
	 	private static final String DB_URL = "jdbc:mysql://localhost:3306/wizard_schema";
	    private static final String DB_USERNAME = "srik6724";
	    private static final String DB_PASSWORD = "28892K0shair!";
	    
	    public static boolean authenticate(String username, String password) {
	        String hashedPassword = hashPassword(password);
	        
	        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
	            String sql = "SELECT COUNT(*) FROM wizard_schema.wiz_credentials WHERE username = ? AND password = ?";
	            PreparedStatement statement = connection.prepareStatement(sql);
	            statement.setString(1, username);
	            statement.setString(2, hashedPassword);
	            ResultSet resultSet = statement.executeQuery();
	            
	            if (resultSet.next()) {
	                int count = resultSet.getInt(1);
	                System.out.println(count);
	                return count > 0;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
	        return false;
	    }
	    
	    public static String hashPassword(String password) {
	        try {
	            MessageDigest md = MessageDigest.getInstance("SHA-256");
	            byte[] hashedBytes = md.digest(password.getBytes());
	            
	            StringBuilder sb = new StringBuilder();
	            for (byte b : hashedBytes) {
	                sb.append(String.format("%02x", b));
	            }
	            
	            return sb.toString();
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        }
	        
	        return null;
	    }

	    public static String getDB_URL()
	    {
	        return DB_URL; 
	    }

	    public static String getDB_USERNAME()
	    {
	        return DB_USERNAME; 
	    }

	    public static String getDB_PASSWORD()
	    {
	        return DB_PASSWORD; 
	    }
}
