import classes.Person;
import enums.Country;
import enums.Gender;
import service.impl.PersonServiceImpl;
import service.impl.WhatsAppServiceImpl;

import java.time.LocalDate;
import java.util.*;

public class Main {
    public static PersonServiceImpl personService = new PersonServiceImpl();
    public static WhatsAppServiceImpl whatsAppService = new WhatsAppServiceImpl();
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Map<String,String>contact = new TreeMap<>();
        Person person1 = new Person(1,"Iskhak", "Abdukhamitov", LocalDate.of(2002,8,28), Gender.MALE, Country.KYRGYZSTAN,"+996507434242",contact);
        Person person2 = new Person(2,"Ilim", "Shabdanov", LocalDate.of(2003,10,3), Gender.MALE, Country.RUSSIA,"+996123456789",contact);
        Person person3 = new Person(3,"Alibek", "Altynbek Uulu", LocalDate.of(2002,3,11), Gender.MALE, Country.USA,"+996123123123",contact);
        List<Person> people = new ArrayList<>(List.of(person1,person2,person3));
        commands(people);
    }

    public static void commands(List<Person> people) {
        boolean isTrue1 = true;
        try {
            while (isTrue1) {
                System.out.println("""
                        1 -> Person.
                        2 -> Whatsapp.
                        0 -> Stop.
                        """);
                int number = scanner.nextInt();
                boolean isTrue2 = true;
                while (isTrue2) {
                    switch (number) {
                        case 1:
                            System.out.println("""
                                    1 -> Add DB,
                                    2 -> Create person,
                                    3 -> Remove,
                                    4 -> Show all person,
                                    5 -> Add contact,
                                    6 -> Remove contact,
                                    7 -> Show all contact,
                                    8 -> Show all country,
                                    9 -> Show all person, gender and country,
                                    10 -> Find by name,
                                    0 -> Exit;
                                    """);
                            int num = scanner.nextInt();
                            switch (num) {
                                case 1 -> System.out.println(personService.addDB(people));
                                case 2 -> System.out.println(personService.createPerson());
                                case 3 -> {
                                    System.out.println("Enter student name: ");
                                    System.out.println(personService.removePassport(scanner.next()));
                                }
                                case 4 -> System.out.println(personService.getAllPerson());
                                case 5 -> System.out.println(personService.addContact());
                                case 6 -> System.out.println(personService.removeContact());
                                case 7 -> personService.getAllContact();
                                case 8 -> System.out.println(personService.getAllCountry());
                                case 9 -> personService.getAllPersonGenderAndCountry();
                                case 10 -> {
                                    System.out.println("Enter person name: ");
                                    String name = scanner.next();
                                    System.out.println(personService.findByName(name));
                                }
                                case 0 -> isTrue2 = false;
                                default -> System.out.println("Incorrect input!");
                            }
                            break;
                        case 2:
                            System.out.println("""
                                    1 -> Sign in,
                                    2 -> Log in,
                                    3 -> Remove profile,
                                    4 -> Show all profiles,
                                    5 -> Find by username,
                                    0 -> Exit;
                                    """);
                            num = scanner.nextInt();
                            switch (num) {
                                case 1 -> System.out.println(whatsAppService.signIn(personService.getPersonList()));
                                case 2 -> System.out.println(whatsAppService.logIn(personService.getPersonList()));
                                case 3 -> System.out.println(whatsAppService.removeProfile());
                                case 4 -> System.out.println(whatsAppService.getAllProfile());
                                case 5 -> System.out.println(whatsAppService.findByProfile(scanner.next()));
                                case 0 -> isTrue2 = false;
                                default -> System.out.println("No such number!");
                            }
                            break;
                        case 0:
                            isTrue2 = false;
                            isTrue1 = false;
                            break;
                    }
                }
            }

        }catch (InputMismatchException e){
            System.out.println("Incorrect input!");
            e.printStackTrace();
        }
    }
}