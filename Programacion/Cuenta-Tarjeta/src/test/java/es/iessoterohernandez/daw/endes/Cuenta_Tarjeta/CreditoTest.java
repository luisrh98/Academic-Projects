package es.iessoterohernandez.daw.endes.Cuenta_Tarjeta;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CreditoTest {
	private Cuenta cuenta;
	private Credito credito;

	@BeforeEach
	public void setUp() {
		cuenta = new Cuenta("1234", "Titular de la cuenta");
		credito = new Credito("4321", "Titular de la tarjeta", new Date(), 1000.0);
		credito.setCuenta(cuenta);
	}

	@AfterEach
	public void finish() {
		cuenta = null;
		credito = null;
	}

	// En este Test devuelve solo la comision de la tarjeta!!
	@Test
	public void testRetirarCredito() {
		try {
			credito.retirar(500);
			assertEquals(25, credito.getSaldo());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// Saldo negativo
		try {
			credito.retirar(1100.0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertEquals("Credito insuficiente", e.getMessage());
		}

	}

	@Test
	public void testIngresarCredito() {
		try {
			credito.ingresar(200.0);
		} catch (Exception e) {
			fail("Excepción inesperada: " + e.getMessage());
		}
		assertEquals(200.0, credito.getSaldo());
	}

	@Test
	public void testPagoEnEstablecimientoCredito() {
		try {
			credito.pagoEnEstablecimiento("Tienda de prueba", 100.0);
		} catch (Exception e) {
			fail("Excepción inesperada: " + e.getMessage());
		}
		assertEquals(100.0, credito.getSaldo());
	}

	@Test
	public void testLiquidar() {
		try {
			if (credito.mCuentaAsociada != null) {
				cuenta.ingresar(1000);
				credito.ingresar(500);
				credito.liquidar(1, 2024);
				assertEquals(1500, cuenta.getSaldo());
			} else {
				System.out.println("La cuenta asociada es nula, no se puede realizar el ingreso");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
