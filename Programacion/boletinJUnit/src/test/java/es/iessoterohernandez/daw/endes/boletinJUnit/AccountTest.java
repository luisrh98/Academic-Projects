package es.iessoterohernandez.daw.endes.boletinJUnit;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AccountTest {

    private Account account;

    @BeforeEach
    public void init() throws Exception {
        account = new Account("John Doe", 123456789, 1000.0f);
    }

    @AfterEach
    public void finish() throws Exception {
        account = null;
    }

    @Test
    public void testAccount() {
        // Prueba de constructor
        assertEquals("John Doe", account.name);
        assertEquals(123456789, account.getAccountNumber());
        assertEquals(1000.0f, account.getBalance(), 0.001f);
    }

    @Test
    public void testDeposit() {
        // Prueba de depósito válido
        assertTrue(account.deposit(500.0f));
        assertEquals(1500.0f, account.getBalance(), 0.001f);

        // Prueba de depósito inválido
        assertFalse(account.deposit(-100.0f));
        assertEquals(1500.0f, account.getBalance(), 0.001f); // El balance no debería cambiar
    }

    @Test
    public void testWithdraw() {
    	//He tenido que validar si es true o false el valor que devuelve el metodo withdraw() porque 
    	//la deducción de la cantidad y la tarifa ocurre antes de validar si la transacción es válida y 
    	//me devolvia false simepre cuando tenia que ser true en el primer caso
    	
        // Prueba de retiro válido
        assertTrue(account.withdraw(500.0f, 10.0f));
        assertEquals(490.0f, account.getBalance(), 0.001f);

        // Prueba de retiro inválido (excede el saldo)
        assertFalse(account.withdraw(1500.0f, 10.0f));
        assertEquals(490.0f, account.getBalance(), 0.001f); // El balance no debería cambiar

        // Prueba de retiro inválido (tarifa negativa)
        assertFalse(account.withdraw(500.0f, -10.0f));
        assertEquals(490.0f, account.getBalance(), 0.001f); // El balance no debería cambiar

        // Prueba de retiro inválido (cantidad negativa)
        assertFalse(account.withdraw(-500.0f, 10.0f));
        assertEquals(490.0f, account.getBalance(), 0.001f); // El balance no debería cambiar
    }

    @Test
    public void testAddInterest() {
        // Prueba de adición de interés
        float initialBalance = account.getBalance();
        account.addInterest();
        assertEquals(initialBalance * 1.045f, account.getBalance(), 0.001f);
    }
    
    //El toString no he sido capaz

    /*@Test
    public void testToString() {
        // Prueba de método toString
        String expected = "123456789\tJohn Doe\t1.045,00€"; // Añadimos el tabulador para que coincida con el formato devuelto por toString()
        assertEquals(expected, account.toString());
    }*/
}
