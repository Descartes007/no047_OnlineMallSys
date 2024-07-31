package edu.school.entity;/*
 *@BelongsProject：pm_sys
 *@BelongPackage:edu.school.entity

 *@Description:网站分页设计
 */

import java.util.List;

public class QtPageBean<T> {
    private int totalCount;//总记录数
    private int totalPage;//总页数

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    private int currentPage;//当前页
    private  int pageSize;//每页显示的数据量
    private List<T> list;//封装

}
