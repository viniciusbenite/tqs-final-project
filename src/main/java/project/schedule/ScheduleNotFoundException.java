package project.schedule;

public class ScheduleNotFoundException extends RuntimeException {

    ScheduleNotFoundException(Long id) {
        super("Could not find schedule " + id);
    }
}