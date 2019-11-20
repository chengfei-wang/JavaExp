package xyz.nfcv.pupil.asmd.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

import xyz.nfcv.pupil.asmd.ui.fragment.AnalysisFragment;
import xyz.nfcv.pupil.asmd.ui.fragment.TestFragment;
import xyz.nfcv.pupil.asmd.widget.ViewPager;

public class MainPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments = new ArrayList<>();

    public MainPagerAdapter(@NonNull FragmentManager fm, ViewPager mainViewpager) {
        super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        fragments.add(new TestFragment());
        fragments.add(new AnalysisFragment());

        mainViewpager.setAdapter(this);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position%fragments.size());
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
