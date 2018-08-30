package com.dal.hrm_management.views.manage_absence.Hr;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dal.hrm_management.R;
import com.dal.hrm_management.views.manage_absence.Hr.holiday.HolidayHRFragment;
import com.dal.hrm_management.views.manage_absence.Hr.ListAbsence.AbsenceListHRFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AbsenceForHRFragment extends Fragment {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public AbsenceForHRFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_absence_for_hr, container, false);
        setHasOptionsMenu(true);
        initUI(view);
        return view;
    }

    private void getDataFromApi() {
    }



    private void initUI(View view) {
        getActivity().setTitle("Absence Manager");
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(getResources().getColor(R.color.color_white),getResources().getColor(R.color.colorAccent));
    }


    private void setupViewPager(ViewPager viewPager) {

        AbsenceForHRFragment.ViewPagerAdapter adapter = new AbsenceForHRFragment.ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new AbsenceListHRFragment(),getString(R.string.hr_view_list_absence));
        adapter.addFragment(new HolidayHRFragment(),getString(R.string.hr_holiday));
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> fragmentList = new ArrayList<>();
        private final List<String> fragmentTitles = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager){
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        public void addFragment(Fragment fragment,String title){
            fragmentList.add(fragment);
            fragmentTitles.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitles.get(position);
        }
    }

}
