import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;


public class FReader {

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
		
		
		String current;
		
		ArrayList<String> matches = new ArrayList<String>();
		
		ArrayList<Match> parsedmatches = new ArrayList<Match>();
		
	
		
		while(scan.hasNext()){
			
			current = scan.nextLine();
			
			if(current.contains("Score = ") || current.contains("Expect")){
				System.out.println("Score Added");
				matches.add(current);
			}
			
			if(current.contains("Gaps") || current.contains("Identities")){
				System.out.println("Gaps Added");
				matches.add(current);
			}
			
			if(current.contains("Strand")){
				System.out.println("Strand Added");
				matches.add(current);
			}		
		}
		
		//Create a matches - we have needed information

			
			//We now need create a match using the three strings
			parsedmatches = createMatch(matches);
		
			
	
			//Looking for new set of match info
			
			
		
		
		
		return parsedmatches;		
		
	}
	
	
	public static ArrayList<Match> createMatch(ArrayList<String> matchinfo){
		
		//Make sure we have needed info
		if(matchinfo.size() % 3 != 0)		
			return null;
		
		//If so, initialise variables - for now just match percentage / matching nucleotides
		int matchnum = 0;
		int totalmatch = 0;
		int matchper = 0;
		
		
		int count = 1;
		
		ArrayList<Match> newmatches = new ArrayList<Match>();
		
		System.out.println(matchinfo.size());
		
		for(String n: matchinfo){
			
			if(count == 1){
				
				System.out.println("SCORE:" + n);
				
				
			}
			
			if(count == 2){
				
				System.out.println("GAPS:" + n);
				
				matchnum = Integer.parseInt(n.substring(n.indexOf("/") + 1, n.indexOf("(", n.indexOf("/")) - 1));
				totalmatch = Integer.parseInt(n.substring( n.indexOf("=") + 2, n.indexOf("/")));
				matchper =  (int) ((float) totalmatch/matchnum * 100);
				
				
				
				
				System.out.println("MatchNum: " + matchnum);
				System.out.println("MatchTotal: " + totalmatch);
				System.out.println("MatchPer: " + matchper);
			}
			
			if(count == 3){
				
				
				System.out.println("STRAND:" + n);
				
				//Now create a match
				newmatches.add(new Match(matchper, totalmatch));
				
				
			}
			
			
			
			
			//Reset/Increment Count
			if(count!=3)
				count++;			
			
			else
				count = 1;		
			
		}		
		
		return newmatches;
		
		
	}
	
	
	
	
	
	
	
	


}
