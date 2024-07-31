package edu.school.entity;/*
 *@BelongPackage:edu.school.entity
 *@Description:电影票
 */



public class Products {
    private Integer id;

    @Override
    public String toString() {
        return "Products{" +
                "id=" + id +
                ", pno='" + pno + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", c_id=" + c_id +
                ", pnum=" + pnum +
                ", imgUrl='" + imgUrl + '\'' +
                ", description='" + description + '\'' +
                ", startDate='" + startDate + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Products() {
    }

    public String getPno() {
        return pno;
    }

    public void setPno(String pno) {
        this.pno = pno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getC_id() {
        return c_id;
    }

    public void setC_id(Integer c_id) {
        this.c_id = c_id;
    }

    public Integer getPnum() {
        return pnum;
    }

    public void setPnum(Integer pnum) {
        this.pnum = pnum;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Products(Integer id, String pno, String name, double price, Integer c_id, Integer pnum, String imgUrl, String description, String startDate, String endDate) {
        this.id = id;
        this.pno = pno;
        this.name = name;
        this.price = price;
        this.c_id = c_id;
        this.pnum = pnum;
        this.imgUrl = imgUrl;
        this.description = description;
        this.startDate = startDate;

    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }



    private String pno;//物品编号
    private String  name;
    private double price;//拍卖价格
    private Integer c_id;//物品分类的外键。
    private Integer pnum;//物品数量
    private String imgUrl;//图片路径
    private String description;//物品简介
    private String startDate;//拍卖开始日期

}
