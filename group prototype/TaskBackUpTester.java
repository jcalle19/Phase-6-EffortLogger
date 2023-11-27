
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JTable;

public class TaskBackUpTester 
{
	
	List<Person> membersOfTeam = new ArrayList<Person>(); 
	
	{
		System.out.println("Just for some general information for how this will work, "
				+ "task information will be collected via input from user."); 
		System.out.println("Depending on the number of members inside the group, it will be "
				+ "stored securely inside database."); 
	}
	
	public boolean isNumeric(String str)
	{
		if(str == null)
		{
			return false; 
		}
		
		try {
			Double.parseDouble(str); 
			return true; 
		} catch(NumberFormatException exception)
		{
			return false; 
		}
	}
	
	public void configureTasks() throws InterruptedException, IOException
	{
		Scanner sc = new Scanner(System.in);
		TaskBox box = new TaskBox(); 
		//Create list to store name of people
		//Each person--> nameOfPerson, nameOfTask, descriptionOfTask, allottedTimeOfTask
		List<Task> tasksPerPerson = new ArrayList<Task>(); 
		List<List<Task>> tasksOfAllPeople = new ArrayList<List<Task>>(); 
		
		String nameOfPerson = null; 
		String nameOfTask = null; 
		int numberOfTasks = 0; 
		String descriptionOfTask = null; 
		int allottedTimeOfTask = 0; 
		
		boolean check = true; 
		
		System.out.println("Specify the number of people on your team."); 
		
		int length = sc.nextInt(); 
		sc.nextLine(); 
		
		while(check)
		{
			//There needs to be thorough checks for the input passed, but
			//this should be good for the time being. 
			
			for(int i = 0; i < length; i++)
			{
				System.out.println("Enter your name."); 
				nameOfPerson = sc.nextLine(); 
				System.out.println("Specify the number of tasks for this person."); 
				numberOfTasks = sc.nextInt(); 
				sc.nextLine();
				Person person = new Person(nameOfPerson, numberOfTasks); 
				membersOfTeam.add(person); 
				for(int j = 0; j < numberOfTasks; j++)
				{
					System.out.println("Enter name of task."); 
					nameOfTask = sc.nextLine(); 
					System.out.println("Enter description of task."); 
					descriptionOfTask = sc.nextLine(); 
					System.out.println("Enter allotted time of task."); 
					allottedTimeOfTask = sc.nextInt(); 
					System.out.println("Task Number " + (j+1) + " registered."); 
					sc.nextLine();
					Task task = new Task(nameOfTask, descriptionOfTask, nameOfPerson, allottedTimeOfTask); 
					System.out.println("Task " + (j+1) + " registered for person " + nameOfPerson); 
					tasksPerPerson.add(task);
				}
				
				
				if(isNumeric(nameOfPerson))
				{
					System.out.println("Not a valid name for: " + nameOfPerson); 
					check = true; 
				}
				else if(isNumeric(nameOfTask))
				{
					System.out.println("Not a valid task name for: " + nameOfTask); 
					check = true; 
				}
				else if(isNumeric(descriptionOfTask))
				{
					System.out.println("Not a valid description for: " + descriptionOfTask); 
					check = true; 
				}
				
				
				//Adding task Information for this particular iteration
				System.out.println("Tasks for " + nameOfPerson + " fully registered."); 
				
				//Adding this person's task information to our main tasksOfAllPeople List
				tasksOfAllPeople.add(tasksPerPerson); 
			}
			check = false; 
			
			//Checking to see all members have a number of tasks assigned to them, including their name
			for(Person person: membersOfTeam)
			{
				System.out.println("Team Member Name: " + person.getName()); 
				System.out.println("Number Of Tasks: " + person.getNoOfTasks()); 
				System.out.println("------------------------------------------"); 
				TaskBox.TaskNode node = new TaskBox.TaskNode(person.getName(), person.getNoOfTasks()); 
				node.tasksForPerson = searchAllPeopleTasks(tasksOfAllPeople, person.getName()); 
				box.insert(node);
			}
			
			//After tasks are entered, we will store them into a list. 
			//Checking to verify tasks are stored inside list.
			for(List<Task> taskPerPerson: tasksOfAllPeople)
			{
				for(Task tasks: tasksPerPerson)
				{
					System.out.println("Name Of Person: " + tasks.getAssignedToUser()); 
					System.out.println("Name Of Task: " + tasks.getName()); 
					System.out.println("Description Of Task: " + tasks.getDescription()); 
					System.out.println("Allotted Time Of Task: " + tasks.getDurationOfTime()); 
					System.out.println("---------------------------------------------"); 
				}
				
			}
		}
		
		System.out.println("We will now proceed to store this inside a task box.");
		Thread.sleep(1);
		System.out.println("A task box is defined to be a structure of team members " + 
		"where every team members has a set of tasks."); 
		System.out.println("Within each task encapsulates their registered name, task name, description, and time allocated."); 
		Thread.sleep(1);
		System.out.println("Task box is ready to go."); 
		Thread.sleep(1);
		System.out.println("Printing all the task node data associated with task box."); 
		Thread.sleep(1);
		box.printAllTaskNodeData();
		
		System.out.println("All task node data is printed."); 
		
		//Creating backup files here
		File[]files = new File[length]; 
		int count = 0; 
		for(List<Task> taskPerPerson: tasksOfAllPeople)
		{
			File file = new File("person_" + (count+1) + ".txt");
			files[count] = file; 
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
	            // Write data to the file
				for(Task tasks: tasksPerPerson)
				{
					writer.write("Task Name: " + tasks.getName() + "\n");
					writer.write("Description: " + tasks.getDescription() + "\n");
					writer.write("Name Of Person: " + tasks.getName() + "\n");
					writer.write("Task Name: " + tasks.getName() + "\n");
					writer.write("-------------------------------------\n");
				}
				count++; 
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        System.out.println("File has been written to: " + file.getAbsolutePath());
	        
	        //Simulation of system crash
	        //Good news is that files were already uploaded to local system
	        
	        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	                // Process each line of the file
	                System.out.println(line);
	                FileWriter f = new FileWriter(file); 
	                f.write("Going to add some new data.");
	                throw new RuntimeException("Crash randomly happened."); 
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch(Exception e)
	        {
	        	System.out.println("System crashed randomly: " + e.getMessage()); 
	        } finally {
	        	System.out.println("Cleanup and data recovery after the simulated crash");
	        	
	        	//We saved our file objects in the File objects attribute.
	        	//So it's possible to restore this information.
	        	for(File getFile: files)
	        	{
	        		System.out.println("Path of file: " + getFile.getAbsolutePath()); 
	        		System.out.println("Time file was modified before crash: " + getFile.lastModified()); 
	        	}
	        	System.out.println("Restoration of files finally complete."); 
	        	
	        	System.out.println("Going to insert inside database and terminate program."); 
	        	
	        	JFrame frame = new JFrame();
	        	 
	           String[] columnNames = {"Name Of Person",  "Name Of Task", "Description of Task", "Duration of Task"}; 
	           
	           //Sample data inserted for now
	           //Dynamic data received from user input will be handled later
	            Object[][]data = {
	            		{"Srikar Balasubramanian", "Task 1", "Eat food", 10}, 
	            		{"Srikar Balasubramanian", "Task 2", "Sleep well", 5}
	            }; 
	            
	            JTable table = new JTable(data, columnNames);
	 	       
		        frame.add(table);
		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        frame.setSize(400,400);
		        frame.setLocationRelativeTo(null);  
		        frame.setVisible(true);
	        }
		}
	}
	
	public List<Task> searchAllPeopleTasks(List<List<Task>> tasksOfAllPeople, String personName)
	{
		for(List<Task> tasksPerPerson: tasksOfAllPeople)
		{
			for(Task tasks: tasksPerPerson)
			{
				if(tasks.getAssignedToUser().equals(personName))
				{
					return tasksPerPerson; 
				}
				else 
				{
					break;
				}
			}
		}
		return null;
	}
}
