package trab1.grupo2;

import java.util.Arrays;

public class CompoundPath  {
    private final Path[] pat;
    int n;

    public CompoundPath(int n, Path ... paths) throws PathException {
        pat = new Path[n];
        this.n = n;
        if (n < 2 || n > paths.length) {
            throw new PathException("Numero de ligações invalido");
        }
        for (int i = 0; i < n; i++) {
            if (!(paths[i].getLastPlace().getName().equals(paths[i + 1].getFirstPlace().getName()))) {
                throw new PathException();
            }
            pat[i] = paths[i];
        }
    }

    public Place[] getPlaces(){
        Place[] allPlaces = pat[0].getPlaces();

        for(int i = 1; i < pat.length; i++){
            allPlaces = Arrays.copyOf(allPlaces, allPlaces.length+pat[i].getPlaces().length);
            System.arraycopy(pat[i].getPlaces(), 1, allPlaces, allPlaces.length, pat[i].getPlaces().length - 1);
        }
        return allPlaces;
    }

//    public String toString() {
//        return pat[0].getFirstPlace().getName() + " -> " + pat[n].getLastPlace().getName() + ": " + distance + "Km" + getPlaces();
//    }
}
