package com.example.tejaswanjare.glabberassignment.Model;

import com.example.tejaswanjare.glabberassignment.Subject.MessageNotifier;

import java.util.ArrayList;
import java.util.List;

public class MessageObject {


    private int messageID;
    private long messageTime; // unix epoch time
    private String messageText;
    private ArrayList<MessageStatus> messagStatus;//userId's who  read the message or who received the message
    private boolean sent; // message sent successfully
    private List<MessageNotifier> messageNotifiers = new ArrayList<MessageNotifier>();

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public long getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public ArrayList<MessageStatus> getMessagStatus() {
        return messagStatus;
    }

    public void setMessagStatus(ArrayList<MessageStatus> messagStatus) {
        this.messagStatus = messagStatus;
//        notifyAllObservers();
    }

    public void setMessageState(MessageStatus messageState) {
        notifyAllObservers(messageState);
    }

    public void attach(MessageNotifier messageNotifier) {
        messageNotifiers.add(messageNotifier);
    }

    public void notifyAllObservers(MessageStatus messageState) {
        for (MessageNotifier messageNotifier : messageNotifiers) {
            messageNotifier.updateMessageStatus(messageID, messageState);
        }
    }

}
