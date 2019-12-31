package com.base.vo;

import java.util.List;

public class ECharts {

    //标题
    private String title;

    private List dataList;

    private List indicatorList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List getDataList() {
        return dataList;
    }

    public void setDataList(List dataList) {
        this.dataList = dataList;
    }

    public List getIndicatorList() {
        return indicatorList;
    }

    public void setIndicatorList(List indicatorList) {
        this.indicatorList = indicatorList;
    }
}
