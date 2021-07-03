package dependencies;

import java.util.ArrayList;

public class JustForTest {

	
	public static void main(String[] args) {
		ArrayList<String> x = new ArrayList<String>();
		x.add("yoyo");
		x.add("GL");
		System.out.println("OK:"+x.get(0));
		
		x.forEach(n->System.out.println(n));
	}
}
