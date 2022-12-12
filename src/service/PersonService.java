package service;

import classes.Person;
import enums.Country;
import enums.Gender;

import java.util.List;

public interface PersonService {
    String addDB(List<Person> people);
    String createPerson();
    String removePassport(String name);
    List<Person> getAllPerson();
    String addContact();
    String removeContact();
    void getAllContact();
    List<Country>getAllCountry();
    void getAllPersonGenderAndCountry();
    List<Person> findByName(String name);
}
