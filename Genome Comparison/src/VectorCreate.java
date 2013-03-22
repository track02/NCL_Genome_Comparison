import weka.core.FastVector;
import weka.core.Attribute;

public class VectorCreate {

	public static FastVector createMatchVector(){
		
		//Declare attributes
		Attribute matchbases = new Attribute("Matching Bases");
		Attribute totalmatch = new Attribute("Total Bases");
		Attribute matchper = new Attribute("Percentage Match");
		Attribute matchdiff = new Attribute("Base Difference");
				
		FastVector fvClass = new FastVector(3);
		fvClass.addElement("Total Match");
		fvClass.addElement("Partial Match");
		fvClass.addElement("SNP");
		Attribute ClassAttribute = new Attribute("Class", fvClass);
						
		//Create Vector
		FastVector matchAttribs = new FastVector(5);
				
		//Add attributes to the vector
		matchAttribs.addElement(matchbases);
		matchAttribs.addElement(totalmatch);	
		matchAttribs.addElement(matchper);
		matchAttribs.addElement(matchdiff);
		matchAttribs.addElement(ClassAttribute);
				
		//Return Vector
		return matchAttribs;		
	}
}