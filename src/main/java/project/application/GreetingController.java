package project.application;

import java.util.concurrent.atomic.AtomicLong;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import project.home_page.Greeting;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static project.constants.Paths.HOME;

@RestController
@Api(value = "TQS Final Project Rest API")
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @ApiOperation(value = "Home Page")
    @GetMapping(value = HOME)
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}