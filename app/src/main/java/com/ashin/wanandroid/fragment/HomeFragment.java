package com.ashin.wanandroid.fragment;

import android.annotation.SuppressLint;
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
import com.ashin.wanandroid.utils.GlideImageLoader;
import com.ashin.wanandroid.utils.RxUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

public class HomeFragment extends BaseFragment {



    @BindView(R.id.smfl)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    public static final String TAG = HomeFragment.class.getSimpleName();
    private List<ArticleBean> articles;
    private List<BannerBean> bannerData;
    private View bannerLayout;
    private int index = 0;
    private HomeAdapter homeAdapter;
    private Banner banner;
    private View bannerView;

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
        getBanner();

        getArticleList();
    }

    @SuppressLint("CheckResult")
    private void getArticleList() {

        RetrofitHelper.init().getArticleList(index)
                .compose(RxUtils.<BaseResult<GetArticleListResult>>switchSchelers())
                .subscribe(new Consumer<BaseResult<GetArticleListResult>>() {
                    @Override
                    public void accept(BaseResult<GetArticleListResult> getArticleListResultBaseResult) throws Exception {
                        GetArticleListResult data = getArticleListResultBaseResult.getData();
                        index = data.getCurPage();
                        if (index == 1){
                            articles = data.getDatas();
                            homeAdapter.replaceData(articles);
                        }else {
                            articles.addAll(data.getDatas());
                            homeAdapter.addData(data.getDatas());
                        }
                    }
                });

    }



    @SuppressLint("CheckResult")
    private void getBanner() {
        RetrofitHelper.init().getBanner()
                .compose(RxUtils.<BaseResult<List<BannerBean>>>switchSchelers())
               .subscribe(new Consumer<BaseResult<List<BannerBean>>>() {
                   @Override
                   public void accept(BaseResult<List<BannerBean>> listBaseResult) throws Exception {
                       bannerData = listBaseResult.getData();
                       updateBanner();
                   }
               });
    }

    private void updateView() {


    }


    private void updateBanner() {

        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(bannerData);
        banner.start();

    }

    private void initView() {
        bannerView = LayoutInflater.from(getActivity()).inflate(R.layout.banner_layout, null);
        banner = bannerView.findViewById(R.id.banner_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        homeAdapter = new HomeAdapter(R.layout.item_article_layout, articles);
        recyclerView.setAdapter(homeAdapter);
        homeAdapter.addHeaderView(bannerView);

        homeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Log.v(Const.LOG_TAG,"position = "+position);
            }
        });

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                //刷新
                Log.v(Const.LOG_TAG, "onRefresh");
                index = 0;
                initData();
                refreshLayout.finishRefresh(2000);
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                //加载更多
                Log.v(Const.LOG_TAG, "onLoadMore");
                index++;
                getArticleList();
                refreshLayout.finishLoadMore(2000);
            }
        });

    }

}
