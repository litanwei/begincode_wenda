package net.begincode.bean;

import net.begincode.common.BeginCodeConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stay on 2016/9/19  18:26.
 */
public class Page<T> {
    private int totalPage;  //总页数
    private List<T> data = new ArrayList<>();
    private int pageNum;   //当前页数
    private int pageSize = BeginCodeConstant.PAGESIZE;  //每页显示的行数
    private int pageCount;  //总记录数

    public Page(){
    }

    public int getTotalPage() {
        if (pageCount % pageCount == 0) {
            totalPage = pageCount / pageSize;
        } else {
            totalPage = pageCount / pageSize + 1;
        }
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
}
