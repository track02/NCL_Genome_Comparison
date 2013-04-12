import java.util.ArrayList;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

public class PopulateSet {	
	
	//Populates a set of instances match information
	public static Instances popMatchSet(Instances i, FastVector attrib, ArrayList<Match> matches){
				
		//Loop through each match, create an instance and add it to the set
		for(Match m: matches){
			
			//Create the instance and set attributes
			Instance newmatch = new Instance(7);			
			newmatch.setValue((Attribute) attrib.elementAt(0), m.getMatchbases());
			newmatch.setValue((Attribute) attrib.elementAt(1), m.getTotal());
			newmatch.setValue((Attribute) attrib.elementAt(2), m.getMatchper());
			newmatch.setValue((Attribute) attrib.elementAt(3), m.getMatchdiff());
			newmatch.setValue((Attribute) attrib.elementAt(4), m.getQDiff());
			newmatch.setValue((Attribute) attrib.elementAt(5), m.getSDiff());
			//We don't know what type of match we have - so we leave last attribute empty
						
			//Add to the set
			i.add(newmatch);						
		}		
		
		//Return the set
		return i;		
	}

	//Populates Instances with base locations
	public static Instances popMatchSet2(Instances i, FastVector attrib, ArrayList<Match> matches){
		
		//Loop through each match, create an instance and add it to the set
		for(Match m: matches){
			
			//Create the instance and set attributes
			Instance newmatch = new Instance(6);
			newmatch.setValue((Attribute) attrib.elementAt(0), m.getQstart());
			newmatch.setValue((Attribute) attrib.elementAt(1), m.getQend());
			newmatch.setValue((Attribute) attrib.elementAt(2), m.getSstart());
			newmatch.setValue((Attribute) attrib.elementAt(3), m.getSend());
			//We don't know what feature we have - so we leave this attribute empty
			//We also don't know what the next subject/query values are, leave these attributes empty too
			
			//Add to the set
			i.add(newmatch);						
		}		
		
		//Return the set
		return i;		
	}
}


