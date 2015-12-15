package com.eg.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Controller
@RequestMapping("/hello-world")
public class HelloWorldController {

    Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

    @Inject // See build.gradle for JSR-330 Annotation addition
    private Environment environment;

    @Inject
    private CounterService counterService;

    @Inject
    private GaugeService gaugeService;

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<?> sayHello(@RequestParam(value="name", required=false, defaultValue="Stranger") String name) {

        String template = environment.getProperty("hello-world.message","Hello default, %s!");

        Greeting greeting = new Greeting(counter.incrementAndGet(), String.format(template,name));
        return new ResponseEntity<Greeting>(greeting, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/echo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> echoWord(@RequestParam(value = "word") String word){
        counterService.increment("HelloWorldController.echo.GET");
        Instant start = Instant.now();
        Greeting echo = new Greeting(counter.incrementAndGet(),word);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            logger.error(e.getMessage(),e);
        }
        gaugeService.submit("HelloWorldController.echoWord.elapsed",(Instant.now().getNano()-start.getNano()));
        return new ResponseEntity<Greeting>(echo,HttpStatus.ACCEPTED);

    }

    @RequestMapping(method = RequestMethod.GET, path = "/hello/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> sayPredefinedMessage(@PathVariable(value = "id") String id) {
        counterService.increment("HelloWorldController.hello.id.GET");
        Map<String, String> helloMessages = new HashMap<>();
        helloMessages.put("1", "'ello");
        helloMessages.put("2", "Morgan");
        helloMessages.put("3", "top 'o the mornin");

        String returnString = helloMessages.get(id);
        if (returnString == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(new Greeting(counter.incrementAndGet(), returnString));
        }
    }

}
