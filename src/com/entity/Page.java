package com.entity;

import java.util.List;

public class Page<T> {
    private Integer pageNum;//总页数
    private Integer pageCurrent;//当前页数
    private Integer furnNum;//条数总数
    private Integer pagePer;//每页展示的条数
    private List<T> list;//当前页数拥有的家居Furns
    private String url;
    public Page() {
    }

    public Page(Integer pageNum, Integer pageCurrent, Integer furnNum, Integer pagePer, List<T> list) {
        this.pageNum = pageNum;
        this.pageCurrent = pageCurrent;
        this.furnNum = furnNum;
        this.pagePer = pagePer;
        this.list = list;
    }


    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageCurrent() {
        return pageCurrent;
    }

    public void setPageCurrent(Integer pageCurrent) {
        this.pageCurrent = pageCurrent;
    }

    public Integer getFurnNum() {
        return furnNum;
    }

    public void setFurnNum(Integer furnNum) {
        this.furnNum = furnNum;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Integer getPagePer() {
        return pagePer;
    }

    public void setPagePer(Integer pagePer) {
        this.pagePer = pagePer;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNum=" + pageNum +
                ", pageCurrent=" + pageCurrent +
                ", furnNum=" + furnNum +
                ", pagePer=" + pagePer +
                ", list=" + list +
                '}';
    }
}
