package classes;

import enums.Status;

public class Profile {
    private int id;
    private String username;
    private String password;
    private String PhoneNumber;
    private Status status;
    public Profile() {
    }

    public Profile(int id, String username, String password, String phoneNumber, Status status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.PhoneNumber = phoneNumber;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Profile:" +
                "\nId = " + id +
                "\nusername = " + username +
                "\npassword = " + password +
                "\nPhoneNumber = " + PhoneNumber +
                "\nStatus = " + status + "\n";
    }
}
