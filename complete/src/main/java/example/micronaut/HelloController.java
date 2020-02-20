package example.micronaut;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("/hello") // <1>
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
    @Get // <2>
    @Produces(MediaType.TEXT_PLAIN) // <3>
    public String index() {
        logger.info("Anonymous incoming request ");
        return "Hello World"; // <4>
    }

    @Get("/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHello(String name) {

        logger.info("Incoming request from " + name);
        return "Hello " + name;
    }
}
