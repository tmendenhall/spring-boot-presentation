package com.eg.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.format.datetime.standard.DateTimeContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicLong;

@Controller
@RequestMapping("/hello-world")
public class HelloWorldController {

    Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

    @Autowired
    private CounterService counterService;

    @Autowired
    private GaugeService gaugeService;

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<?> sayHello(@RequestParam(value="name", required=false, defaultValue="Stranger") String name) {
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

}
