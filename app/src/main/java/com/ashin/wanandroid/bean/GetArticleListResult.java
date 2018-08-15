package com.ashin.wanandroid.bean;

import java.util.List;

public class GetArticleListResult {


    private int curPage;
    private int offset;
    private int pageCount;
    private int size;
    private int total;
    private boolean over;

    private List<ArticleBean> datas;

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public List<ArticleBean> getDatas() {
        return datas;
    }

    public void setDatas(List<ArticleBean> datas) {
        this.datas = datas;
    }


    @Override
    public String toString() {
        return "GetArticleListResult{" +
                "curPage=" + curPage +
                ", offset=" + offset +
                ", pageCount=" + pageCount +
                ", size=" + size +
                ", total=" + total +
                ", over=" + over +
                ", datas=" + datas +
                '}';
    }
}
