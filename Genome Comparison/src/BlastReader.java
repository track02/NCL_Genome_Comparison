import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class BlastReader {

	//Fields
	static File currentComp;
	static Scanner scan;	
		
	//Methods
	
	//Sets the currentComp to passed file path
	//TODO - File Chooser + GUI
	public static void setComp(String path) throws FileNotFoundException{
				
		currentComp = new File(path);		
		scan = new Scanner(currentComp);		
	}
	
	//Parses currentComp and creates relevant match objects
	public static ArrayList<Match> parseComp() throws IOException{
		
		//String to store current line
		String current;
		
		//Add relevant lines to the list which is used to create match objects
		ArrayList<String> matches = new ArrayList<String>();
		
		//Completed match objects
		ArrayList<Match> parsedmatches = new ArrayList<Match>();
			
		//While we still have content to read
		while(scan.hasNext()){
			
			//Look at the next line
			current = scan.nextLine();
			
			//If the line contains a certain relevant string add it to the list
			if(current.contains("Score = ") || current.contains("Expect"))
				matches.add(current);			
			
			if(current.contains("Gaps") || current.contains("Identities"))			
				matches.add(current);			
			
			if(current.contains("Strand"))				
				matches.add(current);			
		}
		
		//Create matches - we have needed information
			
		//We now need create a match using the strings extracted from the file
		parsedmatches = createMatch(matches);			
		
		//Return the complete objects
		return parsedmatches;				
	}
	
	//Creates match objects from a given arraylist of strings
	public static ArrayList<Match> createMatch(ArrayList<String> matchinfo){
		
		//Make sure we have needed info
		if(matchinfo.size() % 3 != 0)		
			return null;
		
		//If so, initialise variables - for now just number of matching bases / total bases / percentage
		int matchnum = 0;
		int totalmatch = 0;
		int matchper = 0;
		int gaps = 0;
		int matchdiff = 0;
		
		//Used to count out the three strings from the list
		int count = 1;
		
		//List to store match objects
		ArrayList<Match> newmatches = new ArrayList<Match>();		
		
		//For each string
		for(String n: matchinfo){
			
			//Extract the needed values and store
			if(count == 1){		
								
			}			
			
			//Second line - match numbers / gaps
			if(count == 2){			
				
				matchnum = Integer.parseInt(n.substring(n.indexOf("/") + 1, n.indexOf("(", n.indexOf("/")) - 1));
				totalmatch = Integer.parseInt(n.substring( n.indexOf("=") + 2, n.indexOf("/")));
				gaps = Integer.parseInt(n.substring(n.lastIndexOf("=") + 2, n.lastIndexOf("/")));
				matchper =  (int) ((float) totalmatch/matchnum * 100);
				matchdiff = matchnum - totalmatch;
										
			}			
			//Last string needed for a match
			if(count == 3){								
				
				//Create the match				
				newmatches.add(new Match(matchper, totalmatch, matchnum, gaps, matchdiff));								
			}			
			
			//Reset/Increment Count
			if(count!=3)
				count++;						
			else
				count = 1;					
		}		
		
		//Return the finished objects
		return newmatches;		
	}
}