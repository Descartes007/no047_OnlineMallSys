package edu.school.entity;/*
 *@BelongPackage:edu.school.entity
 *@Description:分类
 */

public class Category {
    private int id;



    public Category() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCname() {
        return cname;
    }

    public Category(int id, String cname) {
        this.id = id;
        this.cname = cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    private String cname;//种类名称
}
