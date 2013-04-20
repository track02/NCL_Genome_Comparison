import java.io.File;
import java.io.IOException;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

//Used to train a classifier
public class TrainNet {

	private static File testdata;
	
	
	public static void train(Classifier c) throws IOException, Exception{
		
		//Allows arff files to be converted to instances for use by classifier
		ArffLoader AL = new ArffLoader();
		
		//arff file to use
		File arffdata = testdata;
		
		//Load file
		AL.setFile(arffdata);
		
		//Convert to dataset
		Instances trainingset = AL.getDataSet();
		
		//Set class index 
		trainingset.setClassIndex(6);
		
		//Train the classifier
		c.buildClassifier(trainingset);				
		
		System.out.println("Training Complete!");
		
	}
	
	public static void setFile(File f){
		testdata = f;
	}
	
}