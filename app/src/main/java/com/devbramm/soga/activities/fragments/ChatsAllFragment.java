package com.devbramm.soga.activities.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devbramm.soga.R;
import com.devbramm.soga.activities.adapters.FavoriteChatsAdapter;
import com.devbramm.soga.activities.models.ChatItemList;

import java.util.ArrayList;
import java.util.List;

public class ChatsAllFragment extends Fragment {

    private RecyclerView rvFavoriteChats, rvConversationChats;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_chats_all, container, false);

        // Find the RecyclerView in the XML layout
        rvFavoriteChats = rootView.findViewById(R.id.rv_favorite_chats);
        rvConversationChats = rootView.findViewById(R.id.rv_conversation_chats);

        // Set up the RecyclerView with layout manager and adapter
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getActivity());
        rvFavoriteChats.setLayoutManager(layoutManager);
        rvConversationChats.setLayoutManager(layoutManager2);

        // Create a list of data items
        ArrayList<ChatItemList> chatItems = new ArrayList<>();
        chatItems.add(new ChatItemList("Stacy", "Would you mind us having dinner tommorow?", "9:43 AM"));
        chatItems.add(new ChatItemList("Bramm 2", "You are a MAN", "12:00 PM"));
        chatItems.add(new ChatItemList("Dev Wello", "Reach out to the members in the team", "9:22 AM"));

        // Create an adapter for the RecyclerView and set it
        FavoriteChatsAdapter adapter = new FavoriteChatsAdapter(rootView.getContext(), chatItems);
        rvFavoriteChats.setAdapter(adapter);

        // Create a list of data items
        ArrayList<ChatItemList> chatItems2 = new ArrayList<>();
        chatItems2.add(new ChatItemList("Alice", "What time is the meeting?", "3:15 PM"));
        chatItems2.add(new ChatItemList("John Doe", "I'll be late for the party", "6:30 PM"));
        chatItems2.add(new ChatItemList("Emma", "Can you send me the report?", "11:05 AM"));
        chatItems2.add(new ChatItemList("Sarah", "Let's go for a walk", "5:50 PM"));
        chatItems2.add(new ChatItemList("Michael", "How was your day?", "7:20 AM"));
        chatItems2.add(new ChatItemList("Olivia", "Do you have any plans for the weekend?", "2:55 PM"));
        chatItems2.add(new ChatItemList("David", "See you later!", "8:10 PM"));
        chatItems2.add(new ChatItemList("Sophie", "I can't wait to see you!", "10:30 AM"));
        chatItems2.add(new ChatItemList("Max", "Let's grab lunch together", "1:20 PM"));
        chatItems2.add(new ChatItemList("Emily", "Did you watch the latest episode?", "4:45 PM"));

        // Create an adapter for the RecyclerView and set it
        FavoriteChatsAdapter adapter2 = new FavoriteChatsAdapter(rootView.getContext(), chatItems2);
        rvConversationChats.setAdapter(adapter2);


        return rootView;
    }
}
