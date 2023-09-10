package com.example.demo.commons;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import com.example.demo.model.UserCredentials;
import com.example.demo.model.lastAccountNumberCount;

public class commonUtils {
	int num = 1001;
	public void updateLoginStatus(UserCredentials uc) {
		
		
	}
	
	public String generateCustomerID() {
		String customerId = "CU";
		num = num+1;
		customerId = customerId+""+num;
		
		return customerId;
	}
	
	public String getCurrentYear() {
		String Year="0000";
		Date d=new Date();
		Year=Integer.toString(d.getYear());
		return Year;
	}
	public String getNewAccountNumber(String lastVal,String bankUniqueNumber) {
		String accNo="00";
		commonUtils cu=new commonUtils();
		accNo=bankUniqueNumber+cu.getCurrentYear()+lastVal;		
		return accNo;
	}
	
	public String getFinalYearForRD(String year) {
		String yr = year.substring(0, 4);
		String date = year.substring(4);
		int y = Integer.parseInt(yr);
		y = y + 3;
		System.out.println(yr+", "+date);
		String newDate = Integer.toString(y)+date; 
		return newDate;
	}
	
	public String getFinalYearForFD(String year) {
		String yr = year.substring(0, 4);
		String date = year.substring(4);
		int y = Integer.parseInt(yr);
		y = y + 5;
		System.out.println(yr+", "+date);
		String newDate = Integer.toString(y)+date; 
		return newDate;
	}
	
	
	public String calculateReturnAmountForRDafterEveryMonth(String principle, String time, double rate, String monthlyAmount) {
		int p= Integer.parseInt(principle);
		int t = Integer.parseInt(time);
		double res = (p*t*rate)/100;
		double total = p+res+Double.parseDouble(monthlyAmount);
		return String.valueOf(total);
		
	}
	
	public String calcFinalAmountForFD(String principle, String time, double rate) {
		int p= Integer.parseInt(principle);
		int t = Integer.parseInt(time);
		double res = (p*t*rate)/100;
		double total = p+res;
		return String.valueOf(total);
	}
	
	public String getTimeDifference(String StratTime) throws ParseException {
		String timeDiff="";
		 String mt[]=StratTime.split("-");
	     String newStartDateFormated=mt[2]+"-"+mt[1]+"-"+mt[0];
		commonUtils cmUt=new commonUtils();
		
		SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
		String inputString1 = newStartDateFormated;
		String inputString2 = cmUt.getCurrentDate();

		
		    Date date1 = myFormat.parse(inputString1);
		    Date date2 = myFormat.parse(inputString2);
		    long diff = date2.getTime() - date1.getTime();
		    double k=TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		    int m=(int)k/356;
		    System.out.println ("Days: " +m);
		return Integer.toString(m);
	}
	
	public String deductAmount(String s1, String s2) {
		int oldMoney = Integer.parseInt(s2);
		int money = Integer.parseInt(s1);
		int amount = oldMoney-money;
		
		return Integer.toString(amount);
	}
	public String creditAmount(String s1, String s2) {
		int oldMoney = Integer.parseInt(s2);
		int money = Integer.parseInt(s1);
		int amount = oldMoney+money;
		
		return Integer.toString(amount);
		
	}
	
	public String getAlphaNumericString() {
		int len = 12;
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
									+"0123456789"
									+"abcdefghijklmnopqrstuvwxyz";
		StringBuilder sb = new StringBuilder(len);
		for(int i = 0; i<len; i++) {
			int index = (int)(AlphaNumericString.length()*Math.random());
			sb.append(AlphaNumericString.charAt(index));
		}
		
		return sb.toString();
	}
	
	public String getCurrentDate() {
		String today="00-00-0000";
		 LocalDateTime myDateObj = LocalDateTime.now();
		 DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		 today = myDateObj.format(myFormatObj);
		return today;
	}
	
	public String rdReturn(String principle,String time) {
		String returnAmount="";
		double rate=6.5;
		int p= Integer.parseInt(principle);
		int t = Integer.parseInt(time);
		double res = (p*t*rate)/(100*12);
		double total = p+res;
		returnAmount=String.valueOf(res);
		return returnAmount;
	}
}

