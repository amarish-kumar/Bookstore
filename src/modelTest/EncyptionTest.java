package modelTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Calculation;
import model.Encryption;

public class EncyptionTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testEncyption() {
		String expected = "494414ded24da13c451b13b424928821351c78fce49f93d9e1b55f102790c206";
		String actual = Encryption.encrypt("lala");
		assertEquals(expected,actual);

	}

}
