
public class Match {

	//Fields
	
	//Match %
	private int matchper;
	
	//No. Matching Bases
	private int matchbases;
	
	//Total No. Bases
	private int total;
	
	//No. Gaps in Match
	private int gaps;
	
	//Query Start
	private int qstart;
	
	//Query End
	private int qend;
	
	//Subject Start
	private int sstart;
	
	//Subject End
	private int send;
	
	//Constructor
	public Match(int matchper, int matchbases, int total, int gaps){
		
		this.matchper = matchper;
		this.matchbases = matchbases;
		this.total = total;		
		this.gaps = gaps;
	}	
	
	//Methods - Mutators / Accessors	
	public int getMatchper(){
		return matchper;
	}
	
	public int getMatchbases(){
		return matchbases;
	}
	
	public int getTotal(){
		return total;
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
