package cn.ed2k.driverbook.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.ViewGroup;

import cn.ed2k.driverbook.activity.MainActivity;
import cn.ed2k.driverbook.fragment.ClassifyFragment;
import cn.ed2k.driverbook.fragment.HomeFragment;
import cn.ed2k.driverbook.fragment.LoveFragment;
import cn.ed2k.driverbook.fragment.UserFragment;

/**
 * Created by 44525 on 2018/4/4.
 */
public class FragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter{

    private final int PAGER_COUNT = 4;
    private Fragment homeFragment = null;
    private ClassifyFragment classifyFragment= null;
    private LoveFragment loveFragment = null;
    private UserFragment userFragment = null;

    public FragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        homeFragment = new HomeFragment();
        classifyFragment = new ClassifyFragment();
        loveFragment = new LoveFragment();
        userFragment = new UserFragment();
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case MainActivity.PAGE_ONE:
                fragment = homeFragment;
                break;
            case MainActivity.PAGE_TWO:
                fragment = classifyFragment;
                break;
            case MainActivity.PAGE_THREE:
                fragment = loveFragment;
                break;
            case MainActivity.PAGE_FOUR:
                fragment = userFragment;
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return PAGER_COUNT;
    }

    @Override
    public Object instantiateItem(ViewGroup vg, int position) {
        return super.instantiateItem(vg, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        System.out.println("position Destory" + position);
        super.destroyItem(container, position, object);
    }
}
