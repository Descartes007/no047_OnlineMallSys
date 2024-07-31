package edu.school.entity;

import java.io.Serializable;
import java.util.List;

//处理分页
public class PageBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private int currentPage;// 当前页码
	private int totalCount;// 总条数
	private int totalPage;// 总页数
	private int currentCount;// 每页条数
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public List<Products> getpList() {
		return pList;
	}

	public void setpList(List<Products> pList) {
		this.pList = pList;
	}

	private List<Products> pList;// 每页显示的数据

	public List<Order> getoList() {
		return oList;
	}

	public void setoList(List<Order> oList) {
		this.oList = oList;
	}

	private List<Order> oList;

	public List<Category> getcList() {
		return cList;
	}

	public void setcList(List<Category> cList) {
		this.cList = cList;
	}

	private List<Category> cList;

	public List<User> getuList() {
		return uList;
	}

	public void setuList(List<User> uList) {
		this.uList = uList;
	}

	private List<User> uList;
	/*private String category;//类别
	private String searchfield;//模糊搜索的关键字*/

	/*public String getCategory() {
		return category;
	}

	public String getSearchfield() {
		return searchfield;
	}

	public void setSearchfield(String searchfield) {
		this.searchfield = searchfield;
	}

	public void setCategory(String category) {
		this.category = category;
	}*/

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

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

	public int getCurrentCount() {
		return currentCount;
	}

	public void setCurrentCount(int currentCount) {
		this.currentCount = currentCount;
	}



}
