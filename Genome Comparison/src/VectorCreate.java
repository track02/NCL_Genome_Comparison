import weka.core.FastVector;
import weka.core.Attribute;

public class VectorCreate {

	public static FastVector createMatchVector(){
		
		//Declare attributes
		Attribute matchbases = new Attribute("Matching Bases");
		Attribute totalmatch = new Attribute("Total Bases");
		Attribute matchper = new Attribute("Percentage Match");
				
		FastVector fvClass = new FastVector(3);
		fvClass.addElement("Total Match");
		fvClass.addElement("Partial Match");
		fvClass.addElement("No Match");
		Attribute ClassAttribute = new Attribute("Class", fvClass);
						
		//Create Vector
		FastVector matchAttribs = new FastVector(4);
				
		//Add attributes to the vector
		matchAttribs.addElement(matchbases);
		matchAttribs.addElement(totalmatch);	
		matchAttribs.addElement(matchper);
		matchAttribs.addElement(ClassAttribute);
				
		//Return Vector
		return matchAttribs;		
	}
}