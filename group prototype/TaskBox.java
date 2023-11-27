
import java.util.List;

//What is the goal of the TaskBox?
//Assign a set of tasks to a user
//Point to the next user who has another set of tasks

//Utilizing linked-lists for efficiency O(n) time

//Work in progress

public class TaskBox {
	
	static TaskNode head; 
	
	//Task Box Instance initializer
	{
		System.out.println("Starting process to create a TaskBox."); 
	}
	
	public static class TaskNode
	{
		private String person; 
		private int countOfTasks; 
		public List<Task> tasksForPerson; 
		TaskNode next; 
		
		{
			System.out.println("Collecting tasks for the TaskNode."); 
			
		}
		
		public TaskNode(String person, int countOfTasks)
		{
			this.person = person; 
			this.countOfTasks = countOfTasks; 
		}
	}
	
	public void insert(TaskBox.TaskNode newTaskNode)
	{
		if(head == null)
		{
			head = newTaskNode; 
		}
		else 
		{
			TaskNode root = head; 
			while(root.next != null)
			{
				root = root.next; 
			}
			root.next = newTaskNode; 
		}
	}
	
	public void printAllTaskNodeData()
	{
		if(head == null)
		{
			System.out.println("No TaskNode data exists to view."); 
		}
		else 
		{
			TaskNode current = head; 
			while(current.next != null)
			{
				System.out.println(current.person); 
				System.out.println(current.countOfTasks); 
				current = current.next; 
			}
			System.out.println(current.person);
			System.out.println(current.countOfTasks); 
		}
	}
	
	//Unit Testing
	public static void main(String[]args)
	{
		//Creating set of Tasks to test out
	}
}
