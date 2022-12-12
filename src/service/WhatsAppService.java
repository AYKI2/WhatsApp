package service;

import classes.Person;
import classes.Profile;
import java.util.List;

public interface WhatsAppService {
    String signIn(List<Person>personList);
    String removeProfile();
    String logIn(List<Person>personList);
    List<Profile> getAllProfile();
    List<String>getChat();
    List<Profile>findByProfile(String userName);
}