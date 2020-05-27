package project.saloon;

public class SaloonNotFoundException extends RuntimeException {

    SaloonNotFoundException(Long id) {
        super("Could not find saloon " + id);
    }
}