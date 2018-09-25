package com.example.tejaswanjare.glabberassignment.Subject;

import com.example.tejaswanjare.glabberassignment.Model.MessageObject;
import com.example.tejaswanjare.glabberassignment.Model.MessageStatus;

public abstract class MessageNotifier {
    protected MessageObject messageObject;
    public abstract void updateMessageStatus(int messageId, MessageStatus messageStatus);
}
