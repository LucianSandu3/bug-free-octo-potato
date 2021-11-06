package trab1.grupo1;

public class Print {
    public static void main(String[] args) {
        City c1= new City("Faro", 64560, 202);
        System.out.println(c1.toString()); // "toString" é redundante pois c1 ja é uma string, é indiferente para o compilador estar la a função ou n mas torna se confuso para o utilizados que va ler o código
        City c2= new City("Faro", 64560, 202);
        System.out.println( c1 == c2 );
        System.out.println( c1.equals( c2 ) );

        City c3 = c1;
        System.out.println( c1.equals( c3 ) );
        if ( c3 != null )
            System.out.println(c1==c3);
        System.out.println(c1.populationDensity());
    }
}
