import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class BlastReader {

	//Fields
	static File currentComp;
	static Scanner scan;	
	
	static boolean getQuery = false; 
	static boolean getSubject = false;	
	
	//Methods
	
	//Sets the currentComp to passed file path
	//TODO - File Chooser + GUI
	public static void setComp(File f) throws FileNotFoundException{
				
		currentComp = f;		
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
			if(current.contains("Score = ") || current.contains("Expect")){
				matches.add(current);			
				
				//If we are looking at a line containing "Score"
				//A new match has been reached - reset booleans to get base locations
				getQuery = false;
				getSubject = false;								
			}				
				
			if(current.contains("Gaps") || current.contains("Identities"))			
				matches.add(current);			
			
			if(current.contains("Strand"))				
				matches.add(current);		
			
			//Get inital base location of query
			if(current.contains("Query") && current.contains("Query=") == false && getQuery == false){
				matches.add(current);				
				//Set boolean to true, only want first reading
				getQuery = true;				
			}
			
			//Get initial base location of subject
			if(current.contains("Sbjct") && getSubject == false){
				matches.add(current);				
				//Set boolean to true, only want first reading
				getSubject = true;
							
			}
			
		}
			
		//We now need create a match using the strings extracted from the file
		parsedmatches = createMatch(matches);			
		
		//Return the complete objects
		return parsedmatches;				
	}
	
	//Creates match objects from a given arraylist of strings
	public static ArrayList<Match> createMatch(ArrayList<String> matchinfo){
		
		//Make sure we have needed info
		if(matchinfo.size() % 5 != 0)		
			return null;
		
		//If so, initialise variables - for now just number of matching bases / total bases / percentage
		int matchnum = 0;
		int totalmatch = 0;
		int matchper = 0;
		int gaps = 0;
		int matchdiff = 0;
		
		//Strand - true for plus false for minus
		boolean qstrand = true;
		boolean substrand = true;
		
		//Subject & Query start
		int qstart = 0;
		int substart = 0;
		
		//Subject & Query end
		int qend = 0;
		int subend = 0;		
		
		
		
		//Used to count out the three strings from the list
		int count = 1;
		
		//List to store match objects
		ArrayList<Match> newmatches = new ArrayList<Match>();		
		
		//For each string
		for(String n: matchinfo){
			
			//System.out.println(n);
			
			//First Line Score/Expect info
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
			
			//Third Line - strand info
			if(count == 3){
				
				//Read the character 3 spaces behind the /, this is an L if Plus and an N if minus				
				String qs = n.substring(n.indexOf("/")-3, n.indexOf("/")-2);	
				
				//Now check the character
				if(qs.equals("l"))
					qstrand = true;
				else
					qstrand = false;
								
				
				//Read the character 1 space ahead of the /, P if Plus and M if Minus
				String sst = n.substring(n.indexOf("/")+1, n.indexOf("/")+2);
				
				//Now check the character
				if(sst.equals("P")){
					substrand = true;
				}
				else{
					substrand = false;
				}
				
				System.out.println("QStrand: " + qstrand);
				System.out.println("SubStrand: " + substrand);


				
			}
			
			
			//Fourth line - Query start value
			if(count == 4){
				
				qstart = Integer.parseInt(n.substring(7, n.indexOf(" ", 7) ));
				qend = Integer.parseInt(n.substring(n.lastIndexOf(" ")+1, n.length()));
				

				
				System.out.println("Query End: " + qend);
			}
			
			//Final line - Subject start value
			if(count == 5){				
				
				
				substart = Integer.parseInt(n.substring(7, n.indexOf(" ", 7) ));
				subend = Integer.parseInt(n.substring(n.lastIndexOf(" ")+1, n.length()));
				System.out.println("Subject Start: " + substart);				
				
								
				System.out.println("Subject End: " + subend);
				
				//Create the match				
				newmatches.add(new Match(matchper, totalmatch,
										 matchnum, gaps, 
										 matchdiff, qstart, 
										 qend, substart, 
										 subend,
										 qstrand, substrand));					
				
			}			
			
			//Reset/Increment Count
			if(count!=5)
				count++;						
			else
				count = 1;					
		}		
		
		//Return the finished objects
		return newmatches;		
	}
}