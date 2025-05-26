/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package registrationandlogin;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.util.*;

public class MessageStorage {
    private static final String FILE_PATH = "messages.json";
    private static Gson gson = new Gson();

    public static void saveMessage(message msg) {
        List<message> messages = loadMessages();
        messages.add(msg);
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(messages, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<message> loadMessages() {
        try (Reader reader = new FileReader(FILE_PATH)) {
            return gson.fromJson(reader, new TypeToken<List<message>>() {}.getType());
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}

/**
 *
 * @author RC_Student_lab
 */

    

