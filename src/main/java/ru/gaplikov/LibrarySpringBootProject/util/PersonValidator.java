package ru.gaplikov.LibrarySpringBootProject.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.gaplikov.LibrarySpringBootProject.models.Person;
import ru.gaplikov.LibrarySpringBootProject.services.PersonService;


@Component
public class PersonValidator implements Validator {
    private final PersonService personService;

    @Autowired
    public PersonValidator(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;

        if (personService.findPersonByFio(person.getFio()).isPresent()) {
            errors.rejectValue("fio", "", "Такой клиент уже зарегестрирован");
        }

    }
}
