package es.iessoterohernandez.daw.endes.boletinJUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class BoaTest {

    @ParameterizedTest
    @MethodSource("boaData")
    public void testIsHealthy(String name, int length, String favoriteFood, boolean expected) {
        Boa boa = new Boa(name, length, favoriteFood);
        assertEquals(expected, boa.isHealthy());
    }

    @ParameterizedTest
    @MethodSource("boaCageData")
    public void testFitsInCage(int boaLength, int cageLength, boolean expected) {
        Boa boa = new Boa("Test Boa", boaLength, "Test Food");
        assertEquals(expected, boa.fitsInCage(cageLength));
    }

    // Metodo para proporcionar datos de prueba para isHealthy()
    private static Stream<Arguments> boaData() {
        return Stream.of(
                Arguments.of("Boa1", 10, "granola bars", true), 
                Arguments.of("Boa2", 5, "raton", false),
                Arguments.of("Boa3", 15, "granola bars", true));
    }

    // Metodo para proporcionar datos de prueba para fitsInCage()
    private static Stream<Arguments> boaCageData() {
        return Stream.of(Arguments.of(8, 10, true), 
                Arguments.of(12, 10, false), 
                Arguments.of(10, 10, false));
    }

}