package modelTest;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import bean.Book;
import model.Calculation;

public class CalculationTest {
	
	private Book b;
	private Map<Book,String> m;
	@Before
	public void setUp() throws Exception {
		b = new Book();
		m = new HashMap<Book,String>();
		b.setBid("b001");
		b.setCatergory("science");
		b.setPrice(10);
		b.setQuan(1);
		m.put(b,"2");
	}

	@Test
	public void testCalculateCost() {
		assertEquals(10,Calculation.calculateCost(b,1));
		
	}
	
	@Test
	public void testCalculateCost2() {
		assertEquals(20,Calculation.calculateCost(m));
		
	}

}
