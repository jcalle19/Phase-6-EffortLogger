package stat_report_generator;
import java.util.ArrayList;

//Made by Jacob Allen TH12
public class Employee {
	private String userName; //Name of the User
	private int userTeam; //Team the user belongs to
	private ArrayList<Task> userTasks; //List of tasks completed by user
	
	public Employee(String userName, int userTeam) {
		this.userName = userName;
		this.userTeam = userTeam;
		this.userTasks = new ArrayList<Task>();
	} //employee starts with name and designmated team, task list should start empty since new employee
	
	public String getName() {
		return userName;
	} //Returns the user's name
	
	public int getTeam() {
		return userTeam;
	} //Returns integer representing the user's team
	
	public ArrayList<Task> getTasks() {
		return userTasks;
	} //Returns the ArrayList of tasks
	
	public void addUserTask(Task newTask) {
		userTasks.add(newTask);
	} //adds a completed task to the employee's list of tasks
	
	public void importTaskList(ArrayList<Task> importedList) {
		this.userTasks = importedList;
	} //imports an already existing list, useful for loading employee data
	
	public void censorName() {
		String censoredName  = "";
		for(int i = 0; i < userName.length(); i++) {
			censoredName += 'a';
		}
		userName = censoredName;
	} //Temporary "encoder" to censor name to protect anonymity. Will be changed to something coherent
	
	//will add more methods as needed
}
