/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package registrationandlogin;

/**
 *
 * @author RC_Student_lab
 */
public class message {
    public String userId;
    public String messageText;
    public String hash;
    
    public message(String userId, String messageText, String hash){
        this.userId = userId;
        this.messageText = messageText;
        this.hash = hash;
    }
    
}
