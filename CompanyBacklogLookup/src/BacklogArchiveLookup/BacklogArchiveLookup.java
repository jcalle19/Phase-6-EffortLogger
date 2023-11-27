package BacklogArchiveLookup;

import java.util.*;

public class BacklogArchiveLookup {

	static ArrayList<Project> backlog = new ArrayList<Project>();
	
	public static void main(String[] args) {
		
		String inputLine; 
		char userCommand;
		
		setupArchive();
		
		System.out.println("Welcome to the archive portal for the Company Project Archive. ");
		System.out.print("Choice\t\tAction\n" + "------\t\t------\n" 
				+ "A\t\tView the Project Archive\n"
				+ "B\t\tSearch the Project Archive by description\n"
				+ "C\t\tSearch the Project Archive by team member\n"
				+ "D\t\tSort the Project Archive\n"
				+ "E\t\tFilter the Project Archive\n");
		System.out.println("\nWhich action would you like to perform? Enter the corresponding character.");

		if(backlog.isEmpty()) {
			System.out.println("Error: The Project Archive is empty. There is nothing to view or sort");
			return;
		}
		Scanner scnr = new Scanner(System.in);
		
		//read in the user's input and normalize it as to perform the correct command
		inputLine = scnr.nextLine().trim();
		
		if(inputLine.isEmpty()) inputLine = "!";
		userCommand = inputLine.charAt(0);
		userCommand = Character.toUpperCase(userCommand);

		switch(userCommand) {
			
		//Print the whole project backlog
		case 'A':
			PrintBacklog(backlog);
			break;
			
		//search the project backlog for a matching title or user story	
		case 'B':
			System.out.println("The search function will search the Project Archive for project's who's user story or title match the search terms");
			System.out.print("Enter your search terms: ");
			String searchTerms = scnr.next().toLowerCase();
			
			SearchByDescription(backlog, searchTerms);
			break;
		
		//search the project backlog archive by team member
		case 'C':
			int id = 0;
			
			System.out.println("The search functiion by employee will search the Project Archive and return all projects that the given employee was a part of");
			System.out.print("Please enter the 4-digit ID number of the employee you'd like to searchf or: ");
			
			try {
				id = scnr.nextInt();
				if(id < 1000 || id > 9999) {
					System.out.println("Error: id number is out of range and invalid");
					break;
				}
				
			} catch(InputMismatchException e) {
				System.out.println("Error: An integer was not entered");
				break;
			}
			
			SearchByEmployee(backlog,id);
			break;
		
		//Sort the product backlog according to Story Point Value or the number of defects discovered during the project
		case 'D': 
			SortBacklog(backlog);
			break;
		
		//Filter the product backlog. Print only projects that pass the specified criteria
		//The criteria is greater than or less than the entered Story Point Value or defect threshold
		case 'E': 
			FilterBacklog(backlog);
			break;
		
		default:
			System.out.println("Error: Invalid command");
		}
		scnr.close();
	}
	
	//prints the whole backlog with no sorting or filters
	public static void PrintBacklog(ArrayList<Project> list) {
		for(int i = 0; i< list.size();i++) {
			System.out.println(list.get(i));
		}
	}
	
	//print only projects who's user story or title contain the search terms
	public static void SearchByDescription(ArrayList<Project> list, String searchTerms) {
		
		int matches = 0;
		for(int i = 0; i< list.size();i++) {
			if(list.get(i).title.toLowerCase().contains(searchTerms)|| list.get(i).userStory.toLowerCase().contains(searchTerms)) {
				System.out.println(list.get(i));
				matches++;
			}
		}
		if(matches == 0) System.out.println("No matching projects were found.");
	}
	
	//print only the projects that have the given employee as a team member
	public static void SearchByEmployee(ArrayList<Project> list, int id) {
		
		int matches = 0; //keep track of how many matches are found to notify the user if there are no matches
		
		System.out.println("Projects in which the given employee with id num: " + id + " was a team member on\n");
		//iterate through the project backlog and then iterate through the team members arraylist for each Project for employees with matching IDs
		for(int i = 0; i< list.size();i++) {
			for(int j = 0; j  < list.get(i).teamMembers.size(); j++) {
				if(list.get(i).teamMembers.get(j).ReturnID() == id) {
					System.out.println(list.get(i));
					matches++;
				}
			}
		}
		//print this message if no matches were found
		if(matches == 0) System.out.println("No matching projects were found.");	
	}
	
