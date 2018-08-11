package com.ashin.wanandroid.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ashin.wanandroid.BaseFragment;
import com.ashin.wanandroid.Const;
import com.ashin.wanandroid.R;
import com.ashin.wanandroid.bean.BannerBean;
import com.ashin.wanandroid.network.BaseResult;
import com.ashin.wanandroid.network.RetrofitHelper;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeFragment extends BaseFragment {

    @BindView(R.id.load_view)
    AVLoadingIndicatorView loadView;


    public static final String TAG = HomeFragment.class.getSimpleName();

    public static HomeFragment getInstance(){
        HomeFragment fragment = new HomeFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();

        initData();
    }

    private void initData() {
        loadView.show();

        RetrofitHelper.init().getBanner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResult<List<BannerBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResult<List<BannerBean>> listBaseResult) {
                        List<BannerBean> data = listBaseResult.getData();
                        Log.v(Const.LOG_TAG,"data = "+data.toString());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

      /*  ApiService service = RetrofitHelper.createService();
        Call<BaseResult<List<BannerBean>>> call = service.getBanner();
        call.enqueue(new Callback<BaseResult<List<BannerBean>>>() {
            @Override
            public void onResponse(Call<BaseResult<List<BannerBean>>> call, Response<BaseResult<List<BannerBean>>> response) {
                BaseResult<List<BannerBean>> body = response.body();
                List<BannerBean> data = body.getData();
                Log.v(Const.LOG_TAG,"size = "+data.size());
            }

            @Override
            public void onFailure(Call<BaseResult<List<BannerBean>>> call, Throwable t) {

            }
        });*/
    }

    private void initView() {



    }
}
