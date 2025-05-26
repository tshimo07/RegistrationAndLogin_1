/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package registrationandlogin;


/**
 *
 * @author RC_Student_lab
 */
public class CheckMessage {
    private static String allMessages = "";
    private static int totalMessages = 0;
    
    public boolean isValidMessageId(String messageId){
        return messageId != null && messageId.length() <=10;
    }
        public boolean isValidUserId(String userId){
            return userId != null && userId.matches("\\d{10}");

        }
    public int checkRecipientCell(String cellNumber){
        if(cellNumber != null && cellNumber.matches("\\+27\\d{9}")){
            return 1;
        }else{
            return 0;
        }
    }
    public String createMessageHash(String userId, int messageCount, String message){
        String[]words = message.trim().split("\\s+");
        String firstWord = words.length > 0 ? words[0].toUpperCase(): "NA";
        String lastWord = words.length > 1 ? words[words.length - 1].toUpperCase(): firstWord;
        
        String idPart = userId.substring(0,2);
        return idPart + ":" + messageCount + firstWord + lastWord;
    }
   
    

    // Call this method every time a message is sent
    public void storeMessage(String messageSummary) {
        allMessages += messageSummary + "\n";
        totalMessages++;
    }

    // Returns all messages sent
    public String printMethod() {
        if (allMessages.isEmpty()) {
            return "No messages sent.";
        } else {
            return "Messages Sent:\n" + allMessages;
        }
    }
     
    public int returnTotalMessage() {
        return totalMessages;
    }
   
}


    







