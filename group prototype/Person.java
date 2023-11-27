

public class Person {
	
	{
		System.out.println("This person class contains information about their name and number of tasks."); 
	}
	
	private String name; 
	private int numberOfTasks; 
	
	public Person(String name, int numberOfTasks)
	{
		this.name = name; 
		this.numberOfTasks = numberOfTasks;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getNoOfTasks()
	{
		return numberOfTasks;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setNoOfTasks(int numberOfTasks)
	{
		this.numberOfTasks = numberOfTasks;
	}
	
	
}
