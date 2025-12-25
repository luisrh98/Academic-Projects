package es.iessoterohernandez.daw.endes.Cuenta_Tarjeta;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DebitoTest {
    private Cuenta cuenta;
    private Debito debito;

    @BeforeEach
    void setUp() throws Exception {
        cuenta = new Cuenta("1234", "Titular de la cuenta");
        cuenta.ingresar(1000.0);
        debito = new Debito("4321", "Titular de la tarjeta", new Date());
        debito.setCuenta(cuenta);
    }

    @AfterEach
    void tearDown() {
        cuenta = null;
        debito = null;
    }

    @Test
    void testRetirar() throws Exception {
        debito.retirar(500.0);
        assertEquals(500.0, cuenta.getSaldo());
    }

    //En el metodo ingresar habia una funcion mal ya que se llamaba a una funcion que retiraba en vez de ingresar, entonces hacia el mismo efecto que el test de arriba
    //Y lo he cambiado del metodo para que funcione y este bien
    @Test
    void testIngresar() throws Exception {
        debito.ingresar(200.0);
        assertEquals(1200.0, cuenta.getSaldo());
    }

    @Test
    void testPagoEnEstablecimiento() throws Exception {
        debito.pagoEnEstablecimiento("Tienda", 100.0);
        assertEquals(900.0, cuenta.getSaldo());
    }
}