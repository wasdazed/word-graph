import java.util.HashSet;
import java.util.Iterator;


public class textGen {
	
	public static void genText(graph g, int l){
		for (int i = 0; i <l; i++) {
			String s = genLine(g);
			System.out.println(s);
		}
	}
	
	public static void genPoem(graph g, int l,int syl){
		for (int i = 0; i <l; i++) {
			String s = genLine(g,syl);
			System.out.println(s);
		}
	}
	
	public static void genPoem(graph g, int l,int syl1,int syl2){
		for (int i = 0; i <l; i++) {
			String s1 = genLine(g,syl1);
			String s2 = genLine(g,syl2);
			System.out.println(s1+", "+s2);

		}
	}
	
	public static String genLine(graph g){
		HashSet<word> starts = g.getStarters();
		HashSet<word> ends = g.getEnds();
		word start = getStart(starts);
		StringBuffer sb = new StringBuffer(start.name);
		word curr = start;
		int count = 0;
		while(true){
			word next = curr.getRandomConnection();
			if (next==null) break;
			sb.append(" "+next.name);
			count++;
			if (ends.contains(next)){
				if (Math.random()<(double)count/20) break;
			}
			curr=next;
		}
		sb.append(".");
		return sb.toString();
	}
	
	public static String genLine(graph g,int l){
		
		word start = getStart(g);
		StringBuffer sb = new StringBuffer(start.name);
		word curr = start;
		int count_s = start.sylabys;
//		System.out.println(start.name+" "+start.sylabys);
		while(true){
			word next = curr.getRandomConnection();
			if ((count_s+next.sylabys) <=l){
				sb.append(" "+next.name);
				count_s+=next.sylabys;
//				System.out.println(next.name+" "+next.sylabys);
				curr=next;
				if ((count_s) ==l) break;
			}
			else continue;			
		}
//		System.out.println(count_s);
		return sb.toString();
	}
	
	private static word getStart(HashSet<word> starts){
		int n = starts.size();
		int k =(int)(Math.random()*n);
		word s = null;
		int i=0;
		for (Iterator<word> iterator = starts.iterator(); iterator.hasNext();) {
			s = iterator.next();
			if (i>k) break;
			i++;
		}
		return s;
	}
	
	private static word getStart(graph g){
		int n = g.v.keySet().size();
		int k =(int)(Math.random()*n);
		String s = null;
		int i=0;
		for (Iterator<String> iterator = g.v.keySet().iterator(); iterator.hasNext();) {
			s = iterator.next();
			if (i>k) break;
			i++;
		}
		return g.v.get(s);
	}
}
