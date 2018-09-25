package com.example.tejaswanjare.glabberassignment.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tejaswanjare.glabberassignment.Model.MessageStatus;
import com.example.tejaswanjare.glabberassignment.R;

import java.text.DateFormat;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageStatusAdapter extends BaseAdapter {

    private ArrayList<MessageStatus> messageStatusArrayList;
    private Context context;
    private ViewHolder viewHolder;

    public MessageStatusAdapter(ArrayList<MessageStatus> messagStatus , Context context){
        this.messageStatusArrayList =messagStatus;
        this.context=context;
    }

    @Override
    public int getCount() {
        return messageStatusArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return messageStatusArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return messageStatusArrayList.get(i).getUserID();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        if(convertView == null) {
            viewHolder = new ViewHolder();
//            LayoutInflater inflater = LayoutInflater.from(context);
//            view = inflater.inflate(R.layout.user_list_item, viewGroup, false);
            convertView = LayoutInflater.from(context).inflate(R.layout.user_list_item, parent, false);

            viewHolder.circleImageView = (CircleImageView) convertView.findViewById(R.id.userImageView);
            viewHolder.userName = (TextView) convertView.findViewById(R.id.userName);
            viewHolder.time = (TextView) convertView.findViewById(R.id.time);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if(messageStatusArrayList.get(i) != null) {
            String s = DateFormat.getDateInstance(DateFormat.LONG).format(messageStatusArrayList.get(i).getTime());
            viewHolder.time.setText(s);
            viewHolder.userName.setText(messageStatusArrayList.get(i).getUserName());
        }
        return convertView;
    }

    private class ViewHolder {
        CircleImageView circleImageView;
        TextView userName;
        TextView time;
    }
}
