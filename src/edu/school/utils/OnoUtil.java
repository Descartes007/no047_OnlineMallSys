package edu.school.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class OnoUtil {
	public String getOno() {//生成订单号
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
	      String newDate=sdf.format(new Date());
	      String result="";
	      Random random=new Random();
	      for(int i=0;i<3;i++){
	          result+=random.nextInt(10);
	      }
	      return newDate+result;
	    
	}

}
