package net.begincode.bean;

import net.begincode.common.BeginCodeConstant;

/**
 * Created by Stay on 2016/9/19  11:43.
 */
public class PageParam extends Param {

    private int page; //传入的当前页号

    private int pageEachSize = BeginCodeConstant.PAGESIZE;  //每页显示的行数

    @Override
    public void check() {
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageEachSize() {
        return pageEachSize;
    }

    public void setPageEachSize(int pageEachSize) {
        this.pageEachSize = pageEachSize;
    }
}
