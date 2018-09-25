package com.example.tejaswanjare.glabberassignment.Observer;

import com.example.tejaswanjare.glabberassignment.Activities.MessageInfoActivity;
import com.example.tejaswanjare.glabberassignment.Subject.MessageNotifier;
import com.example.tejaswanjare.glabberassignment.Model.MessageObject;
import com.example.tejaswanjare.glabberassignment.Model.MessageStatus;

public class RedListObserver extends MessageNotifier {
    MessageInfoActivity messageInfoActivity;

    public RedListObserver(MessageInfoActivity messageInfoActivity, MessageObject messageObject) {
        this.messageObject = messageObject;
        this.messageInfoActivity = messageInfoActivity;
        messageObject.attach(this);
    }

    @Override
    public void updateMessageStatus(int messageId, MessageStatus messageStatus) {
        messageInfoActivity.readByArrayList.add(messageStatus);
        messageInfoActivity.redAdapter.notifyDataSetChanged();
        messageInfoActivity.addHeaderFooterView();

    }
}
