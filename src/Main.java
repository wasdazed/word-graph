import java.io.IOException;
import java.util.Iterator;


public class Main {
	
	public static void main(String[] args) {
//		String f = "test.txt";
//		String f = "pg174.txt";
		String f = "Besi.txt";
		try {
			graph g = GraphReader.read(f);
		/*	for (Iterator<String> iterator = g.v.keySet().iterator(); iterator.hasNext();) {
				String type = iterator.next();
				word w = g.v.get(type);
				System.out.println(w.name);
				for (Iterator<word> iterator2 = w.connections.iterator(); iterator2.hasNext();) {
					word type2 = iterator2.next();
					System.out.println("\t"+type2.name);
				}
			}*/
//			textGen.genPoem(g, 10,7);
			textGen.genPoem(g, 10,9,8);
//			textGen.genText(g, 10);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
