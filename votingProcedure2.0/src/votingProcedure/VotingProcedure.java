package votingProcedure;
import java.util.ArrayList;
import java.util.Scanner;

//Author of Function, Jacob Allen
public class VotingProcedure {
	private ArrayList<Integer> voteList; //Has the list of votes
	private double variance; //Has the variance to determine the outliers
	
	public VotingProcedure(ArrayList<Integer >voteList) { //Constructor
		this.voteList = voteList;
		this.variance = findVariance(this.voteList);
	}
	
	
	public double findVariance(ArrayList<Integer> voteList) {
		double mean = 0; //Will hold the mean of the array
		double voteListVar = 0; //Will hold the variance of the array
		for (int i = 0; i < voteList.size(); i++) { //Iterating through the array and adding the total to mean
		        mean += voteList.get(i);
		}
		mean = mean / voteList.size(); //Dividing mean by total number of votes
		for (int i = 0; i < voteList.size(); i++) { //Iterating through the array and adding the variance calculation to voteListVar
		    voteListVar += Math.pow(voteList.get(i) - mean, 2);
		}
		voteListVar = voteListVar / voteList.size(); //Finalizing variance by dividing by total amount of votes
		return voteListVar;
	}
	
	public int checkVotes() {
		if (variance == 0) {
			System.out.println("All votes the same, end session");
			return 0;
		}
		else if (variance <= 1) {
			System.out.println("Commence Discussion, no notable outliers");
			return 1;
		}
		else {  //variance is greater than 1, so the program must find the outliers
			System.out.println("Must identify outliers");
			return 3;
		}

	}//return 1 means all votes are the same
	//return 2 means votes are different but no outlier
	//return 3 means votes are different but at least one outlier

	public ArrayList<Integer> interpretVotes() {
		ArrayList<Integer> outliers = new ArrayList<Integer>();
		if (checkVotes() != 3) {
			return outliers;
		}
		else {
			int minIndex = 0; //Default assuming that the lowest is the first index
			int highIndex = voteList.size() - 1;
			for (int i = 0; i < voteList.size(); i++) {//Finding lowest, highest
				if (voteList.get(i) < voteList.get(minIndex)) {
					minIndex = i;
				}
				if (voteList.get(i) > voteList.get(highIndex)) {
					highIndex = i;
				}
			}
			outliers.add(voteList.get(minIndex));
			outliers.add(voteList.get(highIndex));
		}
		return outliers;
	}
	
	public static void main(String[] args) {
		try {
		ArrayList<Integer> voteList = new ArrayList<Integer>();
		Scanner testInput = new Scanner(System.in);
		System.out.print("Enter the votes consecutively no spaces: ");
		String voteString = testInput.nextLine();
		
		//if the input string is empty return early
		if(voteString.isEmpty()) {
			System.out.println("Error: No votes were entered. Cannot parse an empty string");
			return;
		}
		
		for (int i = 0; i < voteString.length(); i++) { //Collects user input, changes to int and adds to an arrayList to represent the originalvotes
			String vote = Character.toString(voteString.charAt(i));
			int voteInt = Integer.parseInt(vote);
			voteList.add(voteInt);
		}
		//set-up for the methods to compare the votes
		VotingProcedure newVP = new VotingProcedure(voteList);  //create a VotingProcedure class with the collected votes
		ArrayList<Integer> newVoteList = newVP.interpretVotes(); //Is the list that contains the outliers
		for (int i = 0; i < newVoteList.size(); i++) { //Outputting the 2 outliers
			System.out.println(newVoteList.get(i));
		}
		testInput.close();
		} catch(NumberFormatException e) {
			System.out.println("Error: Please only enter integers");
			return;
		}
	}
}


