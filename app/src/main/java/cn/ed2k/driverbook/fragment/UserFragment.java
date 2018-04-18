package cn.ed2k.driverbook.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.ed2k.driverbook.R;
import cn.ed2k.driverbook.app.App;

/**
 * Created by 44525 on 2018/4/4.
 */
public class UserFragment extends Fragment {

    private App mApp;
    private TextView mUserName;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mApp = (App) getActivity().getApplication();
        bindView();
        initData();
        initView();
    }

    private void bindView(){
        mUserName = (TextView) getView().findViewById(R.id.user_name);
    }

    private void initView(){
        mUserName.setText(mApp.getUserName());
    }

    private void initData(){

    }
}
