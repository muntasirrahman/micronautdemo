package example.micronaut;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@Controller("/hello") // <1>
public class HelloController {

    private static LocalDateTime nextMidnight;
    private static Logger logger = Logger.getLogger("example.micronaut");

    @Get
    @Produces(MediaType.TEXT_PLAIN)
    public String index() {
        checkRotatingLogFilename();
        logger.info("Anonymous incoming request ");
        return "Hello World";
    }

    @Get("/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHello(String name) {
        checkRotatingLogFilename();
        logger.info("Incoming request from " + name);
        return "Hello " + name;
    }

    private void checkRotatingLogFilename() {

        LocalDateTime now = LocalDateTime.now();

        if (nextMidnight == null || now.isAfter(nextMidnight)) {

            nextMidnight = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT).plusDays(1);
            try {
                for (Handler handler : logger.getHandlers()) logger.removeHandler(handler); //remove the existing one

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                FileHandler newFileHandler = new FileHandler("application" + formatter.format(now) + ".log", true);
                newFileHandler.setFormatter(new SimpleFormatter());
                logger.addHandler(newFileHandler);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
