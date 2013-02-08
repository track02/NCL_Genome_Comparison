import weka.classifiers.bayes.BayesNet;

public class Main {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		BayesNet testing = BayesianNet.readNetwork("Test_Net.xml");
				
	
		
		FReader.setComp("Test_Comparison_Files/GRJW41KA015-Alignment.txt");
		FReader.parseComp();
		
	}

}
