/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package registrationandlogin;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

public class MessageStorage {
    static message[] msgArr = new message[100];
    static int count = 0;

    

    public static void saveMessage(message msg) {
        if (count < msgArr.length) {
            msgArr[count] = msg;
            count++;
        }
    }

    public static void saveToJson(String filename) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(gson.toJson(java.util.Arrays.copyOf(msgArr, count)));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving JSON: " + e.getMessage());
        }
    }
    
}



   
    




/**
 *
 * @author RC_Student_lab
 */

    

