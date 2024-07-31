package edu.school.entity;/*

 *@BelongPackage:edu.school.entity
 *@Description:todo
 */

public class Cart {
    private Integer id;//主键

    public Integer getId() {
        return id;
    }

    public Cart() {
    }

    public Cart(Integer id, String profile, String name, double price, Integer quantity, Integer stock, Integer pid, Integer uid) {
        this.id = id;
        this.profile = profile;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.stock = stock;
        this.pid = pid;
        this.uid = uid;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    private String profile;//图片
    private String name;//品名称
    private double price;//价格
    private Integer quantity;//购物车数量
    private Integer stock;//库存
    private Integer pid;//外键
    private Integer uid;//外键

 }
