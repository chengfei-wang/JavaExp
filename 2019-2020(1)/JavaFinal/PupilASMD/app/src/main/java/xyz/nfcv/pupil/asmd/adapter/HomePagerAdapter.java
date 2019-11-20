package xyz.nfcv.pupil.asmd.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

import xyz.nfcv.pupil.asmd.ui.fragment.AnalysisFragment;
import xyz.nfcv.pupil.asmd.ui.fragment.ProblemSolveFragment;

public class HomePagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments = new ArrayList<>();

    public HomePagerAdapter(@NonNull FragmentManager fm) {
        super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        fragments.add(new AnalysisFragment());
        fragments.add(new ProblemSolveFragment());
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
