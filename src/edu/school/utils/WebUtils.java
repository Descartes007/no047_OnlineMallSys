package edu.school.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

public class WebUtils {

	public static  <T> T copyParamToBean(Map value, T bean){//使用泛型来减少request.getParameter的重复代码，优化开发
		try {

			System.out.println("注入之前"+bean);
			BeanUtils.populate(bean,value);
			System.out.println("注入之后"+bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
	
	public static int parseInt(String sid,int defalultvalue) {//把String类型的id转化Integer类型的
		  try {
	            return Integer.parseInt(sid);
	        }catch (Exception e){
	            e.printStackTrace();
	        }
	        return defalultvalue;
	}


}