	//sort the backlog according to the user entered criteria
	public static void SortBacklog(ArrayList<Project> backlog) {
		
		char userCommand;
		Scanner scnr = new Scanner(System.in);
		boolean increasing = true;
		
		System.out.println("How would you like to sort the project backlog?\n"
				+ "A: Story Point Value\tB: Number of defects found during development");
		userCommand = scnr.next().charAt(0);
		
		if(userCommand != 'A' && userCommand != 'B') {
			System.out.println("Error: Incorrect command.");
			scnr.close();
			return;
		}
		
		System.out.println("In increasing or decreasing order?\n"
				+ "I: Increasing(lowest at the top)\tD: Decreasing(highest at the top)");
		if(scnr.next().charAt(0) == 'D') increasing = false;
		
		if(userCommand == 'A') {
			Collections.sort(backlog, new ProjectSPComparator());
			
			if(!increasing) Collections.reverse(backlog);
			
			System.out.println("Project Archive sorted by Story Point value.\n");
			for(int i = 0; i< backlog.size();i++) {
				System.out.println(backlog.get(i));
			}
		}
		else if(userCommand == 'B') {
			Collections.sort(backlog, new ProjectDefectsComparator());
			
			if(!increasing) Collections.reverse(backlog);

			System.out.println("Project Archive sorted by number of defects found during development.\n");
			for(int i = 0; i< backlog.size();i++) {
				System.out.println(backlog.get(i));
			}
		}
		scnr.close();
	}
	
	//filter out projects that don't pass the user's criteria and print the rest
	public static void FilterBacklog(ArrayList<Project> backlog) {
		
		int threshold = 0;
		int matches = 0;
		char userCommand;
		Scanner scnr = new Scanner(System.in);
		
		System.out.println("The filter function will only display projects with a story point value or defects number"
				+ " less than or equal to a given threshold");
		System.out.print("Enter your threshold: ");
		try {
			threshold = scnr.nextInt();
		
			System.out.println("How would you like to filter the project backlog?\n"
					+ "A: Story Point Value\tB: Number of defects found during development");
			
			userCommand = scnr.next().charAt(0);
			
			if(userCommand != 'A' && userCommand != 'B') {
				System.out.println("Error: Incorrect command.");
				scnr.close();
				return;
			}
			
			if(userCommand == 'A') {
					
				System.out.println("Projects with a Story Point Value less than or equal to " + threshold + ".\n");
				//go through the list and only print the item's who's SP values pass the criteria
				for(int i = 0; i< backlog.size();i++) {
					if(backlog.get(i).storyPointValue <= threshold) {
						System.out.println(backlog.get(i));
						matches++;
					}
				}
			}
			else if(userCommand == 'B') {
					
				System.out.println("Projecs with the a number of defects less than or equal to " + threshold + ".\n");
				//go through the list and only print the item's who's defect value pass the criteria
				for(int i = 0; i< backlog.size();i++) {
					if(backlog.get(i).defects <= threshold) {
						System.out.println(backlog.get(i));
						matches++;
					}
				}
			}
		}
		catch(InputMismatchException e) {
			System.out.println("Error: An integer was not entered");
			return;
		}
		//print this message if no matches were found
		if(matches == 0) System.out.println("No matching projects were found.");
		scnr.close();
	}
	
	//A helper function to setup the backlog archive for testing purposes
	public static void setupArchive() {
		Employee test1 = new Employee("Dave Chappelle", 2311);
		Employee test2 = new Employee("Bill Burr", 2456);
		Employee test3 = new Employee("George Carlin", 2008);
		ArrayList<Employee> list = new ArrayList<Employee>();
		list.add(test1);
		list.add(test2);
		list.add(test3);
		
		Project pj = new Project("Email Server", "As a manager, I want a better email service fro my business", 7, 4, list);
		
		Employee test4 = new Employee("Chris Rock", 1939);
		Employee test5 = new Employee("Adam West", 2222);
		ArrayList<Employee> list2 = new ArrayList<Employee>();
		list2.add(test4);
		list2.add(test5);
		
		Project pj2 = new Project("Cloud Storage Server", "As an employee, I want to be able to backup my data to cloud storage", 3, 9, list2);
		
		Employee test6 = new Employee("Richard Pryor", 1940);
		Employee test7 = new Employee("Hannibul Buress", 2319);
		Employee test8 = new Employee("Tina Fey", 2530);
		ArrayList<Employee> list3 = new ArrayList<Employee>();
		list3.add(test6);
		list3.add(test7);
		list3.add(test8);
		
		Project pj3 = new Project("Login Portal", "As an IT agent, I want a secure login portal", 8, 2, list3);
		
		Employee test9 = new Employee("Daniel Tosh", 2001);
		Employee test10 = new Employee("Seth McFarlane", 2751);
		ArrayList<Employee> list4 = new ArrayList<Employee>();
		list4.add(test2);
		list4.add(test9);
		list4.add(test10);
		
		Project pj4 = new Project("Discussion Board", "As a user, I want a way to easily discuss with my team members on a discussion board"
				,2, 6, list4);
		
		Employee test11 = new Employee("Hannibal Burress", 2568);
		ArrayList<Employee> list5 = new ArrayList<Employee>();
		list5.add(test5);
		list5.add(test8);
		list5.add(test11);
		
		Project pj5 = new Project("Remote Connection", "As a user, I want to be able to connect to the company server remotely", 5, 1, list5);
		
		backlog.add(pj);
		backlog.add(pj2);
		backlog.add(pj3);
		backlog.add(pj4);
		backlog.add(pj5);
	}

}
