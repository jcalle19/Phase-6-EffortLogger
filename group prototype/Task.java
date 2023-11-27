
public class Task {
	private String name; 
	private String description; 
	private String assignedToUser;  
	private int durationOfTime; 
	
	public Task(String name, String description, String assignedToUser, int durationOfTime)
	{
		this.name = name;
		this.description = description; 
		this.assignedToUser = assignedToUser;
		this.durationOfTime = durationOfTime; 
	}
	
	public String getName()
	{
		return name; 
	}
	
	public String getDescription()
	{
		return description; 
	}
	
	public String getAssignedToUser()
	{
		return assignedToUser; 
	}
	
	public int getDurationOfTime()
	{
		return durationOfTime; 
	}
	
	public void setName(String name)
	{
		this.name = name; 
	}
	
	public void setDescription(String description)
	{
		this.description = description; 
	}
	
	public void setAssignedToUser(String assignedToUser)
	{
		this.assignedToUser = assignedToUser; 
	}
	
	public void setDurationOfTime(int durationOfTime)
	{
		this.durationOfTime = durationOfTime; 
	}
	
	//Unit Testing
	
	public static void main(String[]args)
	{
		//Creating a random list of tasks for testing purposes first 
		//in order to test functionality. 
		//Tasks related to EffortLogger V2 will be created and tested later.
		Task task1 = new Task("Morning Task 1", "Brush my Teeth", "Srikar B.", 5); 
		Task task2 = new Task("Morning Task 2", "Shower", "Srikar B.", 15); 
		Task task3 = new Task("Afternoon Task 1", "Go for a walk", "Srikar B.", 20); 
		Task task4 = new Task("Afternoon Task 2", "Make Lunch", "Srikar B.", 30); 
		Task task5 = new Task("Afternoon Task 3", "Do homework", "Srikar B.", 60); 
		Task task6 = new Task("Afternoon Task 4", "Play Videogames", "Srikar B.", 40); 
		task6.setDurationOfTime(30);
		System.out.println(task1.getDescription()); 
		System.out.println(task3.getName());
		System.out.println(task5.getAssignedToUser()); 
		
		
		
		
	}
}
