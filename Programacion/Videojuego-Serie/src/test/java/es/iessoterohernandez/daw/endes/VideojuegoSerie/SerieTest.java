
package es.iessoterohernandez.daw.endes.VideojuegoSerie;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SerieTest {
	private Serie serie;
	
	@BeforeEach
	void init() {
		serie = new Serie("La casa de papel", 5, "Suspense", "Dani el rojo");
		//System.out.println("init");
	}

	@AfterEach
	void finish() {
		serie = null;
		//System.out.println("finish");
	}

	@Test
    public void testEntregar() {
        //Configurar el estado inicial del videojuego
        assertFalse(serie.isEntregado());

        //Llamar al método entregar() en el objeto Videojuego
        serie.entregar();

        // Assert: Verificar que el videojuego esté entregado
        assertTrue(serie.isEntregado());
    }

    @Test
    public void testDevolver() {
        //Configurar el estado inicial del videojuego
        serie.entregar();
        assertTrue(serie.isEntregado());

        //Llamar al método devolver() en el objeto Videojuego
        serie.devolver();

        // Assert: Verificar que el videojuego no está entregado
        assertFalse(serie.isEntregado());
    }

    @Test
    public void testCompareTo() {
        // Crear una nueva serie con menos temporadas
        Serie otraSerie = new Serie("Berlin", 1, "Suspense", "Netflix");
        assertEquals(Serie.MAYOR, serie.compareTo(otraSerie));

        // Crear una nueva serie con el mismo número de temporadas
        otraSerie.setnumeroTemporadas(5);
        assertEquals(Serie.IGUAL, serie.compareTo(otraSerie));

        // Crear una nueva serie con más temporadas
        otraSerie.setnumeroTemporadas(10);
        assertEquals(Serie.MENOR, serie.compareTo(otraSerie));
    }
    
    @Test
    public void testToString() {
        
        String expected = "Informacion de la Serie: \n" +
                          "\tTitulo: La casa de papel\n" +
                          "\tNumero de temporadas: 5\n" +
                          "\tGenero: Suspense\n" +
                          "\tCreador: Dani el rojo";
        
        assertEquals(expected, serie.toString());
    }
    
    @Test
    public void testEquals() {
        Serie serie2 = new Serie("La casa de papel", 5, "Suspense", "Dani el rojo");
        Serie serie3 = new Serie("Otra Serie", 3, "Drama", "Otro Creador");
        
        assertTrue(serie.equals(serie2));
        assertFalse(serie.equals(serie3));
    }
}
