import java.io.File;
import java.io.IOException;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

//Used to train a classifier
public class TrainNet {

	public static void train(Classifier c, int classIndex, String path) throws IOException, Exception{
		
		//Allows arff files to be converted to instances for use by classifier
		ArffLoader AL = new ArffLoader();
		
		//arff file to use
		File arffdata = new File("Training_Data/" + path);
		
		//Load file
		AL.setFile(arffdata);
		
		//Convert to dataset
		Instances trainingset = AL.getDataSet();
		
		//Set class index 
		trainingset.setClassIndex(classIndex);
		
		//Train the classifier
		c.buildClassifier(trainingset);
		
		
		
	}
}