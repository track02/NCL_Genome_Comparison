
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
	
	//Difference between matching/total
	private int matchdiff;
	
	//Query Start
	private int qstart;
	
	//Query End
	private int qend;
	
	//Subject Start
	private int sstart;
	
	//Subject End
	private int send;
	
	//Difference between query/subject
	private int querydiff;
	
	//Difference between subject/query
	private int subdiff;
	
	//Boolean to indicate reversal (strand plus/minus)
	private boolean qstrand;
	private boolean substrand;
	
	//Feature Present in this match
	private String feature = " ";
	
	
	
	//Constructor
	public Match(int matchper, int matchbases, int total, int gaps, int matchdiff, int qstart, int qend, int substart, int subend, boolean qstrand, boolean substrand){
		
		this.matchper = matchper;
		this.matchbases = matchbases;
		this.total = total;		
		this.gaps = gaps;
		this.matchdiff = matchdiff;
		this.qstart = qstart;
		this.qend = qend;
		this.sstart = substart;
		this.send = subend;
		this.qstrand = qstrand;
		this.substrand = substrand;
		
		//Calculate query/subdiff
		//System.out.println(this.qend + "  " + this.qstart);
		//System.out.println(send + "  " + sstart);
		
		System.out.println(this.qend - this.qstart);
		System.out.println(this.sstart - this.send);
		
		//Both plus
		if(this.qstrand == true && this.substrand == true){
			
			querydiff = (this.qend - this.qstart) - ((send - sstart)); 
			subdiff = ((send - sstart)) - (this.qend - this.qstart);			
		}
		
		else if(this.qstrand == true && this.substrand ==false){
			
			querydiff = (this.qend - this.qstart) - ((sstart - send)); 
			subdiff = ((sstart - send)) - (this.qend - this.qstart); 			
			
		}
		else if(this.qstrand == false && this.substrand == true){
			
			querydiff = (this.qstart - this.qend) - ((send - sstart)); 
			subdiff = ((send - sstart)) - (this.qstart - this.qend); 			
		}
		else{
			
			querydiff = (this.qstart - this.qend + 1) - ((sstart - send)); 
			subdiff = ((sstart - send + 1)) - (this.qstart - this.qend);
			
			
		}
			
			
		
		

		
		
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

	public int getMatchdiff() {
		return matchdiff;
	}	
	
	public int getQDiff(){
		return querydiff;
	}
	
	public int getSDiff(){
		return subdiff;
	}
	
	public void setFeature(String newfeature){
		feature = newfeature;
	}
	
	public void addFeature(String addfeature){
		feature = feature + "/n" + addfeature;		
	}
	
	public String getFeature(){
		return feature;
	}
	
	public boolean getSubStrand(){
		return substrand;
	}
	
	public boolean getQStrand(){
		return qstrand;
	}
	
	
}
