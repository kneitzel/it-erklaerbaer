package dtoexample;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class PersonDAO {

    private List<Person> persons = new ArrayList<>();

    public void add(Person person) {
        if (find(person.getId(), person.getName(), person.getAddress()) .isPresent()) throw new IllegalStateException("Person is already inside.");
        persons.add(person);
    }

    public List<Person> findAllByName(String name) {
        return persons.stream()
                .filter(p -> p.getName().equals(name))
                .collect(Collectors.toList());
    }

    public List<Person> findAllByAddress(String address) {
        return persons.stream()
                .filter(p -> p.getAddress().equals(address))
                .collect(Collectors.toList());
    }

    public List<Person> findAllById(int id) {
        return persons.stream()
                .filter(p -> p.getId() == id)
                .collect(Collectors.toList());
    }

    public Optional<Person> find(int id, String name, String address) {
        return persons.stream()
                .filter(p -> p.getName().equals(name))
                .filter(p -> Objects.equals(p.getAddress(), address))
                .filter(p -> p.getId() == id)
                .findAny();
    }

    public List<Person> findAll() {
        return persons;
    }
}
