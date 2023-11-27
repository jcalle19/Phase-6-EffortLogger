import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class groupMain 
{
	public static void main(String[] args) throws IOException, InterruptedException
	{ 
		// Login Portal Prototype by Ethan
		
		// Testing password encryption to ensure different salt values produce
		// a different hash value.
		System.out.println("------------------------------" + "\nLogin Portal Prototype" + "\n------------------------------");
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
    	UserAccount user1a = new UserAccount("user1asdkasd", "Password32841111!@", "admin");
    	UserAccount user2a = new UserAccount("user2daniosd", "Password32841111!@", "employee");
    	UserAccount user3 = new UserAccount("user3", "Password3284!@", "employee");
    	UserAccount user4 = new UserAccount("user4fhuaisf", "password", "employee");
    	
    	// Printing out information for each UserAccount object.
    	System.out.println("------------------------------" + "\nUser 1 Information" + "\n------------------------------");
    	user1a.printUserInfo();
    	System.out.println("------------------------------" + "\nUser 2 Information" + "\n------------------------------");
    	user2a.printUserInfo();
    	System.out.println("------------------------------" + "\nUser 3 Information" + "\n------------------------------");
    	user3.printUserInfo();
    	System.out.println("------------------------------" + "\nUser 4 Information" + "\n------------------------------");
    	user4.printUserInfo();
    	
    	// Input Checking Prototype by Asish
    	System.out.println("------------------------------" + "\nInput Checking Prototype" + "\n------------------------------");
    	Scanner scanner = new Scanner(System.in);
        String username1 = Prototype.collectInput("Enter username: ", "^(?=[a-zA-Z])[a-zA-Z0-9]{4,}$", "Invalid username format.");
        String password1 = Prototype.collectInput("Enter password: ", "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z0-9!@#$%^&*()-_=+<>?.,;:/\"'\\[\\]{}|]{8,}$", "Invalid password format.");
        System.out.println("Username: " + username1);
        System.out.println("Password: " + password1);
        
        // Statistic Summary Prototype by Jacob
        System.out.println("------------------------------" + "\nStats Summary Prototype" + "\n------------------------------");
        Employee user1 = new Employee("user1", 1); //Employees and their information will be stored on a database to be accessed
		Employee user2 = new Employee("user2", 1);
		ArrayList<Task2> user1List = new ArrayList<Task2>();
		ArrayList<Task2> user2List = new ArrayList<Task2>();
		user1.importTaskList(user1List);
		user2.importTaskList(user2List);
		Task2 doneTask = new Task2("10:00:00", "newnewapp", "Effort Category", "Making new UI"); //Add task to list when the task is done;
		doneTask.setTimeFinished("14:00:00");
		doneTask.setDateCompleted("10/29/23");
		user1.addUserTask(doneTask);
		user2.addUserTask(doneTask);
		Task2 doneTask2 = new Task2("10:00:00", "newnewapp2", "Effort Category", "Making new UI 2"); //Add task to list when the task is done;
		doneTask2.setTimeFinished("14:00:00");
		doneTask2.setDateCompleted("10/29/23");
		user1.addUserTask(doneTask2);
		user2.addUserTask(doneTask2);
		ArrayList<Employee> teamList1 = new ArrayList<Employee>(); //Adding users of same team number to the same list
		teamList1.add(user1); 
		teamList1.add(user2); 
		Report_Generator userGen = new Report_Generator();
		userGen.generateUserReport(user1, "10_29_23");
		userGen.generateTeamReport(teamList1, "10_29_23");
		System.out.println("Stats summary created! :)");
        
        // EffortLog Backup by Srikar
		System.out.println("------------------------------" + "\nEffort Log Prototype" + "\n------------------------------");
		TaskBackUpTester tester = new TaskBackUpTester(); 
		tester.configureTasks(); 
		System.out.println("Task configuration complete! :)");
	}
}
