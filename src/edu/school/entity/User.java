package edu.school.entity;/*
 *@BelongPackage:edu.school.entity
 *@Description:用户
 */


public class User {
    private Integer id; // 主键
    private String name; // 用户姓名
    private String password; // 用户密码

    public User(Integer id, String name, String password, String gender, String email, String telephone, String address, String registeTime) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.email = email;
        this.telephone = telephone;
        this.address = address;
        this.registeTime = registeTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public User() {
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String company) {
        this.address = company;
    }

    public String getRegisteTime() {
        return registeTime;
    }

    public void setRegisteTime(String registeTime) {
        this.registeTime = registeTime;
    }

    private String gender; // 用户性别
    private String email; // 用户邮箱
    private String telephone; // 用户联系电话

    private String address;//所在地址
    private String registeTime;// 注册时间


}
