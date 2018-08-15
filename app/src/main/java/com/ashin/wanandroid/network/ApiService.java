package com.ashin.wanandroid.network;

import com.ashin.wanandroid.bean.BannerBean;
import com.ashin.wanandroid.bean.GetArticleListResult;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {


    @GET("banner/json")
    Observable<BaseResult<List<BannerBean>>> getBanner();


    @GET("article/list/{index}/json")
    Observable<BaseResult<GetArticleListResult>> getArticleList(@Path("index") int index);

}
