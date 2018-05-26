package utils;

import java.util.Hashtable;

public class CompareTime {

	public int Compare_time(String transfer1_adate, String transfer1_startdate) {
		int time_len = 0;
		Hashtable <String, Integer> Month_convert = new Hashtable <>();
		Month_convert.put("Jan", 1);
		Month_convert.put("Feb", 2);
		Month_convert.put("Mar", 3);
		Month_convert.put("Apr", 4);
		Month_convert.put("May", 5);
		Month_convert.put("Jun", 6);
		Month_convert.put("Jul", 7);
		Month_convert.put("Aug", 8);
		Month_convert.put("Sep", 9);
		Month_convert.put("Oct", 10);
		Month_convert.put("Nov", 11);
		Month_convert.put("Dec", 12);	
		Hashtable <Integer, Integer> Month_day1 = new Hashtable <>();
		Month_day1.put(1, 31);
		Month_day1.put(2, 28);
		Month_day1.put(3, 31);
		Month_day1.put(4, 30);
		Month_day1.put(5, 31);
		Month_day1.put(6, 30);
		Month_day1.put(7, 31);
		Month_day1.put(8, 31);
		Month_day1.put(9, 30);
		Month_day1.put(10, 31);
		Month_day1.put(11, 30);
		Month_day1.put(12, 31);
		Hashtable <Integer, Integer> Month_day2 = new Hashtable <>();
		Month_day2.put(1, 31);
		Month_day2.put(2, 29);
		Month_day2.put(3, 31);
		Month_day2.put(4, 30);
		Month_day2.put(5, 31);
		Month_day2.put(6, 30);
		Month_day2.put(7, 31);
		Month_day2.put(8, 31);
		Month_day2.put(9, 30);
		Month_day2.put(10, 31);
		Month_day2.put(11, 30);
		Month_day2.put(12, 31);
		int adate_year = Integer.parseInt(transfer1_adate.replace(" ","").substring(0, 4));
		int ddate_year = Integer.parseInt(transfer1_startdate.replace(" ","").substring(0, 4));
		int adate_mon = Month_convert.get(transfer1_adate.replace(" ","").substring(4, 7));
		int ddate_mon = Month_convert.get(transfer1_startdate.replace(" ","").substring(4, 7));		
		int adate_day = Integer.parseInt(transfer1_adate.replace(" ","").substring(7, 9));
		int ddate_day = Integer.parseInt(transfer1_startdate.replace(" ","").substring(7, 9));
		int adate_hr = Integer.parseInt(transfer1_adate.replace(" ","").substring(9, 11));
		int ddate_hr = Integer.parseInt(transfer1_startdate.replace(" ","").substring(9, 11));
		int adate_min = Integer.parseInt(transfer1_adate.replace(" ","").substring(12, 14));
		int ddate_min = Integer.parseInt(transfer1_startdate.replace(" ","").substring(12, 14));
		if(ddate_year-adate_year>1)
			return 100000;
		else if((adate_year%4==0)&&(ddate_year-adate_year)==1)
		{ 
			for(int i=1;i<ddate_mon;i++)
			{
				time_len = time_len + Month_day1.get(i);
			}
			time_len = time_len + ddate_day;
			for(int i=adate_mon;i<13;i++)
			{
				time_len = time_len + Month_day2.get(i);
			}
			time_len = time_len - adate_day;
			if(time_len == 1){
				time_len = 24*60+(ddate_hr*60+ddate_min)-(adate_hr*60+adate_min);
			    return time_len;
			}			
			if(time_len == 0){
				time_len = (ddate_hr*60+ddate_min)-(adate_hr*60+adate_min);
				return time_len;
			}				
			else 
				return 100000;
		}
		
		else if((ddate_year%4==0)&&(ddate_year-adate_year)==1)
		{ 
			for(int i=1;i<ddate_mon;i++)
			{
				time_len = time_len + Month_day2.get(i);
			}
			time_len = time_len + ddate_day;
			for(int i=adate_mon;i<13;i++)
			{
				time_len = time_len + Month_day1.get(i);
			}
			time_len = time_len - adate_day;
			if(time_len == 1){
				time_len = 24*60+(ddate_hr*60+ddate_min)-(adate_hr*60+adate_min);
			    return time_len;
			}			
			if(time_len == 0){
				time_len = (ddate_hr*60+ddate_min)-(adate_hr*60+adate_min);
				return time_len;
			}				
			else 
				return 100000;
		}
		
		else if ((adate_year%4==0)&&(ddate_year-adate_year)==0){
			for(int i=1;i<ddate_mon;i++)
			{
				time_len = time_len + Month_day2.get(i);
			}
			time_len = time_len + ddate_day;
			for(int i=1;i<adate_mon;i++)
			{
				time_len = time_len - Month_day2.get(i);
			}
			time_len = time_len - adate_day;
			if(time_len == 1){
				time_len = 24*60+(ddate_hr*60+ddate_min)-(adate_hr*60+adate_min);
				return time_len;
			}				
			if(time_len == 0){
				time_len = (ddate_hr*60+ddate_min)-(adate_hr*60+adate_min);
				return time_len;
			}
			else 
				return 100000;
		}
		else if ((adate_year%4!=0)&&(ddate_year-adate_year)==0){
			for(int i=1;i<ddate_mon;i++)
			{
				time_len = time_len + Month_day1.get(i);
			}
			time_len = time_len + ddate_day;
			for(int i=1;i<adate_mon;i++)
			{
				time_len = time_len - Month_day1.get(i);
			}
			time_len = time_len - adate_day;
			if(time_len == 1){
				time_len = 24*60+(ddate_hr*60+ddate_min)-(adate_hr*60+adate_min);
				return time_len;
			}
			if(time_len == 0){
				time_len = (ddate_hr*60+ddate_min)-(adate_hr*60+adate_min);
				return time_len;
			}
			else 
				return 100000;
		}
		else 
			return 100000;			
	}
}
