package com.example.locallaundryapp.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.locallaundryapp.View.Fragments.OrderSections.CancelledFragment;
import com.example.locallaundryapp.View.Fragments.OrderSections.CompletFregment;
import com.example.locallaundryapp.View.Fragments.OrderSections.UpcomingFragment;

public class OrderTabPagerAdapter extends FragmentStateAdapter {


    public OrderTabPagerAdapter(Fragment fragment){
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: return new UpcomingFragment();
                case 1: return new CancelledFragment();
                case 2: return new CompletFregment();
                default: return new UpcomingFragment();
            }
    }

    @Override
    public int getItemCount() {
        return 3;
    }



}
