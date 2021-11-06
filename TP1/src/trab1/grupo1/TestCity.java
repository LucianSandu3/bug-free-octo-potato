package trab1.grupo1;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;
public class TestCity {
    private static final City FARO = new City("Faro", 64560, 202 );
    private static final City NOVA_YORK = new City("Nova York", "Estados Unidos",  8419000, 783);

    private void testGetters( City c, String nm, String ct, int p, int a) {
        assertEquals(nm, c.getName());
        assertEquals(ct, c.getCountry());
        assertEquals(p, c.getPopulation());
        assertEquals(a, c.getArea());
    }

    @Test
    public void testConstruct() {
        testGetters( FARO, "Faro", "Portugal", 64560, 202 );
        testGetters( NOVA_YORK, "Nova York", "Estados Unidos",  8419000, 783);
    }

    @Test
    public void testToString() {
        assertEquals( "Portugal:Faro - 202km²:64560", FARO.toString() );
        assertEquals( "Estados Unidos:Nova York - 783km²:8419000", NOVA_YORK.toString() );
    }

    @Test
    public void testPopulationDensity() {
        assertEquals( 64560/202, FARO.populationDensity());
        assertEquals(8419000/783, NOVA_YORK.populationDensity());
    }

    @Test
    public void testEquals() {

        assertTrue( FARO.equals(new City(new String("Faro"), 64560, 202)) );
        assertTrue( NOVA_YORK.equals(new City("Nova York", new String("Estados Unidos"),  8419000, 783)) );

        assertFalse( NOVA_YORK.equals( new City("Nova","Estados Unidos",  8419000, 783) ));
        assertFalse( NOVA_YORK.equals( new City("Nova York", "Estados",  8419000, 783) ));
        assertFalse( NOVA_YORK.equals( new City("Nova York","Estados Unidos",  8419, 783) ));
        assertFalse( NOVA_YORK.equals( new City("Nova York", "Estados Unidos",  8419000, 78) ));

        assertFalse( FARO.equals( null ) );
        assertFalse( FARO.equals("Portugal:Faro - 202²:64560"));
    }

    @Test
    public void testCompareTo() {
        assertTrue( FARO.compareTo( FARO ) == 0 );
        assertTrue( FARO.compareTo( NOVA_YORK ) < 0 );
        assertTrue( NOVA_YORK.compareTo( FARO ) > 0 );
     }

     @Test
    public void testGetCity() {
        City f =  City.getCity( "Portugal:Faro - 202km²:64560" );
        testGetters( f, "Faro", "Portugal", 64560, 202 );
        assertTrue(FARO.equals( f ) );
        assertEquals( FARO, f  );
        City ny = City.getCity( "Estados Unidos:Nova York - 783km²:8419000" );
        testGetters(ny, "Nova York", "Estados Unidos",  8419000, 783);
        assertEquals( NOVA_YORK, ny  );
     }

    private static String[] descriptions = {
            "Portugal:Faro - 202km²:64560",
            "Estados Unidos:Nova York - 783km²:8419000",
            "Estados Unidos:Boston - 232km²:684379",
            "Estados Unidos:Chicago - 606km²:2710000",
            "Reino Unido:Londres - 1572km²:8982000",
            "Portugal:Lisboa - 100km²:504718",
            "Portugal:Braga - 184km²:136885",
            "Portugal:Chaves - 591km²:41243",
            "Portugal:Viana do Castelo - 319km²:85864",
            "Reino Unido:Bristol - 110km²:467099",
            "Portugal:Porto - 42km²:214349",
            "Portugal:Coimbra - 31km²:105842",
    };

    private static City[] getCities( int n ) {
        if ( n > descriptions.length ) n= descriptions.length;
        City [] cities = new City[n];
        for( int i= 0; i< n; ++i )
            cities[i] = City.getCity( descriptions[i] );
        return cities;
    }

    @Test
    public void testCountryCitiesCount() {
        City [] cities = getCities(descriptions.length);
        assertEquals( 7, City.getCountryCitiesCount(cities, new String("Portugal")));
        assertEquals( 3, City.getCountryCitiesCount(cities, "Estados Unidos"));
        assertEquals( 2, City.getCountryCitiesCount(cities, "Reino Unido"));
        assertEquals( 0, City.getCountryCitiesCount(cities, "Espanha") );
    }

    @Test
    public void testSmallerCities() {
        assertEquals( FARO, City.smallerCities( FARO ) );
        assertEquals( FARO, City.smallerCities( FARO, NOVA_YORK ) );
        assertEquals( FARO, City.smallerCities( NOVA_YORK, FARO ) );
        assertEquals( descriptions[0], City.smallerCities( getCities(5 )).toString() );
        assertEquals( descriptions[5], City.smallerCities( getCities(8 )).toString() );
        assertEquals( descriptions[descriptions.length-1], City.smallerCities( getCities(descriptions.length )).toString() );
        assertNull( City.smallerCities( new City[0]) );
    }

    @Test
    public void testTop10() {
        Comparator<City> cmp = City::compareTo;
        City[] cities = getCities( 3  );
        City[] top10 = City.top10( cities );
        assertFalse( cities != top10 );
        assertEquals( 3, top10.length );
        Arrays.sort( cities, cmp.reversed() );
        assertArrayEquals(cities, top10 );

        cities = getCities( descriptions.length );
        top10 = City.top10( cities);
        assertEquals( 10, top10.length );
        Arrays.sort( cities, cmp.reversed() );
        City[] expected = Arrays.copyOf(cities, 10);
        assertArrayEquals(expected, top10 );

        Arrays.sort(  cities, cmp );
        top10 = City.top10( cities);
        assertEquals( 10, top10.length );
        assertArrayEquals(expected, top10 );

    }
}
