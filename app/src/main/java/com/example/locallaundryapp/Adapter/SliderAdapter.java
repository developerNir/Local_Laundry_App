package com.example.locallaundryapp.Adapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.locallaundryapp.R;

public class SliderAdapter extends PagerAdapter {





        private Context context;
        LayoutInflater layoutInflater;


        public SliderAdapter(Context context) {
            this.context = context;
        }

        // Image array ---------------------------------------
        int Images[] = {
                R.drawable.picup,
                R.drawable.frame,
                R.drawable.takeup,

        };

        // header array ----------------------------------------
        int Headers[] ={
                R.string.headerOne,
                R.string.headerTwo,
                R.string.headerTree,
        };

        // description array ------------------------------------
        int Descriptions[] ={
                R.string.desOne,
                R.string.desTwo,
                R.string.desTree,

        };

        @Override
        public int getCount() {
            return Headers.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {

            // kon layout slide korba example LinearLayout ----------------------
            return view == (LinearLayout) object;
        }

        @SuppressLint("MissingInflatedId")
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {

            layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            // Inflater view Create ---------------------------------------
            View view = layoutInflater.inflate(R.layout.slider_layout, container, false);

            // element introduce -----------------------------------
            ImageView imageView = view.findViewById(R.id.imageView);
            TextView textView = view.findViewById(R.id.textView);
            TextView des = view.findViewById(R.id.TextDescription);

            imageView.setImageResource(Images[position]);
            textView.setText(Headers[position]);
            des.setText(Descriptions[position]);

            container.addView(view);

            return view;
        }


        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {



            // kon layout slide korba example LinearLayout ----------------------
            container.removeView((LinearLayout) object );
        }
    }


