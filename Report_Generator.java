
import java.util.ArrayList;
import java.io.*;

//Made by Jacob Allen Team: TH12
public class Report_Generator { //Takes in employee data and exports to file in format to be specified
	/* Format for report text file
	 * ---------------------------
	 * (Employee Name, censored)
	 * (Employee Team, changed to a simple number common across all team members, not consistent across reports)
	 * (Employee Task 1: attribute 1 | attribute 2 | ... | attribute n)
	 * (Employee Task 2: attribute 1 | attribute 2 | ... | attribute n)
	 * ...
	 * (Employee Task n)
	 * ---------------------------
	 * ... <- same format for Employee 1 to n for team Reports
	 * ---------------------------
	 * (Employee Name n, censored)
	 * (Employee Team, changed to a simple number common across all team members, not consistent across reports)
	 * (Employee Task 1: attribute 1 | attribute 2 | ... | attribute n)
	 * (Employee Task 2: attribute 1 | attribute 2 | ... | attribute n)
	 * ...
	 * (Employee Task n)
	 * ---------------------------
	 * 
	 * will add summary attributes as necessary
	 */
	public void generateUserReport(Employee user, String date) throws IOException{ //Use underscores instead of / for the date
		user.censorName();
		String fileString = user.getName() + "Report" + date + ".txt";
		File newFile = new File(fileString);
		FileWriter reportWriter = new FileWriter(newFile);
		String userIDLine = "Encoded UserID: " + user.getName(); //Formats the userID line
		String userTeamLine = "Localized User Team #: " + Integer.toString(user.getTeam()); //Formats the user team line
		String userTaskLines = ""; //Initializes for formatting in the upcoming loop
		Task2 tempTaskHolder; //will hold the current task being parsed into string
		for (int i = 0; i < userIDLine.length(); i++) { //Goes through every character in user name
			reportWriter.write(userIDLine.charAt(i));   //and inputs it into the file
		}
		
		reportWriter.write("\n"); //Newline
		
		for (int i = 0; i < userTeamLine.length(); i++) { //Goes through every character in user team line
			reportWriter.write(userTeamLine.charAt(i));  //and inputs it into the file
		}
		
		reportWriter.write("\n"); //Newline
		
		for (int i = 0; i < user.getTasks().size(); i++) { //Iterates through task list
			tempTaskHolder = user.getTasks().get(i);
			userTaskLines = "Task " + (i + 1) + ": " + tempTaskHolder.getTaskName() + " | "; //Adding task number and name to the string
			userTaskLines += tempTaskHolder.getTaskProject() + " | "; //Adding project to string
			userTaskLines += tempTaskHolder.getDateCompleted() + " | "; //Adding date completed to string
			userTaskLines += tempTaskHolder.getTimeStarted() + " - " + tempTaskHolder.getTimeFinished() + " | "; //Adding time taken to string
			userTaskLines += tempTaskHolder.getEffortCategory(); //Adding effort category to string
			//These could be done in one line but it is done split up for clarity
			for (int j = 0; j < userTaskLines.length(); j++) {
				reportWriter.write(userTaskLines.charAt(j));
			}
			reportWriter.write("\n");
		}
		
		reportWriter.write("\n"); //Newline
		reportWriter.close();
	}
	
	public void generateTeamReport(ArrayList<Employee> teamMembers, String date) throws IOException {
		if (teamMembers.size() >= 1) {
			String fileString = "Team_" + teamMembers.get(0).getTeam() + "_Report" + date + ".txt";
			File newFile = new File(fileString);
			FileWriter reportWriter = new FileWriter(newFile);
			for (int k = 0; k < teamMembers.size(); k++) {
				Employee user = teamMembers.get(k);
				user.censorName();
				String userIDLine = "Encoded UserID: " + user.getName(); //Formats the userID line
				String userTeamLine = "Localized User Team #: " + Integer.toString(user.getTeam()); //Formats the user team line
				String userTaskLines = ""; //Initializes for formatting in the upcoming loop
				Task2 tempTaskHolder; //will hold the current task being parsed into string
				for (int i = 0; i < userIDLine.length(); i++) { //Goes through every character in user name
					reportWriter.write(userIDLine.charAt(i));   //and inputs it into the file
				}
		
				reportWriter.write("\n"); //Newline
		
				for (int i = 0; i < userTeamLine.length(); i++) { //Goes through every character in user team line
					reportWriter.write(userTeamLine.charAt(i));  //and inputs it into the file
				}
		
				reportWriter.write("\n"); //Newline
		
				for (int i = 0; i < user.getTasks().size(); i++) { //Iterates through task list
					tempTaskHolder = user.getTasks().get(i);
					userTaskLines = "Task " + (i + 1) + ": " + tempTaskHolder.getTaskName() + " | "; //Adding task number and name to the string
					userTaskLines += tempTaskHolder.getTaskProject() + " | "; //Adding project to string
					userTaskLines += tempTaskHolder.getDateCompleted() + " | "; //Adding date completed to string
					userTaskLines += tempTaskHolder.getTimeStarted() + " - " + tempTaskHolder.getTimeFinished() + " | "; //Adding time taken to string
					userTaskLines += tempTaskHolder.getEffortCategory(); //Adding effort category to string
					//These could be done in one line but it is done split up for clarity
					for (int j = 0; j < userTaskLines.length(); j++) {
						reportWriter.write(userTaskLines.charAt(j));
					}
					reportWriter.write("\n");
				}
				reportWriter.write("\n");
			}
		
		reportWriter.write("\n"); //Newline
		reportWriter.close();
		}
		else
			System.out.println("Invalid Team Size, unable to generate report");
	}
	
	public static void main(String[] args) throws IOException{ 
		//Not in final product, only for testing. All of the values here are hard coded
		//whereas in the final product these values will be dynamically entered through the UI
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
		
		
	}
}
