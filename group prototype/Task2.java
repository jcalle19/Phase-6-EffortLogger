

//Made by Jacob Allen Team: TH12
public class Task2 {
	//Contains the different attributes entered when creating a task
	//Used to create tasks completed for the employee object
	private String timeStarted; //Time of day task was started
	private String timeFinished; //Time of day task was finished
	private String taskProject; //Project the task is contributing towards
	private String dateCompleted; //The day of completion for the task
	private String effortCategory; //The effort category of the task
	private String taskName; //The name/description of the task
	
	public Task2(String timeStarted, String taskProject, String effortCategory, String taskName) {
		this.timeStarted = timeStarted;
		this.taskProject = taskProject;
		this.effortCategory = effortCategory;
		this.taskName = taskName;
	}//This will be called when the task is started and this "finished" attributes
	 //will be modified when the task is complete
	
	//The following are "getter" methods for the different attributes of the Task object
	public String getTimeStarted() {
		return timeStarted;
	}
	
	public String getTimeFinished() {
		return timeFinished;
	}
	
	public String getTaskProject() {
		return taskProject;
	}
	
	public String getDateCompleted() {
		return dateCompleted;
	}
	
	public String getEffortCategory() {
		return effortCategory;
	}
	
	public String getTaskName() {
		return taskName;
	}
	
	//The following are "setter" methods for the different attributes of the Task object
	public void setTimeStarted(String startTimeEntered) {
		timeStarted = startTimeEntered;
	}//Sets the time started
	
	public void setTimeFinished(String finishTimeEntered) {
		timeFinished = finishTimeEntered;
	}//Sets the time finished
	
	public void setTaskProject(String projectEntered) {
		taskProject = projectEntered;
	}//Sets the task project
	
	public void setDateCompleted(String completeDateEntered) {
		dateCompleted = completeDateEntered;
	}//sets the date completed
	
	public void setEffortCategory(String effortCategoryEntered) {
		effortCategory = effortCategoryEntered;
	}//sets the effort category
	
	//will add more methods/attributes as needed
}
