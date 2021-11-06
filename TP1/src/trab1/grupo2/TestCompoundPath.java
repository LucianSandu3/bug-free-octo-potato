package trab1.grupo2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import trab1.grupo1.City;
import java.util.Arrays;

public class TestCompoundPath {
    private static Place[] PLACES = {
            new City("Faro",64560 , 202),
            new City("Lisboa", 504718, 100),
            new City("Coimbra", 105842, 31),
            new City("Porto", 214349,42 ),
            new City("Viana do Castelo", 85864, 319),
            new City("Braga",13688 , 184),
            new City("Chaves", 41243, 591),
    };
    private static double[] DISTANCES = {
            216.21, 204.4, 121.5, 98.6, 61.8, 126.1
    };

    private static Path[] getPaths( int n, int delta, double[] distances, Place ... places ) {
        Path [] paths = new Path[n];
        for( int i= 0, j=0; i< n; ++i, j+= delta)
            paths[i] = new SimplePath(places[j], places[j+1], distances[j]);
        return paths;
    }
    private static Path[] getPaths( int n, double[] distances, Place ... places ) {
      return getPaths( n, 1, distances, places);
    }
    private static Path[] getAllPaths(  ) {
        return getPaths( PLACES.length-1 ,  DISTANCES, PLACES);
    }

   private void testGetters( Path p, Place f, Place l, Place ... places) {
        assertEquals(f, p.getFirstPlace());
        assertEquals(l, p.getLastPlace());
        assertArrayEquals(places, p.getPlaces());
    }

    @Test
    public void testConstruct() throws Exception {
        testGetters(new CompoundPath(3, getAllPaths()), PLACES[0], PLACES[3], Arrays.copyOf(PLACES, 4));
        testGetters(new CompoundPath(DISTANCES.length, getAllPaths()), PLACES[0], PLACES[PLACES.length - 1], PLACES);
    }

    private CompoundPath getCompoundCompoundPath() throws Exception{
        CompoundPath p1 = new CompoundPath( 4, getAllPaths() );
        CompoundPath p2 = new CompoundPath( 2, getPaths(2,
                           Arrays.copyOfRange(DISTANCES, DISTANCES.length-2, DISTANCES.length),
                           Arrays.copyOfRange(PLACES, PLACES.length-3, PLACES.length)) );
        return new CompoundPath(2, p1, p2);
    }

    @Test
    public void testGetPlaces() throws Exception{
        assertArrayEquals(PLACES,  new CompoundPath(DISTANCES.length, getAllPaths()).getPlaces() );
        CompoundPath p1 = new CompoundPath( 4, getAllPaths() );
        CompoundPath p2 = new CompoundPath( 2, getPaths(2, Arrays.copyOfRange(DISTANCES, DISTANCES.length-3, DISTANCES.length),
                                                                Arrays.copyOfRange(PLACES, PLACES.length-3, PLACES.length)) );
        assertArrayEquals(PLACES, getCompoundCompoundPath().getPlaces() );
    }

/*
   @Test
    public void testExpectedPathException() {
        PathException e =assertThrows(PathException.class, ()-> new CompoundPath(4, getPaths(2, DISTANCES, PLACES)));
        assertEquals("Número de ligações inválido", e.getMessage());

        e =assertThrows(PathException.class, ()-> new CompoundPath(1, getPaths(1, DISTANCES, PLACES)[0]));
        assertEquals("Número de ligações inválido", e.getMessage());

        e =assertThrows(PathException.class, ()-> new CompoundPath(3, getPaths(3, 2, DISTANCES, PLACES)));
        assertEquals("Ligação inválida", e.getMessage());
    }
*/

    @Test
    public void testToString() throws Exception {
       // Soma todas as distancias
       double d = Arrays.stream( DISTANCES ).sum();
       String expected = "Faro -> Chaves: "+d+"Km (Faro->Lisboa->Coimbra->Porto->Viana do Castelo->Braga->Chaves)";
       assertEquals(expected, new CompoundPath(DISTANCES.length, getAllPaths()).toString());
       assertEquals(expected, getCompoundCompoundPath().toString() );
    }

}
