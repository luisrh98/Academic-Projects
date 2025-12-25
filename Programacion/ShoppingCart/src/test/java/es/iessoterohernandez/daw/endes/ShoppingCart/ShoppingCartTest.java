package es.iessoterohernandez.daw.endes.ShoppingCart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class ShoppingCartTest {

    private ShoppingCart carrito;
    private Product producto;

    @BeforeEach
    public void init() {
        carrito = new ShoppingCart();
        producto = new Product("Producto 1", 10.0);
    }

    @AfterEach
    public void finish() {
        carrito = null;
    }

    @Test
    public void testCrearCarritoSinElementos() {
        assertEquals(0, carrito.getItemCount());
    }

    @Test
    public void testVaciarCarrito() {
        // Agregar algunos elementos al carrito para probar que se vacía correctamente
        carrito.addItem(producto);
        

        // Verificar que el carrito no esté vacío antes de vaciarlo
        assertEquals(false, carrito.getItemCount() == 0);

        // Vaciar el carrito
        carrito.empty();

        // Verificar que el carrito esté vacío después de vaciarlo
        assertEquals(0, carrito.getItemCount());
    }
    @Test
    public void testAñadeQuitaProducto() throws ProductNotFoundException {

        carrito.addItem(producto);
        assertEquals(1, carrito.getItemCount());
            
        carrito.removeItem(producto);
        assertEquals(0, carrito.getItemCount());
     }

    @Test
    public void testEliminarProductoNoExistenteLanzaExcepcion() {

        // Utilizamos un bloque try-catch para capturar la excepción y luego llamamos a fail()
        try {
            carrito.removeItem(producto);
            // Si removeItem() no lanza la excepción, llamamos a fail() para indicar que la prueba falló
            fail("Se esperaba que lanzara ProductNotFoundException");
        } catch (ProductNotFoundException e) {
            // Si se lanza la excepción, la prueba pasa
        }
    }
    @Test
    public void testBalanceNuevo() {
    	carrito.addItem(producto);
    	assertEquals(10.0, carrito.getBalance());
    }
    
}
