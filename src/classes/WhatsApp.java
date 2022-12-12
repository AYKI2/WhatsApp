package classes;

import enums.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class WhatsApp extends Profile{
    private Map<String, String> sendMessage = new TreeMap<>();
    private Map<String, String> readMessage = new TreeMap<>();
    public WhatsApp() {
    }

    public WhatsApp(int id, String username, String password, String phoneNumber, Status status, Map<String,String> sendMessage,Map<String,String> readMessage) {
        super(id, username, password, phoneNumber, status);
        this.sendMessage = sendMessage;
        this.readMessage = readMessage;
    }

    public Map<String,String> getMessage() {
        return sendMessage;
    }

    public void setMessage(Map<String,String> sendMessage) {
        this.sendMessage = sendMessage;
    }

    public Map<String, String> getReadMessage() {
        return readMessage;
    }

    public void setReadMessage(Map<String, String> readMessage) {
        this.readMessage = readMessage;
    }

    @Override
    public String toString() {
        return "\nSend message = " + sendMessage+
                "\nRead message = " + readMessage;
    }
}
