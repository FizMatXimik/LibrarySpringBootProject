package ru.gaplikov.LibrarySpringBootProject.services;


import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gaplikov.LibrarySpringBootProject.models.Book;
import ru.gaplikov.LibrarySpringBootProject.models.Person;
import ru.gaplikov.LibrarySpringBootProject.repositories.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly=true)
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findOne(int id) {
        Optional<Person> foundPerson = personRepository.findById(id);
        return foundPerson.orElse(null);
    }

    public  Optional<Person> findPersonByFio(String fio) {
        return personRepository.findPersonByFio(fio);
    }

    @Transactional
    public void save(Person person) {
        personRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        updatedPerson.setId(id);
        personRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id) {
        personRepository.deleteById(id);
    }

    public List<Book> getBooksByPersonId(int id) {
        Optional<Person> foundPerson = personRepository.findById(id);
        if(foundPerson.isPresent()) {
            Hibernate.initialize(foundPerson.get().getBooks());
            return foundPerson.get().getBooks();
        }
        return null;
    }
}
