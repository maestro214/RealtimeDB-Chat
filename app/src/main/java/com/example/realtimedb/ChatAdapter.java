package com.example.realtimedb;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.core.Context;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {

    private List<ChatData> mDataset;
    private String myNickName;


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView Textview_nickname;
        public TextView TextView_msg;
        public View rootView;

        public MyViewHolder(View v){
            super(v);
            Textview_nickname = v.findViewById(R.id.TextView_nickname);
            TextView_msg = v.findViewById(R.id.TextView_msg);
            rootView = v;

            v.setClickable(true);
            v.setEnabled(true);


        }

    }

    public ChatAdapter(List<ChatData> myDataset, MainActivity context, String myNickName) {
        mDataset = myDataset;
        this.myNickName = myNickName;
    }


    @Override
    public ChatAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {

        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.row_chat,parent,false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ChatData chat = mDataset.get(position);

        holder.Textview_nickname.setText(chat.getNickname());
        holder.TextView_msg.setText(chat.getMsg());

        if(chat.getNickname().equals(this.myNickName)) {
            holder.TextView_msg.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
            holder.Textview_nickname.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        }
        else {

            holder.TextView_msg.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            holder.Textview_nickname.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        }


    }


    @Override
    public int getItemCount() {
        return mDataset==null ? 0 : mDataset.size();
    }

    public ChatData getChat(int position) {
        return mDataset != null ? mDataset.get(position) : null;
    }

    public void addChat(ChatData chat) {
        mDataset.add(chat);
        notifyItemInserted(mDataset.size() - 1); //데이터가 삽입되는 것의 변화를 위해 notifyiteminserted사용
    }
    }



