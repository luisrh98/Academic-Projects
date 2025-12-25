package es.iessoterohernandez.daw.endes.boletinJUnit;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class OperadorAritmeticoTest {

	private static OperadorAritmetico oa1;
	
	@BeforeAll
	static void init() throws Exception {
		oa1 = new OperadorAritmetico();
	}

	@AfterAll
	static void finish() throws Exception {
		oa1 = null;
	}

	@Test
	void testSuma() {
		assertEquals(3, oa1.suma(1, 2));
	}

	@Test
	void testDivision() throws Exception{
		//Cuaando el divisor es 0
		assertThrows(Exception.class, () -> {
            oa1.division(1, 0);
        });
		//Cuando el divisor es mayor que 0
		assertEquals(4, oa1.division(8, 2));
	}

}
