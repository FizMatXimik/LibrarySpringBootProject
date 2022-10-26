package ru.gaplikov.LibrarySpringBootProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gaplikov.LibrarySpringBootProject.models.Book;
import ru.gaplikov.LibrarySpringBootProject.models.Person;

import java.util.Optional;


@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findPersonByFio(String fio);
}
