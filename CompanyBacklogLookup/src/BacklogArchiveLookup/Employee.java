package BacklogArchiveLookup;
//Employee class for the Project Backlog Archive
//An Employee is made up of a name and ID number

public class Employee {

	String name;
	int idNum;
	
	//constructor
	Employee(String name, int id){
		this.name = name;
		this.idNum = id;
	}
	
	public int ReturnID() {
		return idNum;
	}
	public String toString(){
		String result = "\tEmployee name: " + name + "\t\tID Number: " + idNum + "\n";
		return result;
	}
}
