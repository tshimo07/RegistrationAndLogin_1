/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package registrationandlogin;
import javax.swing.JOptionPane;


/**
 *
 * @author RC_Student_lab
 */
public class CheckMessage {

    public CheckMessage() {
        this("+27834557896", "Did you get the cake?", "Sent");
    }

    public CheckMessage(String string, String did_you_get_the_cake, String sent) {
    }
     // Arrays to store message summeries,disregarded messages, user's information, and hashes (maximum 100).
    private String[] summaries = new String[100];
    static String[] messages = new String[100];
    static String[] userIds = new String[100];
    static String[] recipients = new String[100];
    static String[] hash = new String[100];
    public static String[] disregardMsg = new String[100];
    static String[] flags = new String[3];
   // Counters to calculate how many messages were sent, disregarded, and Summaries created.
    static int messageCount = 0;
    private int summaryCount = 0;
    public static int disregardCount = 0;
    // Method to check if the message id entered is correct.
    public boolean isValidMessageId(String messageId){
        return messageId != null && messageId.length() <=10;
    }
    // Method to check if the user id entered is correct.
    public boolean isValidUserId(String userId){
            return userId != null && userId.matches("\\d{10}");
    }
     // Method to check if the user phone number entered is correct.
    public int checkRecipientCell(String cellNumber){
        if(cellNumber != null && cellNumber.matches("\\+27\\d{9}")){
            return 1;
        }else{
            return 0;
        }
    } 
    // Generates a unique hash string for each message
    public String createMessageHash(String userId, int messageCount, String message) {
    // Split the message into words
    String[] words = message.trim().split("\\s+");
    // Get first and last words (fallback to "NA" if needed)
    String firstWord = words.length > 0 ? words[0].toUpperCase() : "NA";
    String lastWord = words.length > 1 ? words[words.length - 1].toUpperCase() : firstWord;
    // Extract first 2 characters from user ID
    String idPart = userId.substring(0, 2);
     // Combine to form the message hash
    return idPart + ":" + messageCount + firstWord + lastWord;
}

    // Stores a summary of a sent message in the summaries array
    public void storeMessage(String summary) {
    // Check if there is space to store the summary
    if (summaryCount < summaries.length) {
        // Add summary to the array and increment the counter
        summaries[summaryCount] = summary;
        summaryCount++;
    }
    }
     
    // Returns a list of all stored full message details
    public static String printMethod() {
    String result = "";
    for (int index = 0; index < messageCount; index++) {
        result += (index + 1) + ". " + messages[index] + "\n";
    }
    // Return default message if no messages are stored
    return result.isEmpty() ? "No message sent yet." : result;
}
    // Returns a list of all stored message summaries
    public String printMethods() {
    String output = "";
    for (int i = 0; i < summaryCount; i++) {
        output += (i + 1) + ". " + summaries[i] + "\n";
    }
    return output;
}

    
            
     
   // Returns the total number of messages that have been stored
    public int returnTotalMessage() {
    return messageCount;
}

   // Returns the longest message stored based on character length
    public static String getLongestMessage() {
    if (messageCount == 0)
        return "No message have been sent.";
    
    String longest = messages[0];
    for (int i = 1; i < messageCount; i++) {
        if (messages[i] != null && messages[i].length() > longest.length()) {
            longest = messages[i];
        }
    }
    return "Longest Message:\n " + longest + "\nCharacters used: " + longest.length();
}

   // Stores the message details including user ID, recipient cell number, and message content
    public static void store(String id, String cell, String msg) {
    userIds[messageCount] = id;
    recipients[messageCount] = cell;
    messages[messageCount] = msg;
    messageCount++;
}

    // Searches for a message by user ID and returns the corresponding recipient and message content
    public static String searchById(String id) {
    for (int i = 0; i < messageCount; i++)
        if (id.equals(userIds[i]))
            return "Recipient: " + recipients[i] + "\nMessage: " + messages[i];
    return "No message found with ID: " + id;
}

