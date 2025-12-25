package es.iessoterohernandez.daw.endes.boletinJUnit;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class PilaTest {
	
	private static Pila pila;

	@BeforeAll
	static void init() throws Exception {
		pila = new Pila();
	}

	@AfterAll
	static void finish() throws Exception {
		pila = null;
	}
	@Test
	public void pruebaTest() {
		assertEquals(true, pila.isEmpty());
		pila.push(5);
		assertEquals(false, pila.isEmpty());
		pila.pop();
		assertEquals(true, pila.isEmpty());
	}
	
	@Test
    void testPush() {
        assertTrue(pila.isEmpty());

        // Añadir número a la pila (5)
        pila.push(5);
        assertFalse(pila.isEmpty());

        // Sacar el último número añadido (5)
        assertEquals(5, pila.pop());

        // La pila debe estar vacía después de sacar el único elemento
        assertTrue(pila.isEmpty());

        // Añadir número es 2 (no debería añadirse)
        pila.push(2);
        assertTrue(pila.isEmpty());

        // Añadir número es 20 (no debería añadirse)
        pila.push(20);
        assertTrue(pila.isEmpty());

        // Añadir número es 10 (debería añadirse)
        pila.push(10);
        assertFalse(pila.isEmpty());

        // La pila debería estar vacía después de sacar el único elemento
        assertEquals(10, pila.pop());
        assertTrue(pila.isEmpty());
    }

	@Test
	void testPop() {
		//Si pila esta vacia
		assertEquals(null, pila.pop());
		//Si no esta vacia
		assertEquals(true, pila.isEmpty());
		pila.push(5);
		assertEquals(false, pila.isEmpty());
		pila.pop();
		assertEquals(true, pila.isEmpty());
	}

	@Test
	void testIsEmpty() {
		//Esta vacio 
		assertEquals(true, pila.isEmpty());
		//No esta vacio
		assertEquals(true, pila.isEmpty());
	}

	@Test
    void testTop() {
        assertNull(pila.top()); // La pila está vacía, debe devolver null

        pila.push(7);
        assertEquals(7, pila.top()); // Devuelve el último elemento añadido (7)

        pila.pop(); // Sacar el único elemento en la pila
        assertNull(pila.top()); // La pila está vacía después de sacar el único elemento
    }

}