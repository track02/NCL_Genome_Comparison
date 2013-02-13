import weka.classifiers.bayes.BayesNet;

public class Main {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		BayesNet testing = BayesianNet.readNetwork("Test_Net.xml");
				
	
		
		BlastReader.setComp("Test_Comparison_Files/GRJW41KA015-Alignment.txt");
		BlastReader.parseComp();
		
	}

}
