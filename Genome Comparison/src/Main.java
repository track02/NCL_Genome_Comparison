import java.util.ArrayList;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.BayesNet;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.classifiers.bayes.BayesNet;

public class Main {

	private static int limit = 1;

	private static Display display;
	//private static Classifier bnet;
	
	//private static Classifier bnet2;
	
	
	public static void main(String[] args) throws Exception {
		
		//Read in saved Bayesnets
		
		//Set up GUI
		display = new Display();
		
		//Pass networks
		display.setNetworks(BayesianNet.DeserialiseNet("MatchNet"),BayesianNet.DeserialiseNet("IDNet") );
		
		display.drawWindow();
	
	}
	
		
		
	public static void run(Classifier net1, Classifier net2) throws Exception {
		//Sets the comparison file to read from
		//BlastReader.setComp("Test_Comparison_Files/Test Data - Duplication");
		
		//Reads from the comparison file and returns a number of matches
		ArrayList<Match> matches = BlastReader.parseComp();
		
		//Now we create feature vectors holding the attributes of our problems
		
		//These attributes are for examining single features - looking for total match / snp / partial match
		FastVector attribs = VectorCreate.createMatchVector();
		
		//These attributes are for examining two matches at a time - looking for insertions / deletions
		FastVector attribs2 = VectorCreate.createMatchVector2();
		
		
		//Create two set of n instances where n is the number of returned matches		
		Instances dataSet = new Instances("MatchSet", attribs, matches.size());
		Instances dataSet2 = new Instances("MatchSet2", attribs2, matches.size());
		
		//Set class index - fourth attribute (Match / Partial / No Match)	
		dataSet.setClassIndex(4);		
		
		//Set class index - fourth attributes Insertion / Deletion / N/A
		dataSet2.setClassIndex(6);
		
		//Initialise the values of each instance to that of a match object
		PopulateSet.popMatchSet(dataSet, attribs, matches);
		
		PopulateSet.popMatchSet2(dataSet2, attribs2, matches);
	
				
		//Array to store classifier results
		double[] dist;
		double[] dist2;
		
		//Looking at single matches
		//Features - Match / SNP / SB-IN / SB-DEL
		
		//If base is missing in subject - 
		//If base is missing in query - 
		
		
		//Now loop through our instances and pass to the classifier
		for(int i=0; i <dataSet.numInstances(); i++){						
			
			System.out.println("QDiff - " + matches.get(i).getQDiff() + "\nSDiff - " + matches.get(i).getSDiff() + "\n\n\n");
			
			dist = net1.distributionForInstance(dataSet.instance(i));
						
			String results;
			//Display results
			results = "";
			results = "\nMatching Bases: " + dataSet.instance(i).value(0) + "/" + dataSet.instance(i).value(1);
			results = results + ("\nMatch Percentage: " + dataSet.instance(i).value(2) + "%");
			results = results + ("\nBase Difference: " + dataSet.instance(i).value(3));
			results = results + ("\nMatch Query Start: " + matches.get(i).getQstart());
			results = results + ("\nMatch Query End: " + matches.get(i).getQend());
			results = results + ("\nMatch Subject Start: " + matches.get(i).getSstart());
			results = results + ("\nMatch Subject End: " + matches.get(i).getSend());
			results = results + ("\n\nResults:");
			results = results + ("\nTotal Match: " + dist[0]);
			results = results + ("\nPartial Match: " + dist[1]);
			results = results + ("\nSNP: " + dist[2]);
			results = results + ("\nSB-IN: " + dist[3]);
			results = results + ("\nSB-DEL: " + dist[4]);
			results = results + ("\n___________________________");
			
			display.setResultText(results);
			
		}
		
		
		//Comparing matches against matches

		
		//For each match
		for(int i=0; i <dataSet.numInstances(); i++){
				
			String results;
			
			//Values for this match (i)
			int qstart = matches.get(i).getQstart();
			int qend = matches.get(i).getQend();
			int substart = matches.get(i).getSstart();
			int subend = matches.get(i).getSend();
			
			//Distance from qend to nextqstart
			int qdistance;
			//Distance from send to nextsubstart
			int sdistance;
			
			int hold = 0;
			
			//Check for reversal
			if(matches.get(i).getQStrand() == false){				
				hold = qstart;
				qstart = qend;
				qend = hold;				
			}
			
			if(matches.get(i).getSubStrand() == false){
				hold = substart;
				substart = subend;
				subend = hold;					
			}
			
			//Values for match we are comparing to (j)
			int nextqstart;
			int nextqend;
			int nextsubstart;
			int nextsubend;
			
			
						
			
			//Compare it against every other match
			for(int j=0; j<dataSet2.numInstances(); j++){
				

				System.out.println(j);
				
				//Except itself
				if(j == i){}
				
				//Examine Query Start / Subject Start positions of 2nd match
				//Alter attribute values depending on location
				//0 = not nearby or overlap
				//1 = within limit/adjacent
				
				//2 = This match has been duplicated
				//3 = No duplication
				
				else{			
					
					//Initialise variables 
					nextqstart = matches.get(j).getQstart();
					nextqend = matches.get(j).getQend();				
					nextsubstart = matches.get(j).getSstart();
					nextsubend = matches.get(j).getSend();
					
					//Reverse depending on strand
					if(matches.get(j).getQStrand() == false){				
						hold = nextqstart;
						nextqstart = nextqend;
						nextqend = hold;				
					}
					
					if(matches.get(j).getSubStrand() == false){
						hold = nextsubstart;
						nextsubstart = nextsubend;
						nextsubend = hold;					
					}
					
					//Want the shortest distance distance to the next query/subject match 
					
					//First start to end
					qdistance = Math.abs((nextqstart - qend)-1);
					sdistance = Math.abs((nextsubstart - subend) -1);	
					
					
					//Start to start
					if(Math.abs((nextqstart - qend)-1) < qdistance)
						qdistance = Math.abs((nextqstart - qend)-1);
					if(Math.abs((nextsubstart - subend)-1) < sdistance)
						sdistance = Math.abs((nextsubstart - subend)-1);
						
					//End to end
					if(Math.abs((nextqend - qend)-1) < qdistance)
						qdistance = Math.abs((nextqend - qend)-1);
					if(Math.abs((nextsubend - subend)-1) < sdistance)
						sdistance = Math.abs((nextsubend - subend)-1);
										
					//End to start
					if(Math.abs((nextqend - qstart)-1) < qdistance)
						qdistance = Math.abs((nextqend - qstart)-1);
					if(Math.abs((nextsubend - substart)-1) < sdistance)
						sdistance = Math.abs((nextsubend - substart)-1);
										
					
					System.out.println("\nNext Q Start: " + nextqstart + "\nQ End " + qend + "\nDistance Between: " + qdistance);
					System.out.println("\nNext S Start: " + nextsubstart + "\nS End " + subend + "\nDistance Between: " + sdistance);
					
					//If adjacent
					if(qdistance == 0)						
						dataSet2.instance(i).setValue(4, 1);						
					
					//If within limit
					else if(qdistance <= limit)				
						dataSet2.instance(i).setValue(4, 1);				
					
					//If adjacent
					if(sdistance == 0)
						dataSet2.instance(i).setValue(5, 1);						
					
					//If within limit
					else if(sdistance <= limit)
						dataSet2.instance(i).setValue(5, 1);		
					
					//If a duplication is present overwrite ins/del changes
					if(qstart == nextqstart && qend == nextqend){
						
						dataSet2.instance(i).setValue(4,2);
						dataSet2.instance(i).setValue(5,3);
						
					}
					
					else if (substart == nextsubstart && subend == nextsubend){
						
						dataSet2.instance(i).setValue(4,3);
						dataSet2.instance(i).setValue(5,2);					
						
					}
					
					
					dist2 = net2.distributionForInstance(dataSet2.instance(i));
					
					
					results = "";
					results = results + ("\n\nInsertion: " + dist2[0]);
					results = results +("\nDeletion: " + dist2[1]);
					results = results +("\nQuery Duplication: " + dist2[2]);
					results = results +("\nSubject Duplication: " + dist2[3]);
					results = results +("\nN/A: " + dist2[4]);
					display.setResultText(results);
					
				}				
			}			
		}
		
		//Serialise the net - save
		BayesianNet.SerialiseNet("MatchNet", net1);	
		BayesianNet.SerialiseNet("IDNet", net2);
	}
	
	public static void setlimit(int dist){
		limit = dist;
	}
	

	

	
	
}