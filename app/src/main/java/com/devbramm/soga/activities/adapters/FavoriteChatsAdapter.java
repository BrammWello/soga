package com.devbramm.soga.activities.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devbramm.soga.R;
import com.devbramm.soga.activities.models.ChatItemList;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class FavoriteChatsAdapter extends RecyclerView.Adapter<FavoriteChatsAdapter.FavoriteChatsViewHolder> {

    Context context;
    ArrayList<ChatItemList> chatItemLists;

    public FavoriteChatsAdapter(Context context, ArrayList<ChatItemList> chatItemLists) {
        this.context = context;
        this.chatItemLists = chatItemLists;
    }

    @NonNull
    @Override
    public FavoriteChatsAdapter.FavoriteChatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_chat_layout, parent, false);
        return new FavoriteChatsAdapter.FavoriteChatsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteChatsAdapter.FavoriteChatsViewHolder holder, int position) {
        holder.tvChatItemName.setText(chatItemLists.get(position).getChatName());
        holder.tvChatItemDesc.setText(chatItemLists.get(position).getChatText());
        holder.tvChatItemTime.setText(chatItemLists.get(position).getChatTime());
    }

    @Override
    public int getItemCount() {
        return chatItemLists.size();
    }

    public class FavoriteChatsViewHolder extends RecyclerView.ViewHolder {

        TextView tvChatItemName, tvChatItemDesc, tvChatItemTime;
        public FavoriteChatsViewHolder(@NonNull View itemView) {
            super(itemView);

            tvChatItemName = itemView.findViewById(R.id.tv_chat_item_name);
            tvChatItemDesc = itemView.findViewById(R.id.tv_chat_item_desc);
            tvChatItemTime = itemView.findViewById(R.id.tv_chat_item_time);
        }
    }
}
