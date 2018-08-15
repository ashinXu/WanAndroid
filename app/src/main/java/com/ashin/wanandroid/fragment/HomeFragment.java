package com.ashin.wanandroid.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ashin.wanandroid.BaseFragment;
import com.ashin.wanandroid.Const;
import com.ashin.wanandroid.R;
import com.ashin.wanandroid.adapter.HomeAdapter;
import com.ashin.wanandroid.bean.ArticleBean;
import com.ashin.wanandroid.bean.BannerBean;
import com.ashin.wanandroid.bean.GetArticleListResult;
import com.ashin.wanandroid.network.BaseResult;
import com.ashin.wanandroid.network.RetrofitHelper;
import com.ashin.wanandroid.utils.RxUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class HomeFragment extends BaseFragment {

    @BindView(R.id.smfl)
    SmartRefreshLayout refreshLayout;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    public static final String TAG = HomeFragment.class.getSimpleName();
    private List<BannerBean> data;
    private List<ArticleBean> articles;

    public static HomeFragment getInstance() {
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
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {

        RetrofitHelper.init().getArticleList(0)
                .compose(RxUtils.<BaseResult<GetArticleListResult>>switchSchelers())
                .subscribe(new Observer<BaseResult<GetArticleListResult>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResult<GetArticleListResult> result) {
                        GetArticleListResult data = result.getData();
                        articles = data.getDatas();

                        Log.v(Const.LOG_TAG,"result = "+result.isSuccess());
                        updateView();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void updateView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        HomeAdapter homeAdapter = new HomeAdapter(R.layout.item_article_layout, articles);
        recyclerView.setAdapter(homeAdapter);

    }

    private void updateBanner() {


    }

    private void initView() {

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                //刷新
                Log.v(Const.LOG_TAG,"onRefresh");
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                //加载更多
                Log.v(Const.LOG_TAG,"onLoadMore");
            }
        });

    }

}
