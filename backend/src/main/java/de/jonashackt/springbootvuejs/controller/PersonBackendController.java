package de.jonashackt.springbootvuejs.controller;

import de.jonashackt.springbootvuejs.domain.Person;
import de.jonashackt.springbootvuejs.exception.PersonNotFoundException;
import de.jonashackt.springbootvuejs.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api")
public class PersonBackendController {

    private static final Logger LOG = LoggerFactory.getLogger(PersonBackendController.class);

    public static final String HELLO_TEXT = "Hello from Spring Boot Backend!";
    public static final String SECURED_TEXT = "Hello from the secured resource!";

    @Autowired
    private PersonRepository personRepository;

    @RequestMapping(path = "/person/{lastName}/{firstName}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public long addNewPerson (@PathVariable("lastName") String lastName, @PathVariable("firstName") String firstName) {
        Person savedPerson = personRepository.save(new Person(firstName, lastName));

        LOG.info(savedPerson.toString() + " successfully saved into DB");

        return savedPerson.getId();
    }

    @GetMapping(path = "/person/{id}")
    public Person getPersonById(@PathVariable("id") long id) {

        return personRepository.findById(id).map(person -> {
            LOG.info("Reading person with id " + id + " from database.");
            return person;
        }).orElseThrow(() -> new PersonNotFoundException("The person with the id " + id + " couldn't be found in the database."));
    }

/*    @RequestMapping(path="/secured", method = RequestMethod.GET)
    public @ResponseBody String getSecured() {
        LOG.info("GET successfully called on /secured resource");
        return SECURED_TEXT;
    }

    // Forwards all routes to FrontEnd except: '/', '/index.html', '/api', '/api/**'
    // Required because of 'mode: history' usage in frontend routing, see README for further details
    @RequestMapping(value = "{_:^(?!index\\.html|api).*$}")
    public String redirectApi() {
        LOG.info("URL entered directly into the Browser, so we need to redirect...");
        return "forward:/";
    }*/

}
