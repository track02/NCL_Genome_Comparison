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
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
				
		//Sets the comparison file to read from
		BlastReader.setComp("Test_Comparison_Files/GRJW41KA015-Alignment.txt");
		
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
	
		//Load Bayesian Network - this is our classifier		
		Classifier bnet = BayesianNet.DeserialiseNet("MatchNet");
		
		Classifier bnet2 = BayesianNet.DeserialiseNet("IDNet");
		
		//Train Network
		
		//TrainNet.train(bnet,4, "MatchTraining.arff");		
		//TrainNet.train(bnet2, 6, "MatchTraining2.arff");
				
		//Array to store classifier results
		double[] dist;
		double[] dist2;
		
		//Looking at single matches
		//Features - Match / SNP / SB-IN / SB-DEL
		
		//If base is missing in subject - 
		//If base is missing in query - 
		
		
		//Now loop through our instances and pass to the classifier
		for(int i=0; i <dataSet.numInstances(); i++){						
			
			dist = bnet.distributionForInstance(dataSet.instance(i));
						
			//Display results
			System.out.println("\nMatching Bases: " + dataSet.instance(i).value(0) + "/" + dataSet.instance(i).value(1));
			System.out.println("Match Percentage: " + dataSet.instance(i).value(2) + "%");
			System.out.println("Base Difference: " + dataSet.instance(i).value(3));
			System.out.println("\nResults:");
			System.out.println("Total Match: " + dist[0]);
			System.out.println("Partial Match: " + dist[1]);
			System.out.println("SNP: " + dist[2]);
			System.out.println("SB-IN: " + dist[3]);
			System.out.println("SB-DEL: " + dist[4]);
			System.out.println("___________________________");
			
		}
		
		
		//Comparing matches against matches
		
		for(Match m: matches){
			
			System.out.println(m.getQstart() + " " + m.getQend() + " " + m.getSstart() + " " + m.getSend());
			
		}
		
		//For each match
		for(int i=0; i <dataSet.numInstances(); i++){
			
			
			dist2 = bnet2.distributionForInstance(dataSet2.instance(i));
			
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
			
			System.out.println(qstart + " " + qend + " " + substart + " " + subend);
						
			
			//Compare it against every other match
			for(int j=0; j<dataSet2.numInstances(); j++){
				

				
				
				//Except itself
				if(j == i){}
				
				//Examine Query Start / Subject Start positions of 2nd match
				//Alter attribute values depending on location
				//0 = not nearby or overlap
				//0.5 = within limit
				//1 = adjacent
				
				else{			
					
					//Initialise variables 
					nextqstart = matches.get(j).getQstart();
					nextqend = matches.get(j).getQend();
					nextsubstart = matches.get(j).getSstart();
					nextsubend = matches.get(j).getSend();
										
					//Want the distance to the next query/subject match 
					qdistance = (nextqstart - qend)-1;
					sdistance = (nextsubstart - subend) -1;		
					
					//If adjacent
					if(qdistance == 0)						
						dataSet2.instance(i).setValue(4, 1);						
					
					//If within limit
					else if(qdistance <= limit)				
						dataSet2.instance(i).setValue(4, 0.5);				
					
					//If adjacent
					if(sdistance == 0)
						dataSet2.instance(i).setValue(5, 1);						
					
					//If within limit
					else if(sdistance <= limit)
						dataSet2.instance(i).setValue(5, 0.5);							
					
				}				
			}			
		}
		
		//Serialise the net - save
		BayesianNet.SerialiseNet("MatchNet", bnet);	
		BayesianNet.SerialiseNet("IDNet", bnet2);
	}
	
	public static void setlimit(int dist){
		limit = dist;
	}
	
	
	
}