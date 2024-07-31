package edu.school.entity;

public class Order {

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private Integer id;

	public Order() {
	}

	public Order(Integer id, String orderno, double money, String address, String receiver, String phone, int status, String createTime,Integer uid) {
		this.id = id;
		this.orderno = orderno;
		this.money = money;
		this.address = address;
		this.receiver = receiver;
		this.phone = phone;
		this.status = status;
		this.createTime = createTime;
		this.uid=uid;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}



	private String orderno;//订单编号
	private double money; // 订单总价
	private String address; // 送货地址
	private String receiver; // 收货人姓名
	private String phone; // 收货人电话
	private int status; // 订单状态
	private String createTime; // 下单时间

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	private Integer uid;



}
