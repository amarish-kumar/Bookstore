package model;

import java.util.Map;
import java.util.Set;

public final class Calculation {
	public static int calculateCost(Map<Book,String> m){
		
		Set<Book> ks = m.keySet();
		int cost = 0;
		for(Book b : ks){
			Integer quan = Integer.parseInt(m.get(b));
			int p = b.getPrice()*quan;
			cost += p;
		}
		return cost;
		
	}
	
	public static int calculateCost(Book b, int quan){
		int result = b.getPrice()*quan;
		return result;
		
	}
}
