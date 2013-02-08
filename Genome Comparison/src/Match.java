
public class Match {

	//Fields
	
	
	//Match %
	private int matchper;
	
	//No. Matching Bases
	private int matchbases;
	
	//Query Start
	private int qstart;
	
	//Query End
	private int qend;
	
	//Subject Start
	private int sstart;
	
	//Subject End
	private int send;
	
	//Constructor
	public Match(int matchper, int matchbases){
		
		this.matchper = matchper;
		this.matchbases = matchbases;
		
		
		
	}
	
	
	//Methods
	
	public int getMatchper(){
		return matchper;
	}
	
	public int getMatchbases(){
		return matchbases;
	}
	
	public int getQstart(){
		return qstart;
	}
	
	public int getQend(){
		return qend;
	}
	
	public int getSstart(){
		return sstart;
	}
	
	public int getSend(){
		return send;
	}	
}
