package cn.ed2k.driverbook.activity;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import cn.ed2k.driverbook.R;
import cn.ed2k.driverbook.adapter.FragmentPagerAdapter;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {


    private RadioGroup rg_tab_bar;
    private RadioButton[] rbs;
    private ViewPager vpager;

    private FragmentPagerAdapter mAdapter;


    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;
    public static final int PAGE_FOUR = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager());
        bindViews();
        initView();
        initData();
        setEvent();
    }

    private void bindViews(){
        rg_tab_bar = (RadioGroup)findViewById(R.id.rg_tab_bar);
        rbs = new RadioButton[4];
        rbs[PAGE_ONE] = (RadioButton) findViewById(R.id.rb_home);
        rbs[PAGE_TWO] = (RadioButton) findViewById(R.id.rb_classify);
        rbs[PAGE_THREE] = (RadioButton) findViewById(R.id.rb_love);
        rbs[PAGE_FOUR] = (RadioButton) findViewById(R.id.rb_user);

    }

    private void initView(){
        vpager = (ViewPager) findViewById(R.id.book_vpager);
        vpager.setAdapter(mAdapter);
        vpager.addOnPageChangeListener(this);
        rbs[PAGE_ONE].setChecked(true);
    }

    private void initData() {

    }


    private void setEvent() {
        rg_tab_bar.setOnCheckedChangeListener(this);

        for (RadioButton rb : rbs) {
            //挨着给每个RadioButton加入drawable限制边距以控制显示大小
            Drawable[] drs = rb.getCompoundDrawables();
            //获取drawables
            Rect r = new Rect(0, 0, drs[1].getMinimumWidth()*4/5, drs[1].getMinimumHeight()*4/5);
            //定义一个Rect边界
            drs[1].setBounds(r);

            rb.setCompoundDrawables(null,drs[1],null,null);
            //添加限制给控件
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rb_home:
                vpager.setCurrentItem(PAGE_ONE,false);
                break;
            case R.id.rb_classify:
                vpager.setCurrentItem(PAGE_TWO,false);
                break;
            case R.id.rb_love:
                vpager.setCurrentItem(PAGE_THREE,false);
                break;
            case R.id.rb_user:
                vpager.setCurrentItem(PAGE_FOUR,false);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == 2){
            switch (vpager.getCurrentItem()){
                case PAGE_ONE:
                    rbs[PAGE_ONE].setChecked(true);
                    break;
                case PAGE_TWO:
                    rbs[PAGE_TWO].setChecked(true);
                    break;
                case PAGE_THREE:
                    rbs[PAGE_THREE].setChecked(true);
                    break;
                case PAGE_FOUR:
                    rbs[PAGE_FOUR].setChecked(true);
                    break;
            }
        }
    }
}
