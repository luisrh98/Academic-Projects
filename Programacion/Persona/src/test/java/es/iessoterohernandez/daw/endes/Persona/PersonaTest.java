package es.iessoterohernandez.daw.endes.Persona;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PersonaTest {

    private Persona persona; // Declaración del objeto Persona que se utilizará en las pruebas

    @BeforeEach
    public void init() {
        // Configurar el objeto Persona antes de cada prueba
        // Inicializar el objeto Persona con valores específicos utilizando un constructor
        persona = new Persona("Juan", 30, 'H', 75, 1.75);
        //System.out.println("inicio");
    }
    
    @AfterEach
    public void finish() {
        persona = null;
        //System.out.println("finish");
    }

    @Test
    public void calcularIMC() {
        // Prueba para calcular el IMC en diferentes situaciones de peso
        // Testeamos el peso ideal
        assertEquals(Persona.PESO_IDEAL, persona.calcularIMC());

        // Testeamos el infrapeso
        persona.setPeso(45);
        persona.setAltura(1.60);
        assertEquals(Persona.INFRAPESO, persona.calcularIMC());

        // Testeamos el sobrepeso
        persona.setPeso(90);
        persona.setAltura(1.80);
        assertEquals(Persona.SOBREPESO, persona.calcularIMC());
    }

    @Test
    public void esMayorDeEdad() {
    	// Testeamos una persona mayor de edad
        assertTrue(persona.esMayorDeEdad());
        
        // Testeamos una persona menor de edad
        persona.setEdad(17);
        assertFalse(persona.esMayorDeEdad());

        
    }
}