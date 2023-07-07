package com.devbramm.soga.activities.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.devbramm.soga.activities.fragments.ChatsAllFragment;
import com.devbramm.soga.activities.fragments.ChatsFamilyFragment;
import com.devbramm.soga.activities.fragments.ChatsFriendsFragment;
import com.devbramm.soga.activities.fragments.ChatsWorkFragment;

public class TabsViewPagerAdapter extends FragmentStateAdapter {

    public TabsViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new ChatsAllFragment();
            case 1:
                return new ChatsFamilyFragment();
            case 2:
                return new ChatsFriendsFragment();
            case 3:
                return new ChatsWorkFragment();
            default:
                return new ChatsAllFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
