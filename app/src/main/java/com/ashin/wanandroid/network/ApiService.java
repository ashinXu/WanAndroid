package com.ashin.wanandroid.network;

import com.ashin.wanandroid.bean.BannerBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {


    @GET("banner/json")
    Observable<BaseResult<List<BannerBean>>> getBanner();

}
