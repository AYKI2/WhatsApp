package classes;

import enums.Country;
import enums.Gender;

import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

public class Person{
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Gender gender;
    private Country country;
    private String phoneNumber;
    private Map<String, String> contacts = new TreeMap<>();

    public Person() {
    }

    public Person(int id, String firstName, String lastName, LocalDate dateOfBirth, Gender gender, Country country,String phoneNumber, Map<String,String> contacts) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.contacts = contacts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Map<String, String> getContacts() {
        return contacts;
    }

    public void setContacts(Map<String, String> contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "Person: " +
                "\nid = " + id +
                "\nfirstName = " + firstName +
                "\nlastName = " + lastName +
                "\ndateOfBirth = " + dateOfBirth +
                "\ngender = " + gender +
                "\ncountry = " + country +
                "\nphoneNumber = " + phoneNumber +
                "\ncontacts = " + contacts + "\n";
    }
}
