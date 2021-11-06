package trab1.grupo2;

public class SimplePath {
    Place firstPlace, lastPlace;
    double distance;

    public SimplePath(Place f, Place l, double d){
        firstPlace = f;
        lastPlace = l;
        distance = d;
    }

    public Place getFirstPlace(){
        return firstPlace;
    }

    public Place getLastPlace(){
        return lastPlace;
    }

    public Place[] getPlaces(){
        Place[] bothPlaces = new Place[2];
        bothPlaces[0] = firstPlace;
        bothPlaces[1] = lastPlace;
        return bothPlaces;
    }

    public String toString() {
        return getFirstPlace().getName() + " -> " + getLastPlace().getName() + ": " + distance + "Km";
    }
}
