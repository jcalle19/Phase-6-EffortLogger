package BacklogArchiveLookup;

import java.util.*;

public class Project {

	protected String title;
	protected String userStory;
	protected int storyPointValue;
	protected int defects;
	protected ArrayList<Employee> teamMembers;
	
	//constructor
	Project(String title, String userStory, int storyPointValue, int defects, ArrayList<Employee> list){
		this.title = title;
		this.userStory = userStory;
		this.storyPointValue = storyPointValue;
		this.defects = defects;
		this.teamMembers = list;
	}
	
	public String toString() {
		String result = "Project Title: " + title + "\nUser Story: " + this.userStory
				+ "\nStory Point Value: " + this.storyPointValue + "\nPredicted Story Point Value: " + "\nNumber of Defects: " 
				+ this.defects + "\nTeam Members:\n";
		
		for(int i = 0;i < teamMembers.size();i++) {
			result += teamMembers.get(i);
		}
		
		return result;
 	}
}

class ProjectSPComparator implements Comparator<Project>{

	@Override
	public int compare(Project p1, Project p2) {
		if (p1 == p2) return 0;
		else if (p1.storyPointValue > p2.storyPointValue) return 1;
		else return -1;
	}
	
}

class ProjectDefectsComparator implements Comparator<Project>{

	@Override
	public int compare(Project p1, Project p2) {
		if (p1 == p2) return 0;
		else if (p1.defects > p2.defects) return 1;
		else return -1;
	}
	
}


