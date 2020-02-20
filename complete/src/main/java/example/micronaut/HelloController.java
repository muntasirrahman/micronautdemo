package example.micronaut;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@Controller("/hello") // <1>
public class HelloController {

    Logger logger = Logger.getLogger("example.micronaut");

    public HelloController() {
        FileHandler handler;
        try {
            handler = new FileHandler("default.log", true);
            SimpleFormatter formatter = new SimpleFormatter();
            handler.setFormatter(formatter);

            logger.addHandler(handler);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Get
    @Produces(MediaType.TEXT_PLAIN)
    public String index() {
        logger.info("Anonymous incoming request ");
        return "Hello World";
    }

    @Get("/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHello(String name) {
        logger.info("Incoming request from " + name);
        return "Hello " + name;
    }
}
