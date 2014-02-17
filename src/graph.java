import java.util.HashSet;
import java.util.Hashtable;


public class graph {

	public Hashtable<String,word> v;
	
	public HashSet<word> starts;
	public HashSet<word> ends;
	
	public graph(Hashtable<String,word> h,HashSet<word> s,HashSet<word> e){
		this.v=h;
		this.starts=s;
		this.ends=e;
	}
	
	public HashSet<word> getStarters(){
		return starts;
	}
	
	public HashSet<word> getEnds(){
		return ends;
	}
}
