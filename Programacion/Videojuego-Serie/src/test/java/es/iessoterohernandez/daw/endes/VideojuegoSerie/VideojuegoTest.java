package es.iessoterohernandez.daw.endes.VideojuegoSerie;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VideojuegoTest {

    private Videojuego videojuego;

    @BeforeEach
    public void init() {
        // Configurar el objeto Videojuego antes de cada prueba
        videojuego = new Videojuego("The Legend of Zelda", 50, "Aventura", "Nintendo");
    }

    @AfterEach
    public void finish() {
        // Limpiar el estado después de cada prueba
        videojuego = null;
    }

    @Test
    public void testEntregar() {
        //Configurar el estado inicial del videojuego
        assertFalse(videojuego.isEntregado());

        //Llamar al método entregar() en el objeto Videojuego
        videojuego.entregar();

        // Assert: Verificar que el videojuego esté entregado
        assertTrue(videojuego.isEntregado());
    }

    @Test
    public void testDevolver() {
        //Configurar el estado inicial del videojuego
        videojuego.entregar();
        assertTrue(videojuego.isEntregado());

        //Llamar al método devolver() en el objeto Videojuego
        videojuego.devolver();

        // Assert: Verificar que el videojuego no está entregado
        assertFalse(videojuego.isEntregado());
    }

    @Test
    public void testToString() {
        //Llamar al método toString() en el objeto Videojuego
        String resultado = videojuego.toString();

        // Assert: Verificar que el resultado devuelto coincida con el formato esperado
        String expected = "Informacion del videojuego: \n" +
                          "\tTitulo: The Legend of Zelda\n" +
                          "\tHoras estimadas: 50\n" +
                          "\tGenero: Aventura\n" +
                          "\tcompañia: Nintendo";
        assertEquals(expected, resultado);
    }

    @Test
    public void testEquals() {

        // Caso donde el otro videojuego es igual
        Videojuego otroVideojuego = new Videojuego("The Legend of Zelda", 50, "Aventura", "Nintendo");
        assertTrue(videojuego.equals(otroVideojuego));

        // Caso donde el otro videojuego es diferente
        otroVideojuego = new Videojuego("Mario Kart", 30, "Carreras", "Nintendo");
        assertFalse(videojuego.equals(otroVideojuego));
    }

    @Test
    public void testCompareTo() {

        //Assert: Probar los diferentes casos
        Videojuego otroVideojuego = new Videojuego("Super Mario Bros", 50, "Aventura", "Nintendo");
        
        // Caso donde el otro videojuego tiene las mismas horas estimadas
        assertEquals(Videojuego.IGUAL, videojuego.compareTo(otroVideojuego));
        
        // Caso donde el otro videojuego tiene menos horas estimadas
        otroVideojuego.setHorasEstimadas(20);
        assertEquals(Videojuego.MAYOR, videojuego.compareTo(otroVideojuego));

        // Caso donde el otro videojuego tiene más horas estimadas
        otroVideojuego.setHorasEstimadas(100);
        assertEquals(Videojuego.MENOR, videojuego.compareTo(otroVideojuego));

        
    }
}