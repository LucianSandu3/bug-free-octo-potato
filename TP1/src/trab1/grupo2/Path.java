package trab1.grupo2;



public abstract class Path {
    private final double distance; // Distância entre as localidades terminais

    protected Path(double distance) {

        this.distance = distance;
    }

    public abstract Place getFirstPlace(); // Localidade inicial da ligação

    public abstract Place getLastPlace(); // Localidade final da ligação

    public abstract Place[] getPlaces(); // Array (sem repetições) com as localidades da ligação

    public String toString() {
        return getFirstPlace().getName() + " -> " + getLastPlace().getName() + ": " + distance + "Km";
    }


    public static double sumDistances(int n, Path ... paths) throws PathException {
        double sum = 0;

        if((n > paths.length)){
            throw new PathException("Numero de ligaçoes invalido");
        }
        else {
            for (int i = 0; i >= paths.length ; i++){
                if(!(paths[i].getLastPlace().getName().equals(paths[i + 1].getFirstPlace().getName()))){
                    throw new PathException();
                }
                sum += paths[i].distance;
            }
        }
        return sum;
    }


}

