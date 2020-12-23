package dtoexample;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PersonDAOTest {

    @Test
    public void testPersonDAO() {
        PersonDAO database = new PersonDAO();

        try (ExampleEntityReader reader = new ExampleEntityReader(getClass().getResourceAsStream("/Kundenliste.xlsx"))) {
            reader.stream().forEach( e -> {
                Optional<Person> foundPerson = database.find(e.getId(), e.getName(), e.getAddress());
                Person person;
                if (foundPerson.isPresent()) {
                    person = foundPerson.get();
                } else {
                    person = new Person(e.getId(), e.getName(), e.getAddress());
                    database.add(person);
                }
                person.addDuration(e.getFromDate(), e.getToDate());
            });
        } catch (IOException ex) {
            fail("IOException!", ex);
        }

        for (Person person: database.findAll()) {
            System.out.println("Person: " + person.getId() + " / " + person.getName() + " / " + person.getAddress());
            for (Duration duration: person.getDurations())
                System.out.println(" - " + duration.toString());
        }
    }
}