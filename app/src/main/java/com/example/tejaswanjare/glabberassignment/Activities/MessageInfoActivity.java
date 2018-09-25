package com.example.tejaswanjare.glabberassignment.Activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tejaswanjare.glabberassignment.Adapters.MessageStatusAdapter;
import com.example.tejaswanjare.glabberassignment.Model.MessageObject;
import com.example.tejaswanjare.glabberassignment.Model.MessageStatus;
import com.example.tejaswanjare.glabberassignment.Observer.DeliveredListObserver;
import com.example.tejaswanjare.glabberassignment.Observer.RedListObserver;
import com.example.tejaswanjare.glabberassignment.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MessageInfoActivity extends AppCompatActivity {

    public ListView readByListView;
    public ListView deliveredToListView;
    private String TAG = "MessageInfoActivity";
    public ArrayList<MessageStatus> readByArrayList = new ArrayList<>();
    public ArrayList<MessageStatus> deleveredToArrayList = new ArrayList<>();
    public ArrayList<MessageStatus> sendingToArrayList = new ArrayList<>();
    public ArrayList<MessageStatus> sentToArrayList = new ArrayList<>();
    private MessageObject messageObject;
    public MessageStatusAdapter deliveredAdapter;
    public MessageStatusAdapter redAdapter;
    public ArrayList<MessageStatus> messagStatus;
    public TextView messageTV;
    public TextView messageTimeTV;
    public final int STATUS_DELIVERED = 1;
    public final int STATUS_RED = 2;
    public final int STATUS_SENT = 3;
    public final int STATUS_SENDING = 4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_info);
        setViewById();
        setUpToolbar();
        generateSampleData();
        separateList(messageObject);
        setListView();
        addHeaderFooterView();
        setListViewHeightBasedOnChildren(deliveredToListView);
        setListViewHeightBasedOnChildren(readByListView);
        setObserver();
        setMessage();
    }

    private void setMessage() {
        if (messageObject.isSent()) {
            Date dt = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa");
            messageTimeTV.setText(sdf.format(dt));

            messageTV.setText("This is sample Message");
        }
    }


    private void setObserver() {
        new DeliveredListObserver(this, messageObject);
        new RedListObserver(this, messageObject);
    }

    private void separateList(MessageObject messageObject) {
        for (int i = 0; i < messageObject.getMessagStatus().size(); i++) {
            MessageStatus messageStatus = messageObject.getMessagStatus().get(i);
            switch (messageStatus.getStatus()){
                case 1:
                    deleveredToArrayList.add(messageStatus);
                    break;

                case 2:
                    readByArrayList.add(messageStatus);
                    break;

                case 3:
                    sentToArrayList.add(messageStatus);
                    break;

                case 4:
                    sendingToArrayList.add(messageStatus);

            }
        }
    }


    private void setListView() {
        deliveredAdapter = new MessageStatusAdapter(deleveredToArrayList, MessageInfoActivity.this);
        deliveredToListView.setAdapter(deliveredAdapter);

        redAdapter = new MessageStatusAdapter(readByArrayList, MessageInfoActivity.this);
        readByListView.setAdapter(redAdapter);
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();

        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            if (listItem instanceof ViewGroup) {
                listItem.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }

            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1) + 10);
        listView.setLayoutParams(params);
    }

    private void setViewById() {
        readByListView = findViewById(R.id.readByListView);
        deliveredToListView = findViewById(R.id.deliveredToListView);
        messageTimeTV = findViewById(R.id.messageTimeTV);
        messageTV = findViewById(R.id.messageTV);
    }

    private void setUpToolbar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Message Info");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Log.i(TAG, "onBackPressed: ");
        messageObject.setMessageState(new MessageStatus(4, STATUS_RED, new Date().getTime(), "Oreo"));
    }

    public void generateSampleData() {
        messageObject = new MessageObject();
        messageObject.setSent(true);
        messageObject.setMessageID(1);
        messageObject.setMessageText("that you wish everyone on bornday");
        messageObject.setMessageTime(new Date().getTime());
        messagStatus = new ArrayList<>();
        messagStatus.add(new MessageStatus(4, STATUS_DELIVERED, new Date().getTime(), "Oreo"));
        messagStatus.add(new MessageStatus(5, STATUS_RED, new Date().getTime(), "Nougat"));
        messagStatus.add(new MessageStatus(6, STATUS_SENT, new Date().getTime(), "Marshmallow"));
        messagStatus.add(new MessageStatus(12, STATUS_SENT, new Date().getTime(), "Lollipop"));
        messagStatus.add(new MessageStatus(7, STATUS_DELIVERED, new Date().getTime(), "KitKat"));
        messagStatus.add(new MessageStatus(8, STATUS_SENDING, new Date().getTime(), "Jelly Bean"));
        messagStatus.add(new MessageStatus(9, STATUS_RED, new Date().getTime(), "Ice Cream Sandwich"));
        messagStatus.add(new MessageStatus(10, STATUS_DELIVERED, new Date().getTime(), "Honeycomb"));
        messagStatus.add(new MessageStatus(11, STATUS_RED, new Date().getTime(), "Gingerbread"));
        messageObject.setMessagStatus(messagStatus);
    }

    public void addHeaderFooterView() {

        //Header View for read by list
        View headerView1 = LayoutInflater.from(MessageInfoActivity.this).inflate(R.layout.list_header, null, false);
        TextView headerTitle1 = (TextView) headerView1.findViewById(R.id.headerTitleTV);
        headerTitle1.setText("Read By");//set the text to Header View
        readByListView.addHeaderView(headerView1);//Add view to list view as header view

        //Header View for delevered to list
        View headerView2 = LayoutInflater.from(MessageInfoActivity.this).inflate(R.layout.list_header, null, false);
        TextView headerTitle2 = (TextView) headerView2.findViewById(R.id.headerTitleTV);
        headerTitle2.setText("Delevered to");//set the text to Header View
        deliveredToListView.addHeaderView(headerView2);//Add view to list view as header view

        //footer View for read by list
        View footerView1 = LayoutInflater.from(MessageInfoActivity.this).inflate(R.layout.list_footer, null, false);
        TextView footerTitle1 = (TextView) footerView1.findViewById(R.id.footerTV);
        footerTitle1.setText("Remaining "+sentToArrayList.size());//set the text to Footer View
        readByListView.addFooterView(footerView1);//Add view to list view as footer view

        //footer View for delevered to list
        View footerView2 = LayoutInflater.from(MessageInfoActivity.this).inflate(R.layout.list_footer, null, false);
        TextView footerTitle2 = (TextView) footerView2.findViewById(R.id.footerTV);
        footerTitle2.setText("Remaining "+sendingToArrayList.size());//set the text to Footer View
        deliveredToListView.addFooterView(footerView2);//Add view to list view as footer view
    }


}
