import weka.classifiers.Classifier;
import weka.classifiers.bayes.BayesNet;
import weka.classifiers.bayes.net.BIFReader;

/*BayesianNet class provides operations for
 * initialising, using and training Bayesian Networks
 */

public class BayesianNet {

	//Static reader
	static BIFReader reader = new BIFReader();	

	//Reads in an existing Network from an XML file - created via WEKA gui
	public static BayesNet readNetwork(String filename) throws Exception{
		
		//Create a new network
		BayesNet network = new BayesNet();	
		
		//Initialise network using BIFXML file
		network = reader.processFile("BayesNet/" + filename);
		
		//Return
		return network;			
	}	
	
	//Serialises Classifier/BayesNet
	public static void SerialiseNet(String name, Classifier c) throws Exception{
		weka.core.SerializationHelper.write("BayesNet/"+name, c);
	}
	
	
	//Deserialises Classifier/BayesNet
	public static Classifier DeserialiseNet(String name) throws Exception{
		
		return (Classifier) weka.core.SerializationHelper.read("BayesNet/"+name);
		
	}
}
