package com.ashin.wanandroid.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.ashin.wanandroid.R;
import com.ashin.wanandroid.bean.ArticleBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeAdapter extends BaseQuickAdapter<ArticleBean,HomeAdapter.HomeViewHolder>{

    public HomeAdapter(int layoutResId, @Nullable List<ArticleBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(HomeViewHolder helper, ArticleBean item) {
        helper.tvName.setText(item.getAuthor());
        helper.tvTopic.setText(item.getSuperChapterName()+"/"+item.getChapterName());
        helper.tvTitle.setText(item.getTitle());
    }


    class HomeViewHolder extends BaseViewHolder{

        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_topic)
        TextView tvTopic;
        @BindView(R.id.tv_title)
        TextView tvTitle;

        public HomeViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}
