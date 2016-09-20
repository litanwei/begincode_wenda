package net.begincode.core.param;

import net.begincode.bean.Page;
import net.begincode.bean.Param;

/**
 * Created by Stay on 2016/9/19  11:43.
 */
public class PageParam<T> extends Param {

    private int pageNum; //传入的当前页号

    private Page<T> page = new Page<T>();


    @Override
    public void check() {
        if(pageNum <= 0 ){
            page.setPageNum(1);      //把当前页设为1
        }else if(page.getPageNum() > page.getPageCount()){
            page.setPageNum(page.getTotalPage());    //把当前页设置为最大值
        }
    }

    public PageParam(int pageNum) {
        page.setPageNum(pageNum);
        this.pageNum = pageNum;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public Page<T> getPage() {
        return page;
    }

    public void setPage(Page<T> page) {
        this.page = page;
    }
}