    public void findMessageByRecipient() {
    // Prompt the user to enter the recipient number they want to search for
         String searchRecipient = JOptionPane.showInputDialog(null, "Enter recipient number (e.g. +27...) to search:");
    // Check if the user entered a value 
    if (searchRecipient != null && !searchRecipient.trim().isEmpty()) {
        // Remove leading/trailing spaces from input
        searchRecipient = searchRecipient.trim();
        // Use StringBuilder to efficiently build the result string
        StringBuilder result = new StringBuilder();
        boolean found = false;  // Flag to track if any messages are found
        // Loop through all stored messages
    for (int i = 0; i < messageCount; i++) {
            // Check if the current recipient matches the search input
        if (searchRecipient.equals(recipients[i])) {
                // Append message details to the result string
            result.append("ID: ").append(userIds[i]).append("\nMessage: ").append(messages[i]).append("\n\n");
            found = true;  // Mark that at least one message has been found
            }
        }
        // Display results if any messages were found
        if (found) {
            JOptionPane.showMessageDialog(null, "Messages sent to " + searchRecipient + ":\n\n" + result.toString());
        } else {
            // Inform user if no messages were found for the recipient
            JOptionPane.showMessageDialog(null, "No messages found for this recipient.");
        }
        } else {
        // Inform user if search was canceled or input was empty
        JOptionPane.showMessageDialog(null, "Search canceled.");
    }
}
   //Method called when user wants to delete messages using hash
    public void deleteMessageByHash() {
    // Prompt user to enter the hash of the message they want to delete
    String hashToDelete = JOptionPane.showInputDialog(null, "Enter the message hash to delete:");
   // If user cancels or enters nothing, cancel the operation
    if (hashToDelete == null || hashToDelete.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Operation canceled.");
        return;
    }
    // Flag to track if the message was found
    boolean found = false;  
    // Loop through all messages to find a matching hash
    for (int i = 0; i < messageCount; i++) {
        if (hash[i] != null && hash[i].equals(hashToDelete)) {
            // Shift all elements after the found index one position left to overwrite deleted message
            for (int j = i; j < messageCount - 1; j++) {
                userIds[j] = userIds[j + 1];
                messages[j] = messages[j + 1];
                recipients[j] = recipients[j + 1];
                hash[j] = hash[j + 1];
            }
            messageCount--;  // Decrease the count of messages since one is deleted
            found = true;  // Mark that the message was found and deleted

            // Notify user that deletion was successful
            JOptionPane.showMessageDialog(null, "Message with hash " + hashToDelete + " deleted.");
            break;  // Exit the loop since the message is deleted
        }
    }
    // If no message with the entered hash was found, notify the user
    if (!found) {
        JOptionPane.showMessageDialog(null, "No message found with that hash.");
    }
}
    //Method called when displaying full details of message sent
    public void displayFullMessageDetails() {
    // If there are no messages, inform the user and exit
    if (messageCount == 0) {
        JOptionPane.showMessageDialog(null, "No messages have been sent.");
        return;
    }
     // Build a detailed string of all messages for display
    StringBuilder details = new StringBuilder("FULL MESSAGE DETAILS:\n\n");

    // Loop through all messages and append their details to the string
    for (int i = 0; i < messageCount; i++) {
        details.append("Message ").append(i + 1).append(":\n");
        details.append("User ID: ").append(userIds[i]).append("\n");
        details.append("Recipient: ").append(recipients[i]).append("\n");
        details.append("Message: ").append(messages[i]).append("\n");
        details.append("Hash: ").append(hash[i]).append("\n\n");
    }
    // Show the complete list of messages in a dialog
    JOptionPane.showMessageDialog(null, details.toString());
    }  
    //method to store disregarded messages by the user
    public static void disregardMsg(String msg){
        if(disregardCount< disregardMsg.length){
           disregardMsg[disregardCount] = msg;
           disregardCount++;
        }
    }
    public static String getFlag() {
        flags[messageCount] = "send";
        flags[messageCount] = "Stored";
        flags[messageCount] = "Disregarded";
        return null;
    }
    //Method for test data for my units test

    Object messages() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

   
    






    







