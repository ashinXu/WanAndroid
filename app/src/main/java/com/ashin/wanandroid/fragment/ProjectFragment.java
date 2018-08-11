package com.ashin.wanandroid.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ashin.wanandroid.BaseFragment;
import com.ashin.wanandroid.R;

public class ProjectFragment extends BaseFragment {

    public static ProjectFragment getInstance(){
        ProjectFragment fragment = new ProjectFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project, container, false);
        return view;
    }
}
