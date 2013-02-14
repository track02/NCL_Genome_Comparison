import weka.core.FastVector;
import weka.core.Attribute;


public class VectorCreate {

	public static FastVector createMatchVector(){
		
		//Declare attributes
		Attribute matchper = new Attribute("Match Percentage");
		Attribute totalmatch = new Attribute("Total Matches");
		
		//Create Vector
		FastVector matchAttribs = new FastVector(4);
		
		//Add attributes to the vector
		matchAttribs.addElement(matchper);
		matchAttribs.addElement(totalmatch);				
		
		return matchAttribs;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
