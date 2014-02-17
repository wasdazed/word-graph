import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.StringTokenizer;


public class GraphReader {
	
	public static graph read(String f) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(f));
		Hashtable<String,word> h = new Hashtable<String,word>();
		HashSet<word> starts = new HashSet<word>();
		HashSet<word> ends = new HashSet<word>();
		String l = br.readLine();
		word prevW=null;
		word w = null;
		while(l!=null){
			StringTokenizer st0 = new StringTokenizer(l,".?!");
			while(st0.hasMoreTokens()){
				String l0 = st0.nextToken();
				StringTokenizer st = new StringTokenizer(l0, " ,:;()\"");
				int k=0;
				int c = st.countTokens();
				while(st.hasMoreTokens()){
					String n = st.nextToken().toLowerCase();
					if (!h.containsKey(n)) {
						w = new word(n);
						h.put(n, w);
					}
					else{
						w = h.get(n);
					}
					if (prevW!=null) prevW.addConnection(w);
					if (k==0) starts.add(w);
					else if (k==(c-1)) ends.add(w);
					prevW=w;
					k++;
				}
			}
		l = br.readLine();
		}
		br.close();
		return(new graph(h,starts,ends));
	}

}
