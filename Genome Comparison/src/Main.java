import java.util.ArrayList;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.BayesNet;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

public class Main {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
				
		//Sets the comparison file to read from
		BlastReader.setComp("Test_Comparison_Files/GRJW41KA015-Alignment.txt");
		
		//Reads from the comparison file and returns a number of matches
		ArrayList<Match> matches = BlastReader.parseComp();
		
		//Now we create a feature vector holding the attributes of our problem
			//For now we're looking at the number of bases considered and how many match
			//and if we have a match {total, partial, no match} this attribute is our class
		FastVector attribs = VectorCreate.createMatchVector();
		
		//Create a set of n instances where n is the number of returned matches
		Instances dataSet = new Instances("MatchSet", attribs, matches.size());
		
		//Set class index - second attribute (Match / Partial / No Match)
		dataSet.setClassIndex(2);		
		
		//Initialise the values of each instance to that of a match object
		PopulateSet.popMatchSet(dataSet, attribs, matches);
	
		//Load Bayesian Network - this is our classifier		
		Classifier bnet = BayesianNet.DeserialiseNet("MatchNet");
		
		//Array to store classifier results
		double[] dist;
		
		//Now loop through our instances and pass to the classifier
		for(int i=0; i <dataSet.numInstances(); i++){			
			
			dist = bnet.distributionForInstance(dataSet.instance(i));
			
			//Display results
			System.out.println("Matching Bases: " + dataSet.instance(i).value(0) + "/" + dataSet.instance(i).value(1));
			System.out.println("\nResults:");
			System.out.println("Total Match: " + dist[0]);
			System.out.println("Partial Match: " + dist[1]);
			System.out.println("No Match: " + dist[2] + "\n");
					
		}
		
		//Serialise the net - save
		BayesianNet.SerialiseNet("MatchNet", bnet);			 
	}
}