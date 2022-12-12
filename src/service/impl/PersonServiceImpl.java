package service.impl;

import classes.Person;
import enums.Country;
import enums.Gender;
import service.PersonService;

import java.time.LocalDate;
import java.util.*;

public class PersonServiceImpl implements PersonService {
    public static Scanner scanner = new Scanner(System.in);
    private List<Person> personList = new ArrayList<>();

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) { this.personList = personList;
    }

    @Override
    public String addDB(List<Person> people) {
        this.personList = people;
//        for (Person person : people) {
//            Passport passports1 = new Passport(person.getId(),person.getFirstName(),
//                    person.getLastName(),person.getDateOfBirth(),person.getGender(),person.getCountry());
//            this.passportList.add(passports1);
//        }
        return "Successfully created!";
    }

    @Override
    public String createPerson() {
        Scanner scanner1 = new Scanner(System.in);
        boolean isCreated = false;
        try {
            System.out.print("IDs that already exist: ");
            for (Person person : personList) {
                System.out.print(person.getId() + ", ");
            }
            System.out.println("\nEnter ID: ");
            int id = scanner1.nextInt();
            for (Person person : personList) {
                if (person.getId() == id) {
                    return "This ID already exists!";
                }
            }
            System.out.println("Enter first name: ");
            String firstName = scanner1.next();
            if (firstName.matches("[a-zA-Z]*")) {
                System.out.println("Enter last name: ");
                String lastName = new Scanner(System.in).nextLine();
                if (lastName.matches("[a-zA-Z ]*")) {
                    System.out.println("Enter date of birth (Year,Month,Day): ");
                    LocalDate dateOfBirth = LocalDate.of(scanner1.nextInt(), scanner1.nextInt(), scanner1.nextInt());
                    System.out.println("Choice gender 1(Male), 2(Female): ");
                    int num = scanner1.nextInt();
                    Gender gender = null;
                    switch (num) {
                        case 1 -> gender = Gender.MALE;
                        case 2 -> gender = Gender.FEMALE;
                        default -> System.out.println("No such gender!");
                    }
                    System.out.println("Choice country 1(Kyrgyzstan), 2(Russia), 3(USA), 4(France), 5(England), 6(Polska): ");
                    int number = scanner1.nextInt();
                    Country country = null;
                    switch (number) {
                        case 1 -> country = Country.KYRGYZSTAN;
                        case 2 -> country = Country.RUSSIA;
                        case 3 -> country = Country.USA;
                        case 4 -> country = Country.FRANCE;
                        case 5 -> country = Country.ENGLAND;
                        case 6 -> country = Country.POLSKA;
                        default -> System.out.println("No such gender!");
                    }
                    System.out.println("Enter phone number: ");
                    String phoneNumber = scanner1.next();
                    for (Person person : personList) {
                        if (person.getPhoneNumber().equals(phoneNumber)) {
                            return "This phone number already exists!";
                        }
                    }
                    Map<String, String> contact = new TreeMap<>();
                    Person person = new Person(id, firstName, lastName, dateOfBirth, gender, country, phoneNumber, contact);
                    this.personList.add(person);
//                    Passport passport = new Passport(id, firstName, lastName, dateOfBirth, gender, country);
//                    List<Passport>passports = new ArrayList<>();
//                    passports.add(passport);
//                    passports.addAll(personService.getAllPassport());
//                    personService.setPassportList(passports);
                    isCreated = true;
                }else {
                    return "Last name entered incorrectly";
                }
            }else {
                return "First name entered incorrectly";
            }
        } catch(InputMismatchException e){
            System.out.println("Incorrect input!");
        }
        if (isCreated) {
            return "Person successfully created!";
        } else {
            return "Failed to create a person!";
        }
    }

    @Override
    public String removePassport(String name) {
        boolean isRemoved = this.personList.removeIf(person -> person.getFirstName().equals(name));
//        this.passportList.removeIf(passport -> passport.getFirstName().equals(name));
        return isRemoved ? "Passport successfully removed!" : "Passport remove failed!";
    }

    @Override
    public List<Person> getAllPerson() {
        return personList;
    }

    @Override
    public String addContact() {
        System.out.println("Whose contact to add: ");
        String name = scanner.next();
        for (Person person : personList) {
            if(person.getFirstName().equals(name)) {
                System.out.println("Enter phone number: ");
                String number = scanner.next();
                for (Person person1:personList){
                    if (person1.getPhoneNumber().equals(number)) {
                        if(!person.getPhoneNumber().equals(number)) {
                            System.out.println("Enter name: ");
                            String contactName = scanner.next();
                            if (person.getContacts().size() != 0) {
                                person.getContacts().put(number, contactName);
                                return "Successfully Added!";
                            } else {
                                Map<String, String> contact = new TreeMap<>();
                                contact.put(number, contactName);
                                contact.putAll(person.getContacts());
                                person.setContacts(contact);
                                return "Successfully Added!";
                            }
                        }else {
                            return "This is your number!";
                        }
                    }
                }
            }
        }
        return "Added Failed!";
    }
    @Override
    public String removeContact() {
        boolean isRemoved = false;
        System.out.println("Whose contact you want to delete:");
        String name = scanner.next();
        for (Person person:personList) {
            if (person.getFirstName().equals(name)) {
                System.out.println(person.getContacts());
            }
        }
        System.out.println("Who do you want to remove:");
        String number = scanner.next();
        for (Person person1:personList){
            if (person1.getFirstName().equals(name)) {
                person1.getContacts().remove(number);
                isRemoved = true;
            }
        }
        return isRemoved ? "Contact successfully removed!" : "Contact remove failed!";
    }

    @Override
    public void getAllContact() {
        for (Person person:personList) {
            System.out.print(person.getFirstName()+" -> ");
            System.out.println(person.getContacts());
        }
    }
    @Override
    public List<Country> getAllCountry() {
        List<Country> countries = new ArrayList<>();
        countries.add(Country.KYRGYZSTAN);
        countries.add(Country.RUSSIA);
        countries.add(Country.USA);
        countries.add(Country.FRANCE);
        countries.add(Country.ENGLAND);
        countries.add(Country.POLSKA);
        return countries;
    }

    @Override
    public void getAllPersonGenderAndCountry() {
        for (Person person:personList) {
            System.out.println("Person: " + person.getFirstName() + " " + person.getLastName());
            System.out.println("Gender: " + person.getGender());
            System.out.println("Country: " + person.getCountry());
            System.out.println("------------------------------------------------");
        }
    }

    @Override
    public List<Person> findByName(String name) {
        List<Person> personList1 = new ArrayList<>();
        if(name.matches("[a-zA-Z]*")){
            for (Person person : this.personList) {
                if (person.getFirstName().equals(name)) {
                    personList1.add(person);
                }
            }
        }else {
            System.out.println("Incorrect input!");
        }
        return personList1;
    }
}
