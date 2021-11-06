package trab1.grupo2;

import org.junit.jupiter.api.Test;
import trab1.grupo1.City;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSimplePath {
    private static final Place FARO = new City("Faro", 64560, 202 );
    private static final Place LISBOA = new City("Lisboa", 504718, 100);
    private static final Place PORTO = new City("Porto", 214349, 42);

    private void testGetters( SimplePath p, Place f, Place l, Place ... places) {
        assertEquals(f, p.getFirstPlace());
        assertEquals(l, p.getLastPlace());
        assertArrayEquals(places, p.getPlaces());
    }

    @Test
    public void testConstruct() {
        testGetters( new SimplePath(FARO, LISBOA, 216.21), FARO, LISBOA, FARO, LISBOA );
        testGetters(new SimplePath(LISBOA, PORTO, 313.9),  LISBOA, PORTO, LISBOA, PORTO);
    }

    @Test
    public void testToString() {
        assertEquals( "Faro -> Lisboa: 216.21Km", new SimplePath(FARO, LISBOA, 216.21).toString() );
        assertEquals( "Lisboa -> Porto: 313.9Km", new SimplePath(LISBOA, PORTO, 313.9).toString() );
    }

}
