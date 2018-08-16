package com.cs509.common;

import java.util.Hashtable;

public class CompareLocaltime {

	public int Compare_time(String localtime1, String localtime2) {
		int time_len = 0;
		Hashtable <Integer, Integer> Month_day1 = new Hashtable <Integer,Integer>();
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
		Hashtable <Integer, Integer> Month_day2 = new Hashtable <Integer,Integer>();
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
		int adate_year = Integer.parseInt(localtime1.substring(0, 4));
		int ddate_year = Integer.parseInt(localtime2.substring(0, 4));
		int adate_mon = Integer.parseInt(localtime1.substring(5, 7));
		int ddate_mon = Integer.parseInt(localtime2.substring(5, 7));		
		int adate_day = Integer.parseInt(localtime1.substring(8, 10));
		int ddate_day = Integer.parseInt(localtime2.substring(8, 10));
		int adate_hr = Integer.parseInt(localtime1.substring(11, 13));
		int ddate_hr = Integer.parseInt(localtime2.substring(11, 13));
		int adate_min = Integer.parseInt(localtime1.substring(14, 16));
		int ddate_min = Integer.parseInt(localtime2.substring(14, 16));;
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
