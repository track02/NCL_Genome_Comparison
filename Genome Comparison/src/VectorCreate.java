import weka.core.FastVector;
import weka.core.Attribute;

public class VectorCreate {

	public static FastVector createMatchVector(){
		
		//Declare attributes
		Attribute matchbases = new Attribute("Matching Bases");
		Attribute totalmatch = new Attribute("Total Bases");
		Attribute matchper = new Attribute("Percentage Match");
		Attribute matchdiff = new Attribute("Base Difference");
		Attribute qdiff = new Attribute("Query Difference");
		Attribute sdiff = new Attribute("Subject Difference");

		
				
		FastVector fvClass = new FastVector(5);
		fvClass.addElement("Total Match");
		fvClass.addElement("Partial Match");
		fvClass.addElement("SNP");
		fvClass.addElement("SB-IN");
		fvClass.addElement("SB-DEL");
		Attribute ClassAttribute = new Attribute("Class", fvClass);
						
		//Create Vector
		FastVector matchAttribs = new FastVector(7);
				
		//Add attributes to the vector
		matchAttribs.addElement(matchbases);
		matchAttribs.addElement(totalmatch);	
		matchAttribs.addElement(matchper);
		matchAttribs.addElement(matchdiff);
		matchAttribs.addElement(qdiff);
		matchAttribs.addElement(sdiff);
		matchAttribs.addElement(ClassAttribute);		
		
		//Return Vector
		return matchAttribs;		
	}
	
	//For highlighting Insertion/Deletion
	public static FastVector createMatchVector2(){
		
		//Declare attributes
		Attribute qstart = new Attribute("Query Start");
		Attribute qend = new Attribute("Query End");
		Attribute subend = new Attribute("Subject Start");
		Attribute substart = new Attribute("Subject End");
		
		//These attributes are used to store whether the compared match query/subject overlap or are adjacent
		Attribute nextq = new Attribute("Next Query Start");
		Attribute nexts = new Attribute("Next Subject Start");
		
		
		//Class attribute		
		FastVector fvClass = new FastVector(5);
		fvClass.addElement("Insertion");
		fvClass.addElement("Deletion");
		fvClass.addElement("Query Duplication");
		fvClass.addElement("Subject Duplication");
		fvClass.addElement("Nothing found");
		Attribute ClassAttribute = new Attribute("Class", fvClass);
						
		//Create Vector
		FastVector matchAttribs = new FastVector(7);
				
		//Add attributes to the vector
		matchAttribs.addElement(qstart);
		matchAttribs.addElement(qend);	
		matchAttribs.addElement(substart);
		matchAttribs.addElement(subend);
		matchAttribs.addElement(nextq);
		matchAttribs.addElement(nexts);
		matchAttribs.addElement(ClassAttribute);
				
		//Return Vector
		return matchAttribs;		
	}
}

