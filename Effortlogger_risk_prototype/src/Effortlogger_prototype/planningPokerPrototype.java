//This is the Planning Poker Risk prototype for phase 4 of CSE 360's group project
//Author: Isaiah Nikodimos, Team Th12
//Student ID: 1213084962

package Effortlogger_prototype;
import java.util.*;
import java.io.*;

public class planningPokerPrototype {
	
	public static void main() {
	
		//forward declare variables we will use later. These two take in input from the user
		String inputLine; 
		char userCommand;
		
		//declare an arraylist of integers that will keep track of the votes
		ArrayList<Integer> testList = new ArrayList<Integer>();
		
		//insert 4 random integers between 1 and 10 into the list to initialize
		for(int i=0;i < 4;i++) {
			int randomInt = 1 + (int)(Math.random() * ((10 - 1) + 1));
			testList.add(randomInt);
		}
		
		//To easily facilitate Planning Poker sessions, this prototype gives users a basic interface
		//detailing all the acceptable commands for the session.
		System.out.println("\t\tA new Planning Poker round has begun.\n");
		System.out.println("The current user story is: A user who is trying to search throuth their files"
				+ " wants the ability to search or filter their files via keywords.");

		System.out.print("Choice\t\tAction\n" + "------\t\t------\n" 
		+ "A\t\tInput your estimate(interger 1-10)\n"
        + "B\t\tDisplay the current user story\n" + "C\t\tDisplay the previous user story and weight\n"
        + "L\t\tList the current weight estimates\n"
        + "N\t\tGo to a new round of voting\n"
        + "P\t\tSave current data to a backup file.\n"
        + "Q\t\tQuit\n"
        + "?\t\tHelp\n");  //help displays the list of commands again
		
		do {
		
			System.out.println("\nWhich action would you like to perform? Enter the corresponding character.");
			
			Scanner scnr = new Scanner(System.in);
			
			//read in the user's input and normalize it as to perform the correct command
			inputLine = scnr.nextLine().trim();
			
			if(inputLine.isEmpty()) inputLine = "!";
			userCommand = inputLine.charAt(0);
			userCommand = Character.toUpperCase(userCommand);
			
			switch(userCommand) {
			
			case 'A': //add the user's weight estimate to the vote pool. Check for valid input
				
				if(testList.size() == 5) { //if the list size is 5 the user has already entered their input
					System.out.println("You've already entered your estimate for this round. Please wait for the next one");
					break;
				}
				
				System.out.print("Enter your weight estimate(integer 1-10): ");
				try {
					int userInt = scnr.nextInt();
					
					if(userInt < 1 || userInt > 10) { //check that the int is within bounds
						System.out.println("Error: the entered weight was outside the accepted range(1-10)");
						break;
					}
					System.out.println("Entered your weight estimate of: " + userInt);
					testList.add(0, userInt);
				}
				catch(InputMismatchException e) {
					System.out.println("Error: An integer was not entered");
				}
				break;
				
			case 'B':  //display the current user story. This option makes it easy for teams to view past session data
				System.out.println("Current user story: A user is attempting to search through their files"
						+ " and wants a way to search or filter their files via keywords.");
				break;
			
			case 'C': //display the previous user story and consensus weight
				System.out.println("Previous user story: A user wants to be able to save their work reports as pdf files.");
				System.out.println("Consensus Weight: 2");
				break;
			
			case 'L': //display the current estimates entered from the previous round.
				//Everyone's input is supposed to get revealed at the same time. So if there aren't enough
				//entries output an error
				if(testList.size() < 5) {
					System.out.println("Error: You haven't entered your weight estimate yet");
					break;
				}
				printWeights(testList);
				break;
			
			case 'N': //Start a new round of voting. Clear the test list and collect new weights from users.
				
				if(testList.size() < 5) { //can't start a new round if everyone hasn't voted yet
					System.out.println("Everyone hasn't voted in the current round yet");
					break;
				}
				System.out.println("A new round of voting for the current user story has begun!"
						+ "\nThe previous round's weight totals were:");
				printWeights(testList);
				
				testList.clear();
				for(int i=0;i < 4;i++) {
					int randomInt = 1 + (int)(Math.random() * ((10 - 1) + 1));
					testList.add(randomInt);
				}
				break;
			
			case 'P': //save the current user story and vote data to a output file for backup.
				//the ability to save session data in backups and mitigate data corruption was one of the client's requests.
				System.out.println("The current round's data will be saved to a backup file. "
						+ "Please enter the name of the file for backup:");
				String outputFileName = scnr.nextLine().trim();
				try {
                    File outputFile = new File(outputFileName);
                    PrintWriter fileWriter = new PrintWriter(outputFile);
                    BufferedWriter buffer = new BufferedWriter(fileWriter);

                    buffer.write("This session's user story: A user is attempting to search through their files "
    						+ "and wants a way to search or filter their files via keywords.\n"
                    		+ "Current vote tallies:" + testList);

                    buffer.close();
                    fileWriter.close();
                    //confirmation so the user knows the write was successful
                    System.out.printf("%s is written\n",outputFileName);
                } 
                catch (IOException e) { //catch an exception in case something goes wrong
                    System.out.printf("An error occured trying to write to the backup file.\n");
                }
				break;
				
			case 'Q': //quit
				break;
				
			case '?': //the help command. Display the list of commands and start message again.
				System.out.println("\t\tStart of a new Planning Poker round\n");
				System.out.println("The current user story is: A user wants the ability to sort their files by keyword");

				System.out.print("Choice\t\tAction\n" + "------\t\t------\n" 
	    		+ "A\t\tInput your estimate(interger 1-10)\n"
	            + "B\t\tDisplay the current user story\n" + "C\t\tDisplay the previous user story and weight\n"
	            + "L\t\tList the current weight estimates\n"
	            + "N\t\tGo to a new round of voting\n"
	            + "P\t\tSave current data to a backup file.\n"
	            + "Q\t\tQuit\n"
	            + "?\t\tHelp\n");  //help displays the list of commands again
				
			default: //default command in case the user inputs something incorrectly. Give them the option to view the command list again
                System.out.print("Unknown action.\nPress '?' to see the menu again\n");
                break;
			}
	
		}while (userCommand != 'Q' || inputLine.length() != 1);
	}
	//helper function to print everyone's current weight estimates for the planning poker session
	//The user's weight is always the first index
	public static void printWeights(ArrayList<Integer> list) {
		System.out.println("Your estimate: " + list.get(0));
		System.out.println("Claire's estimate: " + list.get(1));
		System.out.println("Frank's estimate: " + list.get(2));
		System.out.println("Rahvun's estimate: " + list.get(3));
		System.out.println("Nimrod's estimate: " + list.get(4));
	}
}
