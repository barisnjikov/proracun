package de.jonashackt.springbootvuejs.repository;


import de.jonashackt.springbootvuejs.domain.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Long> {

    List<Person> findByLastName(@Param("lastname") String lastname);

    List<Person> findByFirstName(@Param("firstname") String firstname);

}
