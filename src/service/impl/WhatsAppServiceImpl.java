package service.impl;

import classes.Person;
import classes.Profile;
import classes.WhatsApp;
import enums.Status;
import service.WhatsAppService;

import java.util.*;

public class WhatsAppServiceImpl implements WhatsAppService {
    public Scanner scanner = new Scanner(System.in);
    private List<Profile> profileList = new ArrayList<>();
    private List<WhatsApp> whatsAppList = new ArrayList<>();
    private Map<String, String> sendMessageList =new LinkedHashMap<>();
    private Map<String, String> readMessageList =new LinkedHashMap<>();
    private Set<String> sendContact = new LinkedHashSet<>();

    public List<Profile> getProfileList() {
        return profileList;
    }

    public void setProfileList(List<Profile> profileList) {
        this.profileList = profileList;
    }

    public List<WhatsApp> getWhatsAppList() {
        return whatsAppList;
    }

    public void setWhatsAppList(List<WhatsApp> whatsAppList) {
        this.whatsAppList = whatsAppList;
    }

    public Map<String, String> getSendMessageList() {
        return sendMessageList;
    }

    public void setSendMessageList(Map<String, String> messageList) {
        this.sendMessageList = messageList;
    }

    public Map<String, String> getReadMessageList() {
        return readMessageList;
    }

    public void setReadMessageList(Map<String, String> readMessageList) {
        this.readMessageList = readMessageList;
    }

    public Set<String> getSendContact() {
        return sendContact;
    }

    public void setSendContact(Set<String> sendContact) {
        this.sendContact = sendContact;
    }

    @Override
    public String signIn(List<Person> personList) {
        int counter = profileList.size();
        System.out.println("Enter your passport ID: ");
        int id = scanner.nextInt();
        if (counter > 0) {
            for (Profile profile : this.profileList) {
                for (Person person : personList) {
                    if (person.getId() == id && profile.getId() != id) {
                        System.out.println("Enter phone number: ");
                        String number = scanner.next();
                        if (person.getPhoneNumber().equals(number) && !profile.getPhoneNumber().equals(number)) {
                            System.out.println("Create password: ");
                            String password = scanner.next();
                            if (!profile.getPassword().equals(password)) {
                                System.out.println("Enter username: ");
                                String userName = scanner.next();
                                if (!profile.getUsername().equals(userName)) {
                                    Profile profile1 = new Profile(id, userName, password, number, Status.ONLINE);
                                    this.profileList.add(profile1);
                                    return "Successfully registered!";
                                }
                            }
                        }
                    }
                }
                return "Registered failed!";
            }
        } else {
            for (Person person1 : personList) {
                if (person1.getId() == id) {
                    System.out.println("Enter phone number: ");
                    String number = scanner.next();
                    if (person1.getPhoneNumber().equals(number)) {
                        System.out.println("Create password: ");
                        String password = scanner.next();
                        System.out.println("Enter username: ");
                        String userName = scanner.next();
                        Profile profile1 = new Profile(id, userName, password, number, Status.ONLINE);
                        this.profileList.add(profile1);
                        return "Successfully registered!";
                    } else {
                        System.out.println("This is not your number!");
                    }
                }
            }
        }
        return "Registered failed!";
    }

    @Override
    public String removeProfile() {
        System.out.println("Enter username: ");
        String username = scanner.next();
        boolean isRemoved = this.profileList.removeIf(profile -> profile.getUsername().equals(username));
        this.whatsAppList.removeIf(whatsApp -> whatsApp.getUsername().equals(username));
        return isRemoved ? "Successfully removed!" : "Removed failed!";
    }

