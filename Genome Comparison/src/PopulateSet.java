import java.util.ArrayList;

import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;


public class PopulateSet {	
	
	public static Instances popMatchSet(Instances i, FastVector attrib, ArrayList<Match> matches){
				
		//Loop through each match, create an instance and add it to the set
		for(Match m: matches){
			
			//Create the instance and set attributes
			Instance newmatch = new Instance(3);
			newmatch.setValue((Attribute) attrib.elementAt(0), m.getMatchbases());
			newmatch.setValue((Attribute) attrib.elementAt(1), m.getTotal());
			//We don't know if this is a match - so we leave last attribute empty
						
			//Add to the set
			i.add(newmatch);						
		}		
		
		//Return the set
		return i;
		
	}
}