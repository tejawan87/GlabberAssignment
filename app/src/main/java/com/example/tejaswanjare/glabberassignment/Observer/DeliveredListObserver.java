package com.example.tejaswanjare.glabberassignment.Observer;

import android.util.Log;

import com.example.tejaswanjare.glabberassignment.Activities.MessageInfoActivity;
import com.example.tejaswanjare.glabberassignment.Subject.MessageNotifier;
import com.example.tejaswanjare.glabberassignment.Model.MessageObject;
import com.example.tejaswanjare.glabberassignment.Model.MessageStatus;

public class DeliveredListObserver extends MessageNotifier {
    MessageInfoActivity messageInfoActivity;
    public DeliveredListObserver(MessageInfoActivity messageInfoActivity, MessageObject messageObject) {
        this.messageObject = messageObject;
        this.messageInfoActivity = messageInfoActivity;
        messageObject.attach(this);

    }

    @Override
    public void updateMessageStatus(int messageId, MessageStatus messageStatus) {
        for(int i=0; i < messageInfoActivity.deleveredToArrayList.size(); i++) {

            if(messageStatus.getUserID() == messageInfoActivity.deleveredToArrayList.get(i).getUserID()){

                Log.i("Tejas", "updateMessageStatus:1 "+messageInfoActivity.deleveredToArrayList.get(i).getUserID());
                Log.i("Tejas", "updateMessageStatus:2 "+messageStatus.getUserID());
                messageInfoActivity.deleveredToArrayList.remove(i);
                messageInfoActivity.deliveredAdapter.notifyDataSetChanged();
                messageInfoActivity.addHeaderFooterView();
//                messageInfoActivity.setListViewHeightBasedOnChildren(messageInfoActivity.deliveredToListView);
            }
        }

    }
}
