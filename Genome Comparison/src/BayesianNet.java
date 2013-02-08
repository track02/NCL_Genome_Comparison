import weka.classifiers.bayes.BayesNet;
import weka.classifiers.bayes.net.BIFReader;

/*BayesianNet class provides operations for
 * initialising, using and training Bayesian Networks
 */

public class BayesianNet {

	//Static reader
	static BIFReader reader = new BIFReader();	

	//Reads in an existing Network
	public static BayesNet readNetwork(String filename) throws Exception{
		
		//Create a new network
		BayesNet network = new BayesNet();	
		
		//Initialise network using BIFXML file
		network = reader.processFile("BayesNet/" + filename);
		
		//Return
		return network;			
	}
	
	
	
	
	
	
	

}
