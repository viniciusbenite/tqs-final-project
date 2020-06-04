package project.service;

public class ServiceNotFoundException extends RuntimeException {

    ServiceNotFoundException(Long id) {
        super("Could not find service " + id);
    }
}