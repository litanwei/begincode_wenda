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
    private int currentNum;   //当前页数
    private int pageEachSize = BeginCodeConstant.PAGESIZE;  //每页显示的行数
    private int totalNum;  //总记录数

    public Page(){
    }

    public int getTotalPage() {
        if(totalNum % pageEachSize == 0){
            totalPage = totalNum / pageEachSize;
        }else{
            totalPage = totalNum / pageEachSize +1;
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

    public int getCurrentNum() {
        return currentNum;
    }

    public void setCurrentNum(int currentNum) {
        this.currentNum = currentNum;
    }

    public int getPageEachSize() {
        return pageEachSize;
    }

    public void setPageEachSize(int pageEachSize) {
        this.pageEachSize = pageEachSize;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }
}
