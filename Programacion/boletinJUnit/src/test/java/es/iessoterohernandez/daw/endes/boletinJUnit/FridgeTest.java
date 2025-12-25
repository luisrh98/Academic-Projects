package es.iessoterohernandez.daw.endes.boletinJUnit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class FridgeTest {

    private Fridge fridge;

    @BeforeEach
    public void init() {
        fridge = new Fridge();
    }

    @AfterEach
    public void finish() {
        fridge = null;
    }

    @ParameterizedTest
    @MethodSource("comidas")
    public void testPut(String item) {
        assertTrue(fridge.put(item));
        assertTrue(fridge.contains(item));
    }

    @ParameterizedTest
    @MethodSource("comidas")
    public void testContains(String item) {
        assertFalse(fridge.contains(item));
        fridge.put(item);
        assertTrue(fridge.contains(item));
    }

    @ParameterizedTest
    @MethodSource("comidas")
    public void testTake(String item) {
        fridge.put(item);
        try {
            fridge.take(item);
        } catch (NoSuchItemException e) {
            fail("Exception thrown when taking item from fridge");
        }
        assertFalse(fridge.contains(item));
        assertThrows(NoSuchItemException.class, () -> fridge.take(item));
    }

    private static Stream<String> comidas() {
        return Stream.of("manzana", "platano", "naranja");
    }
}