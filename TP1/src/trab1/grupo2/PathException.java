package trab1.grupo2;

public class PathException extends Exception {

    public String getMessage(String a) {
        return a;
    }

    public PathException(String message) {

        System.out.println(getMessage(message));
    }
    public PathException() {
        System.out.println(getMessage("Ligaçao Invalida"));
    }


}
