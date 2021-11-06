package trab1.grupo1;


import trab1.grupo2.Place;

public class City implements Place {
    private final String name, country;
    private int population;
    private final int area; // Em km²

    public City(String nm, int p, int a) {
        this(nm,"Portugal", p, a);
    }

    public City(String nm, String ctry, int p, int a ) {
        this.name = nm;
        this.population = p;
        this.country = ctry;
        this.area = a;
    }


    public String toString() { // nova definiçao da função toString para apresentaçao do output como indicado no enunciado, concatenação dos respetivos membros da classe
        return country + ":" + name + " - " + area + "km²" + ":" + population;
    }

    public boolean equals(Object obj) {
        if (obj instanceof City) {
            if (obj == null) {
                return false;
            }

            City newCity = (City) obj; // Cast para que o objeto seja um City, em alternativa poderiamos meter a funçao a receber um parametro do tipo City
            return (this.name.equals(newCity.name) && this.country.equals(newCity.country) && this.population == newCity.population && this.area == newCity.area); // comparaçao dos varios tipos usando equals p\ tipos complexos e "==" p\ tipos "basicos"(int,float..etc...)
        }
        else
            return false;
    }

    /*GETTERS*/

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public int getPopulation() {
        return population;
    }

    public int getArea() {
        return area;
    }
    /*--------------*/

    public int populationDensity() {
        return population / area;
    }

    public void populationChange(int rate) {
        this.population = (int) (this.population * Math.exp(rate));
    }

    public int compareTo(City c2) {
        return area - c2.area;
    }

    public static City getCity(String cityStr) {

        int indexCountry = cityStr.indexOf(':');
        int indexName = cityStr.indexOf('-', indexCountry) - 1;
        int indexArea = cityStr.indexOf(':', indexName) - 3; // index of "k" on km²

        String strCountry = cityStr.substring(0, indexCountry);
        String strName = cityStr.substring(indexCountry + 1, indexName);
        String strArea = cityStr.substring(indexName + 3, indexArea);
        String strPopulation = cityStr.substring(indexArea + 4);

        int numArea = Integer.parseInt(strArea);
        int numPopulation = Integer.parseInt(strPopulation);



        return new City(strName, strCountry, numPopulation, numArea);
    }

    public static int getCountryCitiesCount(City[] cityArr, String ctry) {
        int numOfCtry = 0;
        for (City i : cityArr) {
            if (ctry.equals(i.getCountry())){
                numOfCtry++;
            }
        }
        return numOfCtry;
    }

    public static City smallerCities(City ... cityArr) {
        if(cityArr.length == 0){
            return null;
        }

        City Smaller = cityArr[0];
        for (int i = 0; i < cityArr.length; i++) {
            if (Smaller.compareTo(cityArr[i]) > 0) {
                Smaller = cityArr[i];
            }
        }
        return Smaller;
    }

    public static City[] top10(City ... cityArr){
        if (cityArr.length < 10){
            return cityArr;
        }

        City[] Top10 = new City[10];
        for (int i = 0; i < cityArr.length ; i++) {
            for (int j = cityArr.length - 1; j > i; j--) {
                if (cityArr[i].getArea() > cityArr[j].getArea()) {
                    cityArr[i] = cityArr[j];
                }
            }
        }

        for (int y = 0; y < 10; y++) {
            Top10[y] = cityArr[y];
        }
        return Top10;
    }

}

