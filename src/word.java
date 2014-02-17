import java.util.HashSet;
import java.util.Iterator;


public class word {
	
	public String name;
	public HashSet<word> connections;
	int sylabys=0;
	
//	public static final char[] RUS_VOWELS = new char[]{'а','о','э','и','у','ы','е','ё','ю','я'};
	public static final HashSet<Character> RUS_VOWELS = new HashSet<Character>();
	{
		RUS_VOWELS.add(new Character('а'));
		RUS_VOWELS.add(new Character('о'));
		RUS_VOWELS.add(new Character('э'));
		RUS_VOWELS.add(new Character('и'));
		RUS_VOWELS.add(new Character('у'));
		RUS_VOWELS.add(new Character('ы'));
		RUS_VOWELS.add(new Character('е'));
		RUS_VOWELS.add(new Character('ё'));
		RUS_VOWELS.add(new Character('ю'));
		RUS_VOWELS.add(new Character('я'));
	}
	
	public word(String n){
		this.name = n;
		connections = new HashSet<word>();
		sylabys = syllNumber_RUS();
	}
	
	public word getRandomConnection(){
		int n = connections.size();
		int k =(int)(Math.random()*n);
		word s = null;
		int i=0;
		for (Iterator<word> iterator = connections.iterator(); iterator.hasNext();) {
			s = iterator.next();
			if (i>k) break;
			i++;
		}
		return s;
	}
	
	public void addConnection(word w){
		connections.add(w);
	}
	
	public int syllNumber_RUS(){
		int vow=0;
		for (int j = 0; j < name.length(); j++) {
			if (RUS_VOWELS.contains(this.name.charAt(j))) vow++;
		}
		return vow;
	}

}