    @Override
    public String logIn(List<Person>personList) {
        try {
            System.out.println("Enter ID:");
            int id = scanner.nextInt();
            for (Profile profile : profileList) {
                if (profile.getId() == id) {
                    System.out.println("Enter password: ");
                    String password = scanner.next();
                    if (profile.getPassword().equals(password)) {
                        while (true) {
                            System.out.println("""
                                    1 -> Chat,
                                    2 -> Change your status,
                                    3 -> Show your profile,
                                    4 -> Search in your contacts,
                                    5 -> Remove your profile,
                                    6 -> Show all contacts,
                                    0 -> Logout;
                                    """);
                            int number = scanner.nextInt();
                            switch (number) {
                                case 1:
                                    Map<String, String> contacts = new TreeMap<>();
                                    for (Person person : personList) {
                                        if (person.getId() == id) {
                                            System.out.println("------------Chat------------");
                                            for (Map.Entry<String, String> entry : person.getContacts().entrySet()) {
                                                for (Profile profile1 : profileList) {
                                                    if (entry.getKey().equals(profile1.getPhoneNumber())) {
                                                        if (entry.getKey().equals(profile1.getPhoneNumber())) {
                                                            System.out.println(entry.getValue());
                                                            contacts.put(entry.getKey(), entry.getValue());
                                                            break;
                                                        }
                                                    }
                                                }
                                                System.out.println("----------------------------");
                                            }
                                            System.out.println("Choice: ");
                                            String userName = scanner.next();
                                            boolean isChoice = true;
                                            while (isChoice) {
                                                for (Map.Entry<String, String> entry : person.getContacts().entrySet()) {
                                                    if (entry.getValue().equals(userName)) {
                                                        for (Map.Entry<String, String> mes : sendMessageList.entrySet()) {
                                                            System.out.println(mes.getValue() + ": " + mes.getKey());
                                                        }
                                                        System.out.println("Write: ");
                                                        String message = new Scanner(System.in).nextLine();
                                                        System.out.println("Send? - y/n");
                                                        String choice = new Scanner(System.in).nextLine().toLowerCase();
                                                        if (choice.equals("y") || choice.equals("yes")) {
                                                            sendMessageList.put(message, profile.getUsername());
                                                            if (entry.getValue().equals(userName)) {
                                                                sendContact.add(entry.getKey());
                                                            }
                                                        } else {
                                                            isChoice = false;
                                                        }
                                                    }else {
                                                        isChoice = false;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    break;
                                case 2:
                                    if (profile.getId() == id) {
                                        System.out.println(Arrays.toString(Status.values()));
                                        System.out.println("Write status: ");
                                        String status = scanner.next().toLowerCase();
                                        switch (status) {
                                            case "online" -> profile.setStatus(Status.ONLINE);
                                            case "to_the_cinema" -> profile.setStatus(Status.TO_THE_CINEMA);
                                            case "in_gym" -> profile.setStatus(Status.IN_GYM);
                                            case "sleep" -> profile.setStatus(Status.SLEEP);
                                            case "working" -> profile.setStatus(Status.WORKING);
                                            case "coding" -> profile.setStatus(Status.CODING);
                                            default -> System.out.println("No such status!");
                                        }
                                        System.out.println("Successfully changed!");
                                    }
                                    break;
                                case 3:
                                    if (profile.getId() == id) {
                                        System.out.println(profile);
                                    }
                                    break;
                                case 4:
                                    System.out.println("Enter contact name: ");
                                    String contactName = scanner.next();
                                    boolean isUsed = false;
                                    for (Person person : personList) {
                                        if (person.getPhoneNumber().equals(profile.getPhoneNumber())) {
                                            System.out.println("-----Contact Found-----");
                                            for (Map.Entry<String, String> entry : person.getContacts().entrySet()) {
                                                if (entry.getValue().equals(contactName)) {
                                                    for (Profile profile1:profileList){
                                                        if (entry.getKey().equals(profile1.getPhoneNumber())) {
                                                            isUsed = true;
                                                        }
                                                    }
                                                    if (isUsed){
                                                        System.out.println(entry + " -> He (She) uses WhatsApp!");
                                                    }else {
                                                        System.out.println(entry + " -> He (She) doesn't use WhatsApp!");
                                                    }
                                                }
                                            }
                                            System.out.println("-------------------------");
                                        }
                                    }
                                    break;
                                case 5:
                                    System.out.println("Remove your profile? 1(yes), 2(no): ");
                                    int choice = scanner.nextInt();
                                    boolean isRemoved = false;
                                    if (choice == 1) {
                                        isRemoved = this.profileList.removeIf(profile1 -> profile1.getId() == id);
                                        this.whatsAppList.removeIf(whatsApp -> whatsApp.getId() == id);
                                    } else {
                                        break;
                                    }
                                    return isRemoved ? "Successfully removed!" : "Removed failed!";
                                case 6:
                                    boolean isUsed1 = false;
                                    for (Person person : personList) {
                                        if (person.getPhoneNumber().equals(profile.getPhoneNumber())) {
                                            System.out.println("----------Contacts----------");
                                            for (Map.Entry<String, String> entry : person.getContacts().entrySet()) {
                                                isUsed1 = false;
                                                for (Profile profile1:profileList){
                                                    if (entry.getKey().equals(profile1.getPhoneNumber())) {
                                                        isUsed1 = true;
                                                        break;
                                                    }
                                                }
                                                if (isUsed1){
                                                    System.out.println(entry + " -> He (She) uses WhatsApp!");
                                                }else {
                                                    System.out.println(entry + " -> He (She) doesn't use WhatsApp!");
                                                }
                                            }
                                            System.out.println("----------------------------");
                                        }
                                    }
                                    break;
                                case 0:
                                    return " ";
                                default:
                                    System.out.println("No such number!");
                                    break;
                            }
                        }
                    }
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Incorrect input!");
        }
        return "Login failed!";
    }

    @Override
    public List<Profile> getAllProfile() {
        return profileList;
    }

    @Override
    public List<String> getChat() {
        return null;
    }

    @Override
    public List<Profile> findByProfile(String userName) {
        List<Profile> profiles = new ArrayList<>();
        if(userName.matches("[a-zA-Z]*")){
            for (Profile profile : this.profileList) {
                if (profile.getUsername().equals(userName)) {
                    profiles.add(profile);
                }
            }
        }else {
            System.out.println("Incorrect input!");
        }
        return profiles;
    }
}
