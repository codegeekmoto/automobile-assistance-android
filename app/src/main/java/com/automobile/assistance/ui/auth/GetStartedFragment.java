package com.automobile.assistance.ui.auth;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.PagerAdapter;

import com.automobile.assistance.R;
import com.automobile.assistance.databinding.FragmentAuthGetStartedBinding;
import com.automobile.assistance.databinding.ItemBannerBinding;
import com.automobile.assistance.ui.model.GetStarted;

import java.util.ArrayList;
import java.util.List;

public class GetStartedFragment extends Fragment {

    private FragmentAuthGetStartedBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAuthGetStartedBinding.inflate(inflater, container, false);

        GetStartedAdapter getStartedAdapter = new GetStartedAdapter(getContext());
        binding.pager.setAdapter(getStartedAdapter);
        binding.indicator.setupWithViewPager(binding.pager);

        binding.btnGetStarted.setOnClickListener(v -> {
            Navigation.findNavController(getActivity(), R.id.auth_host_fragment)
                    .navigate(R.id.auth_select_login);
        });

        return binding.getRoot();
    }

    public class GetStartedAdapter extends PagerAdapter {

        private LayoutInflater mLayoutInflater;
        private List<GetStarted> getStartedList = new ArrayList<>();

        public GetStartedAdapter(Context context) {
            mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            getStartedList.add(new GetStarted(
                    "LET'S GET STARTED",
                    "Sign in quick, reliable, and safe automobile breakdown assistance."
            ));
            getStartedList.add(new GetStarted(
                    "PEACE OF MIND ON THE ROADS",
                    "Flat car battery or tires, towing or even if you run out of petrol, we've got your back!"
            ));
            getStartedList.add(new GetStarted(
                    "ASK US ANYTHING",
                    "We're your friendly neighbourhood mechanic on your mobile phone"
            ));
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ItemBannerBinding binding = ItemBannerBinding.inflate(mLayoutInflater, container, false);
            binding.title.setText(getStartedList.get(position).getTitle());
            binding.description.setText(getStartedList.get(position).getDescription());
            container.addView(binding.getRoot());
            return binding.getRoot();
        }

        @Override
        public int getCount() {
            return getStartedList.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((LinearLayout) object);
        }
    }

}
