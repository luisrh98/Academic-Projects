package es.iessoterohernandez.daw.endes.Cuenta_Tarjeta;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CuentaTest {
	private Cuenta cuenta;
	private Movimiento m;

	@BeforeEach
	void setUp() throws Exception {
		cuenta = new Cuenta("1234", "Raton Perez");
	}

	@AfterEach
	void tearDown() throws Exception {
		cuenta = null;
	}

	@Test
	void testIngresarDouble() throws Exception {
		// Saldo negativo
		try {
			cuenta.ingresar(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertEquals("No se puede ingresar una cantidad negativa", e.getMessage());
		}
		// Saldo positivo
		cuenta.ingresar(10);
		assertEquals(10, cuenta.getSaldo());
		//Comprobacion de que se añaden los movimientos
		assertEquals(1, cuenta.mMovimientos.size());
	}

	@Test
	void testRetirarDouble() throws Exception {
		// Saldo negativo
		try {
			cuenta.retirar(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertEquals("No se puede retirar una cantidad negativa", e.getMessage());
		}
		// Saldo insuficiente
		try {
			cuenta.retirar(10);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertEquals("Saldo insuficiente", e.getMessage());
		}
		// Saldo positivo
		cuenta.ingresar(15);
		cuenta.retirar(10);
		assertEquals(5, cuenta.getSaldo());
		//Comprobacion de que se añaden los movimientos
		assertEquals(2, cuenta.mMovimientos.size());
	}

	@Test
	void testIngresarStringDouble() throws Exception {
		// Saldo negativo
		try {
			cuenta.ingresar("Nomina", 0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertEquals("No se puede ingresar una cantidad negativa", e.getMessage());
		}
		// Saldo positivo
		assertTrue(cuenta.mMovimientos.isEmpty());
		cuenta.ingresar("Nomina", 10);
		assertEquals(10, cuenta.getSaldo());
		//Comprobacion de que se añaden los movimientos
		assertEquals(1, cuenta.mMovimientos.size());
	}

	@Test
	void testRetirarStringDouble() throws Exception {
		// Saldo negativo
		try {
			cuenta.retirar(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertEquals("No se puede retirar una cantidad negativa", e.getMessage());
		}
		// Saldo insuficiente
		try {
			cuenta.retirar(10);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertEquals("Saldo insuficiente", e.getMessage());
		}
		// Saldo positivo
		cuenta.ingresar(15);
		cuenta.retirar(10);
		assertEquals(5, cuenta.getSaldo());
		//Comprobacion de que se añaden los movimientos
		assertEquals(2, cuenta.mMovimientos.size());
	}

	@Test
	void testAddMovimiento() {
		cuenta.addMovimiento(m);
		assertTrue(cuenta.mMovimientos.contains(m));
	}

}
