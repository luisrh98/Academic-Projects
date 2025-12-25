package es.iessoterohernandez.daw.endes.boletinJUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SubscripcionTest {

	private Subscripcion s1;

	@BeforeEach
	public void setUp() {
		s1 = new Subscripcion(120, 12);
	}

	@AfterEach
	public void finish() {
		s1 = null;
	}

	@Test
	public void testPrecioPorMes() {
		// Verificar que el cálculo del precio por mes es correcto y el resto es 0
		assertEquals(10.0, s1.precioPorMes());

		// Casos de prueba adicionales

		// Verificacion siendo el periodo y el precio 0
		s1 = new Subscripcion(0, 0);
		assertEquals(0.0, s1.precioPorMes());

		// Verificar que el precio por mes es 0 cuando el periodo es 0 y el precio es
		// positivo
		s1 = new Subscripcion(0, 100);
		assertEquals(0.0, s1.precioPorMes());

		// Verificar que el precio por mes es 0 cuando el precio es 0 y el periodo es
		// positivo
		s1 = new Subscripcion(12, 0);
		assertEquals(0.0, s1.precioPorMes());

		// Verificar cuando el resto es > 0
		s1 = new Subscripcion(50, 4);
		assertEquals(13.5, s1.precioPorMes());
	}

	@Test
	public void testCancel() {
		s1.cancel();
		assertEquals(0.0, s1.precioPorMes());
	}
}
