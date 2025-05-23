package com.example.locallaundryapp.View.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.locallaundryapp.Adapter.OrderTabPagerAdapter;
import com.example.locallaundryapp.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class OrderFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_order, container, false);

        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);

        OrderTabPagerAdapter adapter = new OrderTabPagerAdapter(this);
        viewPager.setAdapter(adapter);

        // Link TabLayout with ViewPager
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    switch (position) {
                        case 0: tab.setText("Upcoming"); break;
                        case 1: tab.setText("Complete"); break;
                        case 2: tab.setText("Cancelled"); break;
                    }
                }).attach();





        return view;
    }
}